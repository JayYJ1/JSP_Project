package iducs.jsp.b.csjyjbblog.util;

import iducs.jsp.b.csjyjbblog.model.Blog;
import java.util.Comparator;
public class DescByBlogTitle implements Comparator<Blog> {
    public int compare(Blog o1, Blog o2) {
        return o2.getTitle().compareTo(o1.getTitle()); // o2 <= o1 : 음수 / o2 > o1 : 1 이상 내림차순
        //return o1.getTitle().compareTo(o2.getTitle()); // 오름차순
    }
}
