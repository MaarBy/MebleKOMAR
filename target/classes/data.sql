insert into authority (id, name) values (1, 'ROLE_ADMIN');
insert into authority (id, name) values (2, 'ROLE_USER');
insert into uzytkownik (email, haslo, is_enabled) values ('admin@wp.pl', '$2a$10$rw1hbKrw2vPP3kDlJtuk2.WkNHvSWq0SPYVwLzqtgVvtj5mOeb1lW', 'true');
insert into user_authority(user_id, authority_id) values (1, 1);