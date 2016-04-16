--Инициализация начального набора данных

DELETE * FROM client_categories;
INSERT INTO client_categories(id, name) VALUES(1,'Физические лица');
INSERT INTO client_categories(id, name) VALUES(2,'Юридические лица');

DELETE * FROM client_groups;
INSERT INTO client_groups(id, name) VALUES(1,'Новички');
INSERT INTO client_groups(id, name) VALUES(2,'Постоянные');
INSERT INTO client_groups(id, name) VALUES(3,'Бывшие');

DELETE * FROM client_categories_client_groups;
INSERT INTO client_categories_client_groups(client_categories_id, client_groups_id) VALUES(1,1);
INSERT INTO client_categories_client_groups(client_categories_id, client_groups_id) VALUES(1,2);
INSERT INTO client_categories_client_groups(client_categories_id, client_groups_id) VALUES(1,3);
INSERT INTO client_categories_client_groups(client_categories_id, client_groups_id) VALUES(2,1);
INSERT INTO client_categories_client_groups(client_categories_id, client_groups_id) VALUES(2,2);
INSERT INTO client_categories_client_groups(client_categories_id, client_groups_id) VALUES(2,3);

DELETE * FROM service;
INSERT INTO services(id, name) VALUES(1,'Общие критерии');
INSERT INTO services(id, name) VALUES(2,'Сайт');
INSERT INTO services(id, name) VALUES(3,'Онлайн-обслуживание');
INSERT INTO services(id, name) VALUES(4,'VIP-обслуживание');
INSERT INTO services(id, name) VALUES(5,'Пластиковые карты');
INSERT INTO services(id, name) VALUES(6,'Депозиты');
INSERT INTO services(id, name) VALUES(7,'Кредитование');
INSERT INTO services(id, name) VALUES(8,'Монеты');
INSERT INTO services(id, name) VALUES(9,'Банковские сейфы');
INSERT INTO services(id, name) VALUES(10,'Дорожные чеки');
INSERT INTO services(id, name) VALUES(11,'Брокерское обслуживание');
INSERT INTO services(id, name) VALUES(12,'Инкассация');
INSERT INTO services(id, name) VALUES(13,'Зарплатный проект');
INSERT INTO services(id, name) VALUES(14,'Лизинг');

DELETE * FROM services_client_categories_for;
INSERT INTO services_client_categories_for(services_id, client_categories_for_id) VALUES(1,1);
INSERT INTO services_client_categories_for(services_id, client_categories_for_id) VALUES(2,1);
INSERT INTO services_client_categories_for(services_id, client_categories_for_id) VALUES(3,1);
INSERT INTO services_client_categories_for(services_id, client_categories_for_id) VALUES(4,1);
INSERT INTO services_client_categories_for(services_id, client_categories_for_id) VALUES(5,1);
INSERT INTO services_client_categories_for(services_id, client_categories_for_id) VALUES(6,1);
INSERT INTO services_client_categories_for(services_id, client_categories_for_id) VALUES(7,1);
INSERT INTO services_client_categories_for(services_id, client_categories_for_id) VALUES(8,1);
INSERT INTO services_client_categories_for(services_id, client_categories_for_id) VALUES(9,1);
INSERT INTO services_client_categories_for(services_id, client_categories_for_id) VALUES(10,1);
INSERT INTO services_client_categories_for(services_id, client_categories_for_id) VALUES(11,1);
INSERT INTO services_client_categories_for(services_id, client_categories_for_id) VALUES(1,2);
INSERT INTO services_client_categories_for(services_id, client_categories_for_id) VALUES(2,2);
INSERT INTO services_client_categories_for(services_id, client_categories_for_id) VALUES(3,2);
INSERT INTO services_client_categories_for(services_id, client_categories_for_id) VALUES(4,2);
INSERT INTO services_client_categories_for(services_id, client_categories_for_id) VALUES(7,2);
INSERT INTO services_client_categories_for(services_id, client_categories_for_id) VALUES(12,2);
INSERT INTO services_client_categories_for(services_id, client_categories_for_id) VALUES(13,2);
INSERT INTO services_client_categories_for(services_id, client_categories_for_id) VALUES(14,2);

DELETE * FROM linguistic_terms;
INSERT INTO linguistic_terms(id, name, value) VALUES(1,'очень низкое', 1);
INSERT INTO linguistic_terms(id, name, value) VALUES(2,'низкое', 2);
INSERT INTO linguistic_terms(id, name, value) VALUES(3,'среднее', 3);
INSERT INTO linguistic_terms(id, name, value) VALUES(4,'высокое', 4);
INSERT INTO linguistic_terms(id, name, value) VALUES(5,'очень высокое', 5);
