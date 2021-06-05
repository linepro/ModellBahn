-- password=$2a$10$2Pk5tdyXwooq0eNbRPZBZ.KtVTARFs/OUfgtQetvtNy1QQylH6EJq
-- Password=$2a$10$tmfRPmnJr/lv6WQFaaXuK.R5XgDcN9z1yAMZ0P.WBKYlETL7SMot6
-- P4ssw0rd=$2a$10$RR2OhFjxhml2zIo4Var3iug3d26j1XOQP7woD/o.mn.nS6dx2PI8O
-- P!55Word=$2a$10$/BsECIXk8fIr78kDj0OPuuKIaigzRu3k5OrlEfEzjw9GnEc9D0CZK
-- IAmAGod=$2a$10$NGttgVx/RJalXDUWmilIou.FucFaH07hwABAESt56ETDIsEHTHani
INSERT INTO person (id, name, password, email, first_name, last_name, enabled, login_attempts, login_failures, password_aging, password_changed, last_login, locale, confirmation_token, confirmation_expires) VALUES (1,  'admin', '$2a$10$NGttgVx/RJalXDUWmilIou.FucFaH07hwABAESt56ETDIsEHTHani', 'admin@modellbahn.com', 'admin', null, true, null, null, null, null, null, 'en_GB', null, null);
INSERT INTO PERSON_ROLES (person_id, roles) VALUES (1, 'ADMIN');
INSERT INTO PERSON_ROLES (person_id, roles) VALUES (1, 'USER');
INSERT INTO person (id, name, password, email, first_name, last_name, enabled, login_attempts, login_failures, password_aging, password_changed, last_login, locale, confirmation_token, confirmation_expires) VALUES (2,  'linepro', '$2a$10$MbNfLBG245kQ01dvWmiyn.RH8bhFJQEwvxM1/MP1fzHBL/unhPv1e', 'linepro@compuserve.com', 'John', 'Goff', true, null, null, null, null, null, 'de_DE', null, null);
INSERT INTO PERSON_ROLES (person_id, roles) VALUES (2, 'USER');
ALTER TABLE person ALTER COLUMN id RESTART WITH 3;
