SELECT * FROM mstore_db.role;

insert into mstore_db.role values (1, "ADMIN"); 
insert into mstore_db.role values (2, "CUSTOMER"); 
insert into mstore_db.role values (3, "VENDOR"); 
insert into mstore_db.role values (4, "GUEST"); 
insert into mstore_db.role values (5, "SUPER_ADMIN"); 

-- create schema  mstore_db;

insert into user values(1, true, '$2a$10$/HVu3dYy.gMMrGeC4yp4huJjrAloLPLXvF6j62ixZsmx9btNQajlW', 'yeemonzaw@outlook.com');
insert into user_roles values (1, 5);
insert into profile values('SUPER_ADMIN', 1, 'yeemonzaw@outlook.com', 1, 'Super Admin', 'User', 1234567890, 1, null, null, null, null, null, null, null);

insert into user values(2, true, '$2a$10$/HVu3dYy.gMMrGeC4yp4huJjrAloLPLXvF6j62ixZsmx9btNQajlW', 'ymzaw@mum.edu');
insert into user_roles values (2, 1);
insert into profile values('ADMIN', 2, 'ymzaw@mum.edu', 1, 'Admin', 'User', 1234567891, 1, null, null, null, null, null, null, null);

