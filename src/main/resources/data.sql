INSERT INTO request(ingredients, quantity, date) values ('Орехи', 1000, CURRENT_TIMESTAMP);
INSERT INTO usr(username,password,active) values ('admin','p', true);
INSERT INTO user_role(user_id, roles) values (1, 'ADMIN')