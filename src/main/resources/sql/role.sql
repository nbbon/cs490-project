use mstore_db;

insert into role values (1, "ADMIN"); 
insert into role values (2, "CUSTOMER"); 
insert into role values (3, "VENDOR"); 
insert into role values (4, "GUEST"); 
insert into role values (5, "SUPER_ADMIN"); 

insert into user values(1234567890, true, '$2a$10$/HVu3dYy.gMMrGeC4yp4huJjrAloLPLXvF6j62ixZsmx9btNQajlW', 'yeemonzaw@outlook.com');
insert into user_roles values (1234567890, 5);
insert into profile values('SUPER_ADMIN', 1234567890, 'yeemonzaw@outlook.com', 1, 'Super Admin', 'User', 1234567890, 1, null, null, null, null, null, null, null);
-- 
insert into user values(1234567891, true, '$2a$10$/HVu3dYy.gMMrGeC4yp4huJjrAloLPLXvF6j62ixZsmx9btNQajlW', 'ymzaw@mum.edu');
insert into user_roles values (1234567891, 1);
insert into profile values('ADMIN', 1234567891, 'ymzaw@mum.edu', 1, 'Admin', 'User', 1234567891, 1, null, null, null, null, null, null, null);

drop table if exists orders;
create table orders (order_number varchar(255) not null, order_date date, status varchar(255), address_id1 integer, credit_card_id integer, customer_id bigint, address_id2 integer, primary key (order_number));
