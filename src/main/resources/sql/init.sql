--Инициализация начального набора данных

-- Категории клиентов банка

INSERT INTO client_categories(id, name) VALUES(1,'Физические лица');
INSERT INTO client_categories(id, name) VALUES(2,'Юридические лица');

-- Группы клиентов банка

INSERT INTO client_groups(id, name) VALUES(1,'Новички');
INSERT INTO client_groups(id, name) VALUES(2,'Постоянные');
INSERT INTO client_groups(id, name) VALUES(3,'Бывшие');

INSERT INTO client_categories_client_groups(client_categories_id, client_groups_id) VALUES(1,1);
INSERT INTO client_categories_client_groups(client_categories_id, client_groups_id) VALUES(1,2);
INSERT INTO client_categories_client_groups(client_categories_id, client_groups_id) VALUES(1,3);
INSERT INTO client_categories_client_groups(client_categories_id, client_groups_id) VALUES(2,1);
INSERT INTO client_categories_client_groups(client_categories_id, client_groups_id) VALUES(2,2);
INSERT INTO client_categories_client_groups(client_categories_id, client_groups_id) VALUES(2,3);

-- Услуги банка

INSERT INTO services(id, name) VALUES(1,'Общие критерии');
INSERT INTO services(id, name) VALUES(2,'Сайт');
INSERT INTO services(id, name) VALUES(3,'Онлайн-обслуживание');
INSERT INTO services(id, name) VALUES(4,'VIP-обслуживание');
INSERT INTO services(id, name) VALUES(5,'Пластиковые карты');
INSERT INTO services(id, name) VALUES(6,'Депозиты');
INSERT INTO services(id, name) VALUES(7,'Кредитование');
INSERT INTO services(id, name) VALUES(8,'Монеты');
INSERT INTO services(id, name) VALUES(9,'Денежные переводы');
INSERT INTO services(id, name) VALUES(10,'Банковские сейфы');
INSERT INTO services(id, name) VALUES(11,'Дорожные чеки');
INSERT INTO services(id, name) VALUES(12,'Брокерское обслуживание');
INSERT INTO services(id, name) VALUES(13,'Инкассация');
INSERT INTO services(id, name) VALUES(14,'Зарплатный проект');
INSERT INTO services(id, name) VALUES(15,'Лизинг');

-- Предназначение услуг для категорий клиентов

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

-- Критерии оценивания услуг

INSERT INTO service_quality_criterias(id, name) VALUES(1, 'удобство режима работы подразделений банка');
INSERT INTO service_quality_criterias(id, name) VALUES(2, 'скорость обслуживания специалистом одного клиента');
INSERT INTO service_quality_criterias(id, name) VALUES(3, 'общая атмосфера в банке');
INSERT INTO service_quality_criterias(id, name) VALUES(4, 'проблема очередей в банке');
INSERT INTO service_quality_criterias(id, name) VALUES(5, 'заинтересованность сотрудников банка решить проблему клиента');
INSERT INTO service_quality_criterias(id, name) VALUES(6, 'удобство самостоятельного получения информации');
INSERT INTO service_quality_criterias(id, name) VALUES(7, 'понятность и полнота предоставляемой информации');
INSERT INTO service_quality_criterias(id, name) VALUES(8, 'легкость доступа в отделение');
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(1, 1);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(1, 2);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(1, 3);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(1, 4);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(1, 5);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(1, 6);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(1, 7);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(1, 8);

INSERT INTO service_quality_criterias(id, name) VALUES(9, 'дизайн');
INSERT INTO service_quality_criterias(id, name) VALUES(10, 'навигация (удобство в использовании, организация информации и т.п.)');
INSERT INTO service_quality_criterias(id, name) VALUES(11, 'содержание сайта (наличие всей необходимой информации, понятност изложенной информации и т.п.)');
INSERT INTO service_quality_criterias(id, name) VALUES(12, 'функциональность (быстрота загрузки страницы, отсутствие сбоев в работе сайта и т.п.);');
INSERT INTO service_quality_criterias(id, name) VALUES(13, 'интерактивность (предлагаемые сайтом возможности – ведение диалога, двухсторонний обмен информацией и т.п.)');
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(2, 9);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(2, 10);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(2, 11);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(2, 12);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(2, 13);

