package iducs.jsp.b.csjyjbblog.controller;

//import iducs.jsp.b.csjyjbblog.model.Blog;
import iducs.jsp.b.csjyjbblog.model.Member;
import iducs.jsp.b.csjyjbblog.util.DescByMember;
import iducs.jsp.b.csjyjbblog.repository.BlogDAOImpl;
import iducs.jsp.b.csjyjbblog.repository.MemberDAOImpl;
import iducs.jsp.b.csjyjbblog.util.DescByBlogTitle;
import iducs.jsp.b.csjyjbblog.util.Pagination;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

// Controller
// 첫 슬래시는 웹 애플리케이션의 시작위치임
@WebServlet(name = "members", urlPatterns = {"/members/sort.do","/members/update.do","/members/delete.do","/members/list.do"
        ,"/members/post-form.do","/members/detail.do","/members/register-form.do","/members/login-form.do"
        ,"/members/logout.do","/members/login.do","/members/register.do"}) // urlPatterns : 다수의 url을 기술할 수 있다.
public class MemberController extends HttpServlet {
//    private String message;
//
//    public void init() {
//        message = "Hello World!";
    //}

    public void doService(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        // response.setContentType("text/html");
        // response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        /* URI를 문자열에 저장하고, http://..... war/가 contextPath가 됨,
            전체 URI에서 contextPath를 제거한 경로명으로 명령을 구분함.
         */
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = uri.substring(contextPath.length() + 1); // blogs/post.do,  blogs/list.do가 반환됨
        String action = command.substring(command.lastIndexOf("/") + 1); // post.do, get.do가 반환됨

        MemberDAOImpl dao = new MemberDAOImpl();
        //System.out.println(uri);
//        // /Gradle___iducs_jsp_b___csjyj_b_blog_1_0_SNAPSHOT_war/blogs/list.do
        //System.out.println(contextPath);
//        // /Gradle___iducs_jsp_b___csjyj_b_blog_1_0_SNAPSHOT_war
        //System.out.println(command);
//        //blogs/list.do
        //System.out.println("command : " + action);

        // 데이터베이스 처리 요청 또는 서비스 요청 코드가 추가
        //MemberDAOImpl dao = new MemberDAOImpl();
        if (action.equals("register.do")) {
            Member member = new Member();
            member.setEmail(request.getParameter("email"));
            member.setPw(request.getParameter("pw"));
            member.setName(request.getParameter("name"));
            member.setPhone(request.getParameter("phone"));
            member.setAddress(request.getParameter("address"));

            if (dao.create(member) > 0) { //select * from blogb //dto를 dao에 넘겨준다.
                request.setAttribute("member", member);
                request.getRequestDispatcher("../errors/ok.jsp").forward(request, response); // blogs/post.jsp로 포워딩
            } else {
                request.getRequestDispatcher("../errors/error.jsp").forward(request, response); // blogs/post.jsp로 포워딩
            }

        } else if (action.equals("list.do")) {
            ArrayList<Member> memberList = new ArrayList<Member>();// 처리결과 한개 이상의 블로그를 저장하는 객체
            String pageNo = request.getParameter("pn");

            int curPageNo = (pageNo != null)? Integer.parseInt(pageNo):1;
            int perPage = 3;    //한 페이지에 나타나는 행의 수
            int perPagination = 3;  //한 화면에 나타나는 페이지 번호 수
            int totalRows = dao.readTotalRows(); //dao에서 총 행의 수를 질의함
            Pagination pagination = new Pagination(curPageNo, perPage, perPagination, totalRows);
            if ((memberList = (ArrayList<Member>) dao.readListPagination(pagination)) != null) {
                //한 개 이상의 블로그가 반환, JCF(Java Collecition FrameWork)에 대한 이해
                request.setAttribute("memberList", memberList);//블로그 레코드의 전체를 호출?요청?불러옴?
                request.setAttribute("pagination", pagination);
                request.getRequestDispatcher("../members/list.jsp").forward(request, response); //blog/list.jsp로 포워딩
            } else {
                request.setAttribute("errMsg", "회원 목록 조회 실패");
                request.getRequestDispatcher("../errors/error.jsp").forward(request, response); //오류
            }
        }
        else if (action.equals("register-form.do")){
            request.getRequestDispatcher("../members/register.jsp").forward(request,response);
        }
        else if (action.equals("login-form.do")){
            request.getRequestDispatcher("../members/login.jsp").forward(request,response);
        }
        else if (action.equals("login.do")) {

            Member member = new Member();
            member.setEmail(request.getParameter("email"));
            member.setPw(request.getParameter("pw"));
            //member.setId(Long.parseLong(request.getParameter("id")));
            Member retMember = null;

            if ((retMember = dao.read(member)) != null) {
                //request.setAttribute("member", retMember); // Key -> member Key & Value with "map"
                session.setAttribute("logined", retMember); //${sessionScopte.member}
                //session.setAttribute("member", member.getEmail()); //${sessionScopte.member.email}
                request.getRequestDispatcher("../main/index.jsp").forward(request, response);
            } else {
                request.setAttribute("errMsg", "로그인 실패");
                request.getRequestDispatcher("../errors/error.jsp").forward(request, response);//오류
            }
        }  else if (action.equals("detail.do")) {
            Member member = new Member();
            member.setId(Long.parseLong(request.getParameter("id")));
            Member retMember = null;
            if ((retMember = dao.readByEmail(member)) != null) { //readByEmail
                request.setAttribute("member", retMember); //  email로 조회한 회원 정보 객체를 memer라는 키로 request에 attribute로 저장
             //   session.setAttribute("logined","회원 정보");
                request.getRequestDispatcher("../members/detail-form.jsp").forward(request, response);
            } else {
                request.setAttribute("errMsg", "회원 정보 조회 실패");
                request.getRequestDispatcher("../errors/error.jsp").forward(request, response);//오류
            }
        } else if (action.equals("logout.do")) {
            session.invalidate(); //session 객체를 무효화
            request.getRequestDispatcher("../main/index.jsp").forward(request, response);
        }
        else if(action.equals("update.do")){ //dao에게 업데이트를 요청
            Member member = new Member();
            member.setId(Long.parseLong(request.getParameter("id")));
            member.setEmail(request.getParameter("email"));
            member.setPw(request.getParameter("pw"));
            member.setName(request.getParameter("name"));
            member.setPhone(request.getParameter("phone"));
            member.setAddress(request.getParameter("address"));

            if(dao.update(member) > 0 ){
                request.setAttribute("member", member);
                // 처리 결과를 view에 전달한다. error.jsp -> process-error.jsp
                request.getRequestDispatcher("../members/detail.do").forward(request, response); // blogs/post.jsp로 포워딩
            } else {
                request.getRequestDispatcher("../errors/error.jsp").forward(request, response); // blogs/post.jsp로 포워딩
            }
        }
        else if(action.equals("delete.do")) { //dao에게 업데이트를 요청
            Member member = new Member();
            member.setId(Long.parseLong(request.getParameter("id")));

            if (dao.delete(member) > 0) {
                request.setAttribute("member", member);
              //  request.setAttribute("work", "블로그 삭제");
                // 처리 결과를 view에 전달한다. error.jsp -> process-error.jsp
                session.invalidate(); //session 객체를 무효화
                request.getRequestDispatcher("../main/index.jsp").forward(request, response); // blogs/post.jsp로 포워딩
            } else {
                request.getRequestDispatcher("../errors/error.jsp").forward(request, response); // blogs/post.jsp로 포워딩
            }
        }
        else if (action.equals("sort.do")) {
            ArrayList<Member> memberList = new ArrayList<Member>();   // 처리결과 한개 이상의 블로그를 저장하는 객체

            String properties = request.getParameter("by");
            if ((memberList = (ArrayList<Member>) dao.readList()) != null)
            {   //한 개 이상의 블로그가 반환 JCF(Java Collection Framework)에 대한 이해
                if (properties.equals("desc,title"))
                    Collections.sort(memberList, new DescByMember());
                request.setAttribute("memberList", memberList);
                request.getRequestDispatcher("../members/list.jsp").forward(request, response); // blogs/list.jsp로 포워딩
            }else
            {
                request.setAttribute("errMsg", "회원 목록 조회 실패" );
                request.getRequestDispatcher("../errors/error.jsp").forward(request, response); // blogs/list.jsp로 포워딩
            }

        }
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doService(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doService(request, response);
    }

//    public void destroy() {
//    }
}

/**
 * jcf = arraylist, hashmap
 * 인터페이스는 객체를 가지려면 클래스화가 되어야함.
 * 인터페이스를 클래스로 만드는 것을 임플리멘테이션(impl)이라고 한다.
 * servlet class가 작동 하려면 웹 컨테이너(돔캣)이 있어야한다.
 * */