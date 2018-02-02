delete from PROMO where ID = 10101;

-- ---------------------------------------------------------------
-- Life Shake
-- ---------------------------------------------------------------

insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) values
(10101, 'HealthQuestionaire Promotions', 'HQ_PROMOS', 'HQ_LIFE_SHAKE', 'Life plan promo calculated after the bundles are done', 1000, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'US');

-- Soy dietary restriction ------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10101, PROMO_RULESET_SEQ.nextval,
	'com.shaklee.rulesets.healthQuestionaire.components.Country');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'country_code', 'CA');


insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10101, PROMO_RULESET_SEQ.currval,
	'com.shaklee.rulesets.healthQuestionaire.components.DieraryRestriction');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'diet', 'SOY');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10101, PROMO_RULESET_SEQ.currval,
	'com.shaklee.rulesets.healthQuestionaire.components.PacksContains');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'contains', 'false'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '79351'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '56261'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '56261'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '56264'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '56267'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '79280'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '56278'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '79352'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '79353'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '79354'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '79350'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '56264'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '56278'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '56277');
	
	
insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10101, PROMO_RULESET_SEQ.currval,
	'Message');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'key', 'life_shake_upsell'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '56278'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '56277');

	
-- NO SOY restriction ------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10101, PROMO_RULESET_SEQ.nextval,
	'com.shaklee.rulesets.healthQuestionaire.components.Country');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'country_code', 'CA');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10101, PROMO_RULESET_SEQ.currval,
	'com.shaklee.rulesets.healthQuestionaire.components.DieraryRestriction');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'restriction', 'false'),
	(PROMO_COMPONENT_SEQ.currval, 'diet', 'SOY');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10101, PROMO_RULESET_SEQ.currval,
	'com.shaklee.rulesets.healthQuestionaire.components.PacksContains');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'contains', 'false'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '79351'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '56261'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '56261'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '56264'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '56267'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '79280'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '56278'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '79352'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '79353'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '79354'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '79350'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '56264'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '56278'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '56277');

	
insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10101, PROMO_RULESET_SEQ.currval,
	'Message');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'key', 'life_shake_upsell'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '56261'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '56264'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '56267'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '56270');
