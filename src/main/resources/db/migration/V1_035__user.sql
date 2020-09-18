-- $2y$10$GulJj02j0ljy1gY2HBL52esizNcv1xOoreHv9Peu4b8RRuac.tZTa = Password
INSERT INTO person (id, name, password, email, first_name, last_name, enabled, login_attempts, login_failures, password_aging, password_changed, last_login, locale, confirmation_token, reset_token) VALUES (1,  'admin', '$2y$10$GulJj02j0ljy1gY2HBL52esizNcv1xOoreHv9Peu4b8RRuac.tZTa', 'admin@modellbahn.com', 'admin', null, true, null, null, null, null, null, 'en_GB', null, null);
INSERT INTO PERSON_ROLES (person_id, roles) VALUES (1, 'ADMIN');
INSERT INTO PERSON_ROLES (person_id, roles) VALUES (1, 'USER');
INSERT INTO person (id, name, password, email, first_name, last_name, enabled, login_attempts, login_failures, password_aging, password_changed, last_login, locale, confirmation_token, reset_token) VALUES (2,  'johng', '$2y$10$GulJj02j0ljy1gY2HBL52esizNcv1xOoreHv9Peu4b8RRuac.tZTa', 'linepro@compuserve.com', 'John', 'Goff', true, null, null, null, null, null, 'de_DE', null, null);
INSERT INTO PERSON_ROLES (person_id, roles) VALUES (2, 'USER');
ALTER TABLE person ALTER COLUMN id RESTART WITH 3;
