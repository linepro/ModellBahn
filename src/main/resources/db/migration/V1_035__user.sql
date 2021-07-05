INSERT INTO person (id, name, password, email, first_name, last_name, enabled, login_attempts, login_failures, password_aging, password_changed, last_login, locale, confirmation_token, confirmation_expires) VALUES (1,  'admin', '$2a$10$qMxTrsr2sJsGtk5gY2DntuffR2iO5lfW1r99v3btdpWwJaDo82x0G', 'admin@modellbahn.com', 'admin', null, true, null, null, null, null, null, 'en_GB', null, null);
INSERT INTO PERSON_ROLES (person_id, roles) VALUES (1, 'ADMIN');
INSERT INTO PERSON_ROLES (person_id, roles) VALUES (1, 'USER');
INSERT INTO person (id, name, password, email, first_name, last_name, enabled, login_attempts, login_failures, password_aging, password_changed, last_login, locale, confirmation_token, confirmation_expires) VALUES (2,  'linepro', '$2a$10$MbNfLBG245kQ01dvWmiyn.RH8bhFJQEwvxM1/MP1fzHBL/unhPv1e', 'linepro@compuserve.com', 'John', 'Goff', true, null, null, null, null, null, 'de_DE', null, null);
INSERT INTO PERSON_ROLES (person_id, roles) VALUES (2, 'USER');
ALTER TABLE person ALTER COLUMN id RESTART WITH 3;
