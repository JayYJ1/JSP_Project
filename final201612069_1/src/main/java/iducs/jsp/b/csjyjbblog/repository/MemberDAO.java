package iducs.jsp.b.csjyjbblog.repository;

import iducs.jsp.b.csjyjbblog.model.Member;
import iducs.jsp.b.csjyjbblog.util.Pagination;
import java.util.List;

//crud = Create , Update, Read, Delete
//http method : Post, get, put ,delete
public interface MemberDAO {
    int create(Member member);
    Member read(Member member);
    List<Member> readList();
    int update(Member member);
    int delete(Member member);

    int readTotalRows();
    List<Member> readListPagination(Pagination pagination);
}
