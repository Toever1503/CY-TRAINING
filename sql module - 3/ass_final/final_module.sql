create database final_sql_module;
use final_sql_module;

drop table users;
create table users(
	id varchar(10) primary key,
	email varchar(100), 
    pwd varchar(255),
    phone varchar(15), 
    address nvarchar(255)
);
insert into users(id, email, pwd, address) values
('101', 'admi@fak.ca', '298327', N'Nghệ An'),
('0FA1', 'akm@vmma.com', '9387151', N'Bắc Ninh'),
('0VM01', 'adl09@kt.com', 'nnchhas', N'Hoàng Mai'),
('AB001', 'ab7@it.com', 'ammcn', N'Cầu Giấy'),
('0A0S1', 'bm68@nbt.com', 'masiqz', N'Thanh Xuân');

#A)
insert into users(id, email, pwd, address) values
('001', 'admin@niit.com', '0123456', N'Hà Nội');

#B) Write statement find this persion in users table
select * from users 
where id = '001';


#C) Write statement change address from Hà Nội to Hải Phòng of this persion
update users
set address = N'Hải Phòng';

#D) Write statement delete this person out of system
delete from users
where id = '001';