INSERT INTO service_quality_criterias(id, name) VALUES(14, 'стоимость использования онлайн-услуг');
INSERT INTO service_quality_criterias(id, name) VALUES(15, 'спектр предоставляемых онлайн-услуг');
INSERT INTO service_quality_criterias(id, name) VALUES(16, 'удобство использования онлайн-обслуживания');
INSERT INTO service_quality_criterias(id, name) VALUES(17, 'безопасность и конфиденциальность доступа к онлайн-услугам');
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(3, 14);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(3, 15);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(3, 16);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(3, 17);

INSERT INTO service_quality_criterias(id, name) VALUES(18, 'организация VIP-зоны (комфорт, дизайн и т.п.)');
INSERT INTO service_quality_criterias(id, name) VALUES(19, 'ассортимент специальных банковских программ и продуктов для VIP-клиентов');
INSERT INTO service_quality_criterias(id, name) VALUES(20, 'оценка обслуживания персонального менеджера');
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(4, 18);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(4, 19);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(4, 20);

INSERT INTO service_quality_criterias(id, name) VALUES(21, 'удобство в получении;');
INSERT INTO service_quality_criterias(id, name) VALUES(22, 'наличие банкоматов');
INSERT INTO service_quality_criterias(id, name) VALUES(23, 'удобство в пользовании банкоматом');
INSERT INTO service_quality_criterias(id, name) VALUES(24, 'дизайн пластиковой карточки');
INSERT INTO service_quality_criterias(id, name) VALUES(25, 'стоимость смс-сервиса');
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(5, 21);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(5, 22);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(5, 23);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(5, 24);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(5, 25);

INSERT INTO service_quality_criterias(id, name) VALUES(26, 'процентная ставка');
INSERT INTO service_quality_criterias(id, name) VALUES(27, 'сроки депозитов');
INSERT INTO service_quality_criterias(id, name) VALUES(28, 'минимальная сумма вклада');
INSERT INTO service_quality_criterias(id, name) VALUES(29, 'максимальная сумма вклада');
INSERT INTO service_quality_criterias(id, name) VALUES(30, 'опции вклада');
INSERT INTO service_quality_criterias(id, name) VALUES(31, 'доступность понимания договоров');
INSERT INTO service_quality_criterias(id, name) VALUES(32, 'денежная потеря при досрочном снятии всей суммы вклада');
INSERT INTO service_quality_criterias(id, name) VALUES(33, 'ассортимент депозитов');
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(6, 26);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(6, 27);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(6, 28);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(6, 29);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(6, 30);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(6, 31);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(6, 32);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(6, 33);

INSERT INTO service_quality_criterias(id, name) VALUES(35, 'сроки кредитования');
INSERT INTO service_quality_criterias(id, name) VALUES(36, 'минимальная сумма кредита');
INSERT INTO service_quality_criterias(id, name) VALUES(37, 'максимальная сумма кредита');
INSERT INTO service_quality_criterias(id, name) VALUES(38, 'скорость рассмотрения кредитной заявки');
INSERT INTO service_quality_criterias(id, name) VALUES(39, 'величина комплекта документов, необходимого для предоставления кредита');
INSERT INTO service_quality_criterias(id, name) VALUES(40, 'доступность понимания содержания кредитных договоров');
INSERT INTO service_quality_criterias(id, name) VALUES(41, 'ассортимент кредитов');
INSERT INTO service_quality_criterias(id, name) VALUES(42, 'отношение банка к клиенту в случае задолженности или просрочки оплаты по кредитному договору');
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(7, 26);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(7, 35);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(7, 36);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(7, 37);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(7, 38);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(7, 39);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(7, 40);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(7, 41);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(7, 42);

INSERT INTO service_quality_criterias(id, name) VALUES(43, 'художественное оформление');
INSERT INTO service_quality_criterias(id, name) VALUES(44, 'стоимость');
INSERT INTO service_quality_criterias(id, name) VALUES(45, 'ассортимент');
INSERT INTO service_quality_criterias(id, name) VALUES(46, 'разница между ценой покупки и продажи');
INSERT INTO service_quality_criterias(id, name) VALUES(47, 'комиссия при операциях с монетами');
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(8, 43);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(8, 44);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(8, 45);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(8, 46);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(8, 47);

