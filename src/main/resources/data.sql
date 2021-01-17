INSERT INTO usr(username, password, active)
values ('admin', 'p', true);
INSERT INTO user_role(user_id, roles)
values (1, 'USER'),
       (1, 'ADMIN');
INSERT INTO usr(username, password, active)
values ('user', 'p', true);
INSERT INTO user_role(user_id, roles)
values (2, 'USER');
INSERT INTO batch(chocolate_temperature, chocolate_stirring_speed, chocolate_serving_size,
                  cream_whipping_time, cream_whipping_speed, filler_type, filler_consistency,
                  filler_weight, nuts_weight, nuts_grinding_type, packaging_type, creation_date, formed)
values (42, 5, 90, 10, 50, 'Черника', 'Жидкая', 5, 10, 'Очень мелко', 'Картон', '2021-01-15', true),
       (42, 5, 90, 10, 50, 'Черника', 'Жидкая', 5, 10, 'Очень мелко', 'Картон', '2021-01-16', true),
       (42, 5, 90, 10, 50, 'Черника', 'Жидкая', 5, 10, 'Очень мелко', 'Картон', '2021-01-16', true),
       (42, 5, 90, 10, 50, 'Черника', 'Жидкая', 5, 10, 'Очень мелко', 'Картон', '2021-01-16', true),
       (42, 5, 90, 10, 50, 'Черника', 'Жидкая', 5, 10, 'Очень мелко', 'Картон', NULL, false);