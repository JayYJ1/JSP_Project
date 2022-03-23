create sequence seq_blog201612069 increment by 1 start with 1;

create table blog201612069
(
    id number(11) not null primary key ,
    name varchar2(30) not null,
    email varchar2(30) not null unique,
    title varchar2(50),
    content varchar2(100)
);

drop sequence seq_blog201612069;
drop table  blog201612069;

insert into blog201612069 values(seq_blog201612069.nextval,'전영준','yjjeon@induk.ac.kr','12345678','메시지');

select * from blog201612069;