INSERT INTO service_quality_criterias(id, name) VALUES(48, 'стоимость переводов');
INSERT INTO service_quality_criterias(id, name) VALUES(49, 'скорость доставки денежных средств');
INSERT INTO service_quality_criterias(id, name) VALUES(50, 'максимально возможная сумма перевода');
INSERT INTO service_quality_criterias(id, name) VALUES(51, 'удобство расположения пунктов приема и получения денежных средств');
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(9, 48);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(9, 49);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(9, 50);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(9, 51);

INSERT INTO service_quality_criterias(id, name) VALUES(52, 'количество документов для оформления договора');
INSERT INTO service_quality_criterias(id, name) VALUES(53, 'стоимость услуги за сутки аренды');
INSERT INTO service_quality_criterias(id, name) VALUES(54, 'доступ к ячейке');
INSERT INTO service_quality_criterias(id, name) VALUES(55, 'удобство пользования ячейкой');
INSERT INTO service_quality_criterias(id, name) VALUES(56, 'количество подразделений банка, в которых имеется данная услуга');
INSERT INTO service_quality_criterias(id, name) VALUES(57, 'доступность понимания договора аренды и правил пользования сейфами');
INSERT INTO service_quality_criterias(id, name) VALUES(58, 'стоимость оплаты потери ключа или выхода из строя замка сейфа');
INSERT INTO service_quality_criterias(id, name) VALUES(59, 'отношение банка к клиенту в случае задолженности или просрочки оплаты аренды ячейки');
INSERT INTO service_quality_criterias(id, name) VALUES(60, 'надежность хранилища (страховка от мошенничества персонала банка, видеонаблюдение и т.п.)');
INSERT INTO service_quality_criterias(id, name) VALUES(61, 'размер штрафа в случае неосвобождения ячейки сразу после даты окончания действия договора аренды');
INSERT INTO service_quality_criterias(id, name) VALUES(62, 'физические параметры ячейки (температура, влажность и т.п.)');
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(10, 52);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(10, 53);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(10, 54);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(10, 55);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(10, 56);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(10, 57);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(10, 58);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(10, 59);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(10, 60);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(10, 61);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(10, 62);

INSERT INTO service_quality_criterias(id, name) VALUES(63, 'комиссия за покупку чека');
INSERT INTO service_quality_criterias(id, name) VALUES(64, 'комиссия за продажу чека');
INSERT INTO service_quality_criterias(id, name) VALUES(65, 'минимальный номинал дорожного чека');
INSERT INTO service_quality_criterias(id, name) VALUES(66, 'наличие подразделений, осуществляющих продажу дорожных чеков');
INSERT INTO service_quality_criterias(id, name) VALUES(67, 'предлагаемый ассортимент данного товара');
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(11, 63);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(11, 64);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(11, 65);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(11, 66);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(11, 67);

INSERT INTO service_quality_criterias(id, name) VALUES(68, 'комиссия за оборот по сделкам');
INSERT INTO service_quality_criterias(id, name) VALUES(69, 'минимальная сумма для открытия брокерского счета');
INSERT INTO service_quality_criterias(id, name) VALUES(70, 'информационная и аналитическая поддержка клиента');
INSERT INTO service_quality_criterias(id, name) VALUES(71, 'предоставление отчетов и всей информации клиентам о совершенных сделках и состоянии активов');
INSERT INTO service_quality_criterias(id, name) VALUES(72, 'наличие различных вариантов связи с брокером');
INSERT INTO service_quality_criterias(id, name) VALUES(73, 'открытость и прозрачность банка');
INSERT INTO service_quality_criterias(id, name) VALUES(74, 'персональное обеспечение для вывода клиентских заявок на рынок');
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(12, 68);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(12, 69);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(12, 70);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(12, 71);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(12, 72);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(12, 73);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(12, 74);

INSERT INTO service_quality_criterias(id, name) VALUES(75, 'стоимость услуги');
INSERT INTO service_quality_criterias(id, name) VALUES(76, 'перечень ценностей, которые можно перевозить');
INSERT INTO service_quality_criterias(id, name) VALUES(77, 'расстояние, на которое можно перевозить груз');
INSERT INTO service_quality_criterias(id, name) VALUES(78, 'удобство графика работы службы инкассации');
INSERT INTO service_quality_criterias(id, name) VALUES(79, 'уровень качества машин и водителей');
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(13, 75);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(13, 76);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(13, 77);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(13, 78);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(13, 79);

