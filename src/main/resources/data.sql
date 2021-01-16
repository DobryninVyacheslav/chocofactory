INSERT INTO usr(username,password,active) values ('admin','p', true);
INSERT INTO user_role(user_id, roles) values (1, 'USER'), (1, 'ADMIN');
INSERT INTO usr(username,password,active) values ('user','p', true);
INSERT INTO user_role(user_id, roles) values (2, 'USER');