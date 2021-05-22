-- user
create table user(user_id varchar(8), user_name varchar(12),user_idcard varchar(18),user_password varchar(8),user_phone varchar(11), primary key(user_id));
-- add user_resign_datatime
alter table user add user_resign_datatime date;