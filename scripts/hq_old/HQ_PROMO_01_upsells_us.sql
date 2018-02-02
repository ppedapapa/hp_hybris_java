delete from PROMO where ID = 10100;

-- ---------------------------------------------------------------
-- Life Shake
-- ---------------------------------------------------------------

insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) values
(10100, 'HealthQuestionaire Promotions', 'HQ_PROMOS', 'HQ_LIFE_SHAKE', 'Life plan promo calculated after the bundles are done', 1000, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'US');

-- Soy dietary restriction ------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10100, PROMO_RULESET_SEQ.nextval,
	'com.shaklee.rulesets.healthQuestionaire.components.Country');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'country_code', 'US');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10100, PROMO_RULESET_SEQ.currval,
	'com.shaklee.rulesets.healthQuestionaire.components.DieraryRestriction');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'diet', 'SOY');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10100, PROMO_RULESET_SEQ.currval,
	'com.shaklee.rulesets.healthQuestionaire.components.PacksContains');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'contains', 'false'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '89383'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21261'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21263'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21264'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21267'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '89280'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '27007'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '89402'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '89404'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '89403'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '89384'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21274'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21275'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21276'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21277');
	
insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10100, PROMO_RULESET_SEQ.currval,
	'Message');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'key', 'life_shake_upsell'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21275'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21277');

	
-- NO SOY restriction ------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10100, PROMO_RULESET_SEQ.nextval,
	'com.shaklee.rulesets.healthQuestionaire.components.Country');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'country_code', 'US');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10100, PROMO_RULESET_SEQ.currval,
	'com.shaklee.rulesets.healthQuestionaire.components.DieraryRestriction');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'restriction', 'false'),
	(PROMO_COMPONENT_SEQ.currval, 'diet', 'SOY');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10100, PROMO_RULESET_SEQ.currval,
	'com.shaklee.rulesets.healthQuestionaire.components.PacksContains');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'contains', 'false'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '89383'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21261'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21263'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21264'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21267'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '89280'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '27007'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '89402'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '89404'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '89403'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '89384'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21274'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21275'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21276'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21277');
	
insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10100, PROMO_RULESET_SEQ.currval,
	'Message');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'key', 'life_shake_upsell'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21261'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21264'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21267'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21270');
