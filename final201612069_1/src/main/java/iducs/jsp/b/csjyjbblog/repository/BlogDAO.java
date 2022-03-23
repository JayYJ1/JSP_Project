package iducs.jsp.b.csjyjbblog.repository;

import iducs.jsp.b.csjyjbblog.model.Blog;
import iducs.jsp.b.csjyjbblog.util.Pagination;
import java.util.List;

 //crud = Create , Update, Read, Delete
//http method : Post, get, put ,delete
public interface BlogDAO {
    int create(Blog blog);
    Blog read(Blog blog);
    List<Blog> readList();
    int update(Blog blog);
    int delete(Blog blog);

     int readTotalRows();
     List<Blog> readListPagination(Pagination pagination);
}
