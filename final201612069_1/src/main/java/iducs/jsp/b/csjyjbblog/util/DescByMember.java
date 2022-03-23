package iducs.jsp.b.csjyjbblog.util;

import iducs.jsp.b.csjyjbblog.model.Member;
import java.util.Comparator;

public class DescByMember implements Comparator<Member> {
    public int compare(Member o1, Member o2){
        return o2.getEmail().compareTo(o1.getEmail());
    }
}