INSERT INTO service_quality_criterias(id, name) VALUES(80, 'стоимость годового обслуживания банковской карты');
INSERT INTO service_quality_criterias(id, name) VALUES(81, 'комиссия за начисление заработной платы на карту');
INSERT INTO service_quality_criterias(id, name) VALUES(82, 'стоимость выпуска пластиковых карт');
INSERT INTO service_quality_criterias(id, name) VALUES(83, 'скорость зачисления заработной платы на карточки сотрудников');
INSERT INTO service_quality_criterias(id, name) VALUES(84, 'наличие банкоматов вблизи расположения организации');
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(14, 80);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(14, 81);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(14, 82);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(14, 83);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(14, 84);

INSERT INTO service_quality_criterias(id, name) VALUES(85, 'величина первоначального взноса');
INSERT INTO service_quality_criterias(id, name) VALUES(86, 'сроки кредитного договора по лизингу');
INSERT INTO service_quality_criterias(id, name) VALUES(88, 'стоимость дополнительных затрат (оформление сделки у нотариуса и т.п.)');
INSERT INTO service_quality_criterias(id, name) VALUES(89, 'скорость рассмотрения заявки');
INSERT INTO service_quality_criterias(id, name) VALUES(90, 'величина комплекта документов, необходимого для предоставления лизинга');
INSERT INTO service_quality_criterias(id, name) VALUES(91, 'доступность понимания содержания кредитных договоров по лизингу');
INSERT INTO service_quality_criterias(id, name) VALUES(92, 'требования к бизнесу (финансовому положению) заемщика');
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(15, 85);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(15, 86);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(15, 26);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(15, 88);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(15, 89);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(15, 90);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(15, 91);
INSERT INTO services_service_quality_criterias(services_id, service_quality_criterias_id) VALUES(15, 92);

-- Лингвистическая шкала

INSERT INTO linguistic_terms(id, name) VALUES(1,'очень низкое');
INSERT INTO linguistic_terms(id, name) VALUES(2,'низкое');
INSERT INTO linguistic_terms(id, name) VALUES(3,'среднее');
INSERT INTO linguistic_terms(id, name) VALUES(4,'высокое');
INSERT INTO linguistic_terms(id, name) VALUES(5,'очень высокое');

-- Политики округления при расчёте оценки качества

INSERT INTO esqrouding_policies(id, name) VALUES(1, 'мягкая');
INSERT INTO esqrouding_policies(id, name) VALUES(2, 'нейтральная');
INSERT INTO esqrouding_policies(id, name) VALUES(3, 'жесткая');

-- Профиль с настройками расчёта

INSERT INTO esqsettings_profiles(
            id, name, esq_rouding_policy_id)
    VALUES (1, 'test', 1);

INSERT INTO esqclient_category_importances(
            client_category_id, esq_settings_profile_id, client_category_importance_id)
    VALUES (1, 1, 3);

INSERT INTO esqclient_category_importances(
            client_category_id, esq_settings_profile_id, client_category_importance_id)
    VALUES (2, 1, 4);

INSERT INTO esqclient_group_importances(
            client_group_id, client_category_id, esq_settings_profile_id,
            client_group_importance_id)
    VALUES (1, 1, 1,
            2);

INSERT INTO esqclient_group_importances(
            client_group_id, client_category_id, esq_settings_profile_id,
            client_group_importance_id)
    VALUES (2, 1, 1,
            3);

INSERT INTO esqclient_group_importances(
            client_group_id, client_category_id, esq_settings_profile_id,
            client_group_importance_id)
    VALUES (3, 1, 1,
            1);

INSERT INTO esqclient_group_importances(
            client_group_id, client_category_id, esq_settings_profile_id,
            client_group_importance_id)
    VALUES (1, 2, 1,
            4);

INSERT INTO esqclient_group_importances(
            client_group_id, client_category_id, esq_settings_profile_id,
            client_group_importance_id)
    VALUES (2, 2, 1,
            5);

INSERT INTO esqclient_group_importances(
            client_group_id, client_category_id, esq_settings_profile_id,
            client_group_importance_id)
    VALUES (3, 2, 1,
            1);




