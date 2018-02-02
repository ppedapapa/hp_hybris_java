delete from PROMO where ID = 10035;

insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) values
(10035, 'HealthQuestionaire Canada replacement SKUs', 'MC_HQ', 'HQ_CANADA_SKU', 'HealthQuestionaire Canada replacement SKUs', 948, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'US');

-----------------------
--Canada replacement SKUs
-----------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10035, PROMO_RULESET_SEQ.nextval,
	'com.shaklee.rulesets.healthQuestionaire.components.Country');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'country_code', 'CA');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10035, PROMO_RULESET_SEQ.currval,
	'com.shaklee.rulesets.healthQuestionaire.components.SKUSubstitutions');

-- replacement list

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
(PROMO_COMPONENT_SEQ.currval, 'sku', '20002 57475'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '20076 57860'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '20281 57880'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '20282 57465'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '20284 57570'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '20286 57445'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '20290 57450'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '20608 57280'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '20645 57820'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '20656 57680'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '20962 57690'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '21293 57293'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '21294 57294'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '21501 57501'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '22066 57066'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '22067 57067'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '22076 57315'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '22079 56219'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '89383 79351'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '20095 57600'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '20096 57025'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '20158 54158'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '20283 57460'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '20288 57440'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '20435 57095'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '21309 54309'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '20603 57875'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '20652 57810'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '20732 57815'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '21214 57260'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '21216 57390'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '21217 57400'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '21261 56261'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '21263 56261'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '21264 56264'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '21267 56267'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '22000 56000'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '22031 56031'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '22051 56051'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '22077 57695'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '80638 57760'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '89280 79280'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '20491 57710'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '20495 54495'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '22054 56054'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '20001 57475'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '27007 56278'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '21295 57445'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '21296 57440'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '21298 57450'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '89402 79352'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '89404 79353'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '89403 79354'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '89384 79350'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '21274 56278'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '21275 56278'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '89426 79426'),
(PROMO_COMPONENT_SEQ.currval, 'sku', '89451 79451');