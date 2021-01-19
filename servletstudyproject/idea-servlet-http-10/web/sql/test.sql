
drop table if exists t_user_3;
create table t_user_3(
    id int primary key auto_increment,
    usercode varchar(32) not null ,
    username varchar(32) not null
    );
