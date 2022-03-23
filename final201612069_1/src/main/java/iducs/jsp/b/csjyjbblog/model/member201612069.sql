create sequence seq_member201612069 increment by 1 start with 1;

create table blog201612069
(
    id number(11) not null primary key ,
    email varchar2(30) not null unique,
    pw  varchar2(20) not null,
    name varchar2(30) not null,
    phone varchar2(50),
    adress varchar2(100)
);

drop sequence seq_member201612069;
drop table  member201612069;

insert into member201612069 values(seq_member201612069.nextval,'yjjeon@induk.ac.kr','cometrue','YJ','12345678','주소맨');

select * from member201612069;
