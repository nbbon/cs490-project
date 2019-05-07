SELECT * FROM mstore_db.role;

insert into mstore_db.role values (1, "ADMIN"); 
insert into mstore_db.role values (2, "CUSTOMER"); 
insert into mstore_db.role values (3, "VENDOR"); 
insert into mstore_db.role values (4, "GUEST"); 
insert into mstore_db.role values (5, "SUPER_ADMIN"); 

-- create schema  mstore_db;

-- For Super Admin
-- use mstore_db;
-- update user_roles set role_id = 5 where user_id=2;
-- update profile set enable = true where id = 1;
-- update user set enabled = true where id = 2;




-- vendor name should be in product 


-- Vendor approval - to fix