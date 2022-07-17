insert into app_users(id, email, password, piu_nickname)
values(1, 'bifos@dev.com', 'bifos', 'BIFOS');

insert into app_users(id, email, password, piu_nickname)
values(2, 'admin@dev.com', 'admin', 'ADMIN');

insert into role(id, name) values (1, 'ROLE_USER');
insert into role(id, name) values (2, 'ROLE_ADMIN');

insert into user_role(user_id, role_id) values(1, 1);

insert into user_role(user_id, role_id) values(2, 1);
insert into user_role(user_id, role_id) values(2, 2);