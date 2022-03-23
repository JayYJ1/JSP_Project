package iducs.jsp.b.csjyjbblog.model;

import java.util.Objects;

public class Member { // Model 객체 : dto(데이터 전송 객체), vo객체
    //객체 정의 방식 : Beans, POJO(Plain Old Java Object)
    private  long id;
    private String pw;
    private String email; //이메일이 유니크가 되면 이메일당 블로그를 1개 밖에 못쓴다.
    private String name;
    private String phone;
    private String address;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return id == member.id && email.equals(member.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", pw='" + pw + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
/**
 * equals : 2개의 클래스의 객체가 같은 객체인지 비교하기 위함 (generate) -> 같음의 비교를 정할 수 있음
 *  문자열에서는 값이 같으면 같다, 객체를 비교할때는 해시코드가 같으면 같다고 함
 *
 * */
