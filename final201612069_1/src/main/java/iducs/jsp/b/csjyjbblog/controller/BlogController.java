package iducs.jsp.b.csjyjbblog.controller;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import iducs.jsp.b.csjyjbblog.model.Blog;
import iducs.jsp.b.csjyjbblog.util.DescByBlogTitle;
import iducs.jsp.b.csjyjbblog.repository.BlogDAOImpl;
import iducs.jsp.b.csjyjbblog.util.Pagination;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

// Controller
// 첫 슬래시는 웹 애플리케이션의 시작위치임
@WebServlet(name = "blogs", urlPatterns = {"/blogs/sort.do","/blogs/test.do","/blogs/post.do", "/blogs/list.do",
        "/blogs/detail.do","/blogs/updateForm.do","/blogs/update.do","/blogs/delete.do","/blogs/post-form.do"}) // urlPatterns : 다수의 url을 기술할 수 있다.
public class BlogController extends HttpServlet {
//    private String message;
//
//    public void init() {
//        message = "Hello World!";
//    }

    public void doService(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");//POST로 보내
        request.setCharacterEncoding("UTF-8");

        /* URI를 문자열에 저장하고, http://..... war/가 contextPath가 됨,
            전체 URI에서 contextPath를 제거한 경로명으로 명령을 구분함.
         */
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = uri.substring(contextPath.length() + 1); // blogs/post.do,  blogs/list.do가 반환됨
        String action = command.substring(command.lastIndexOf("/") + 1); // post.do, get.do가 반환됨
        //System.out.println(uri);
//        // /Gradle___iducs_jsp_b___csjyj_b_blog_1_0_SNAPSHOT_war/blogs/list.do
        //System.out.println(contextPath);
//        // /Gradle___iducs_jsp_b___csjyj_b_blog_1_0_SNAPSHOT_war
        //System.out.println(command);
//        //blogs/list.do
        //System.out.println("command : " + action);

        // 데이터베이스 처리 요청 또는 서비스 요청 코드가 추가
        BlogDAOImpl dao = new BlogDAOImpl();

        if (action.equals("post.do")) {
            Blog blog = new Blog();
            blog.setName(request.getParameter("name"));
            blog.setEmail(request.getParameter("email"));
            blog.setTitle(request.getParameter("title"));
            blog.setContent(request.getParameter("content"));

            request.setAttribute("blog", blog);
            //request.setAttribute("member",member);
            if (dao.create(blog) > 0) { //select * from blogb //dto를 dao에 넘겨준다.
                //request.setAttribute("blog", blog);
                // 처리 결과를 view에 전달한다.
                request.getRequestDispatcher("../main/index.jsp").forward(request, response); // blogs/post.jsp로 포워딩
            } else {
                request.setAttribute("errMsg","블로그등록실패");
                request.getRequestDispatcher("/error.jsp").forward(request, response); // blogs/post.jsp로 포워딩
            }
        }else if(action.equals("post-form.do")){
            request.getRequestDispatcher("../blogs/postForm.jsp").forward(request,response);

        } else if (action.equals("list.do")) {
            ArrayList<Blog> blogList = new ArrayList<Blog>();// 처리결과 한개 이상의 블로그를 저장하는 객체
            String pageNo = request.getParameter("pn");

            //int pageNo1 = Integer.parseInt(request.getParameter("pn"));

            int curPageNo = (pageNo != null)? Integer.parseInt(pageNo):1;
            int perPage = 3;    //한 페이지에 나타나는 행의 수
            int perPagination = 3;  //한 화면에 나타나는 페이지 번호 수
            int totalRows = dao.readTotalRows(); //dao에서 총 행의 수를 질의함
            Pagination pagination = new Pagination(curPageNo, perPage, perPagination, totalRows);
            if ((blogList = (ArrayList<Blog>) dao.readListPagination(pagination)) != null) {
                //한 개 이상의 블로그가 반환, JCF(Java Collecition FrameWork)에 대한 이해
                request.setAttribute("blogList", blogList);//블로그 레코드의 전체를 호출?요청?불러옴?
                request.setAttribute("pagination", pagination);
                request.getRequestDispatcher("../blogs/list.jsp").forward(request, response); //blog/list.jsp로 포워딩
            } else {
                request.setAttribute("errMsg", "블로그 목록 조회 실패");
                request.getRequestDispatcher("error.jsp").forward(request, response); //오류
            }
        } else if (action.equals(("detail.do"))) {
            //?email=이메일  : 쿼리 스트링으로 요청한 경우 이메일 파라미터에 이메일이라는 문자열 값을 전달
            //시스템 아웃 프린트ln request.getParameter("email"));요청에포함된 파라미터 중 email파라미터 값을 접근
            Blog blog = new Blog();
//            blog.setEmail(request.getParameter("email")); 이메일이 유일키였을때
            blog.setId(Long.parseLong(request.getParameter("id"))); //아이디가 유일키  일떄
//            String strId = request.getParameter("id"); //Line 77과 동일
//            long id = Long.parseLong(strId);
//            blog.setId(id); //Line 77과 동일
            Blog retBlog = null;
            HttpSession session = request.getSession();
            if ((retBlog = dao.read(blog)) != null) {
                request.setAttribute("blog", retBlog);
                session.setAttribute("blog","로그인 정보");
                request.getRequestDispatcher("detail.jsp").forward(request, response);
            } else {
                request.setAttribute("errMsg", "블로그 조회 실패");
                request.getRequestDispatcher("eroror.jsp").forward(request, response);//오류
            }
        } else if (action.equals("updateForm.do")) { //디테일에서 클릭하면 넘어옴 //update를 위한 정보 조회후 view에게 전달
            Blog blog = new Blog();
            String strId = request.getParameter("id");
            long id = Long.parseLong(strId);
            blog.setId(id);
            Blog retBlog = null;
            if ((retBlog = dao.read(blog)) != null) {
                request.setAttribute("blog", retBlog);
                request.getRequestDispatcher("updateForm.jsp").forward(request, response);
            } else {
                request.setAttribute("errMsg", "블로그 업데이트를 위한 조회 실패");
                request.getRequestDispatcher("error.jsp").forward(request, response);//오류
            }
        }
        else if(action.equals("update.do")){ //dao에게 업데이트를 요청
            Blog blog = new Blog();
            blog.setId(Long.parseLong(request.getParameter("id")));
            blog.setName(request.getParameter("name"));
            blog.setEmail(request.getParameter("email"));
            blog.setTitle(request.getParameter("title"));
            blog.setContent(request.getParameter("content"));

            if(dao.update(blog) > 0 ){
                request.setAttribute("blog", blog);
                // 처리 결과를 view에 전달한다. error.jsp -> process-error.jsp
                request.getRequestDispatcher("../errors/error.jsp").forward(request, response); // blogs/post.jsp로 포워딩
            } else {
                request.getRequestDispatcher("error.jsp").forward(request, response); // blogs/post.jsp로 포워딩
            }
        }

        else if(action.equals("delete.do")){ //dao에게 업데이트를 요청
            Blog blog = new Blog();
            blog.setId(Long.parseLong(request.getParameter("id")));

            if(dao.delete(blog) > 0 ){
                request.setAttribute("blog", blog);
                request.setAttribute("work","블로그 삭제");
                // 처리 결과를 view에 전달한다. error.jsp -> process-error.jsp
                request.getRequestDispatcher("error.jsp").forward(request, response); // blogs/post.jsp로 포워딩
            } else {
                request.getRequestDispatcher("error.jsp").forward(request, response); // blogs/post.jsp로 포워딩
            }
        }
        else if(action.equals("test.do")){ //test script elements
            ArrayList<Blog> blogList = new ArrayList<Blog>();// 처리결과 한개 이상의 블로그를 저장하는 객체
            if ((blogList = (ArrayList<Blog>) dao.readList()) != null) {
                request.setAttribute("blogList", blogList);//블로그 레코드의 전체를 호출?요청?불러옴?
                request.getRequestDispatcher("test.jsp").forward(request, response); //blog/list.jsp로 포워딩
            } else {
                request.setAttribute("errMsg", "블로그 목록 조회 실패");
                request.getRequestDispatcher("error.jsp").forward(request, response); //오류
            }
        }
        else if (action.equals("sort.do")) {
            ArrayList<Blog> blogList = new ArrayList<Blog>();   // 처리결과 한개 이상의 블로그를 저장하는 객체

            String properties = request.getParameter("by");
            if ((blogList = (ArrayList<Blog>) dao.readList()) != null)
            {   //한 개 이상의 블로그가 반환 JCF(Java Collection Framework)에 대한 이해
                if (properties.equals("desc,title"))
                    Collections.sort(blogList, new DescByBlogTitle());
                request.setAttribute("blogList", blogList);
                request.getRequestDispatcher("../blogs/list.jsp").forward(request, response); // blogs/list.jsp로 포워딩
            }else
            {
                request.setAttribute("errMsg", "블로그 목록 조회 실패" );
                request.getRequestDispatcher("../errors/error.jsp").forward(request, response); // blogs/list.jsp로 포워딩
            }

        }
        /*
        String inputName = request.getParameter("name");
        String inputEmail = request.getParameter("email");
        String phone = request.getParameter("phone");

        Blog blog = new Blog();
        blog.setName(inputName);
        blog.setEmail(inputEmail);
        blog.setPhone(phone);
        blog.setMessage(request.getParameter("message"));

        request.setAttribute("list", blog);

        request.getRequestDispatcher("postView.jsp").forward(request, response);
        */

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