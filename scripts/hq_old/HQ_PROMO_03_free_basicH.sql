delete from PROMO where ID = 10103;

-- ---------------------------------------------------------------
-- Add basic H is the user did not already get this promo
-- ---------------------------------------------------------------

insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) values
(10103, 'HealthQuestionaire Promotion Free Basic H', 'HQ_PROMOS', 'HQ_BASIC_H', 'Free basic H when buying comprehensive bundle', 1000, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 0, 'US');

-- basic H for TIER_3 bundle 

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10103, PROMO_RULESET_SEQ.nextval,
	'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');
	
insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10103, PROMO_RULESET_SEQ.currval,
	'com.shaklee.rulesets.healthQuestionaire.components.DidNotUseHpPromo');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'promo_id', '10300');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10103, PROMO_RULESET_SEQ.currval,
	'com.shaklee.rulesets.healthQuestionaire.components.UserRankSmallerThan');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'rank', '7');
	
insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10103, PROMO_RULESET_SEQ.currval, 'Message');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'key', 'TIER_3_free_basic_H'),
	(PROMO_COMPONENT_SEQ.currval, 'action', 'BUY_BUNDLE'),
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'TIER_3'),
	(PROMO_COMPONENT_SEQ.currval, 'promo_code', 'HPFRP');