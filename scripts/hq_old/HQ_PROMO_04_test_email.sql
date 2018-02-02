delete from PROMO where ID = 10105;

-- ---------------------------------------------------------------
-- Test promotion for guest flow and email
-- ---------------------------------------------------------------

insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) values
(10105, 'Health Print EMAIL Promotion', 'HQ_PROMOS', 'HP_EMAIL_PROMOTION', 'EMAIL PROMOTION when buying comprehensive bundle', 1000, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'US');

-- free product
insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10105, PROMO_RULESET_SEQ.nextval,
	'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10105, PROMO_RULESET_SEQ.currval,
	'com.shaklee.rulesets.healthQuestionaire.components.DoesRequestedPromoMatch');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'promo_code', 'HPTEST');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10105, PROMO_RULESET_SEQ.currval,
	'com.shaklee.rulesets.healthQuestionaire.components.DidNotUseRequestedPromo');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10105, PROMO_RULESET_SEQ.currval,
	'com.shaklee.rulesets.healthQuestionaire.components.UserRankSmallerThan');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'rank', '7');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10105, PROMO_RULESET_SEQ.currval, 'Message');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'key', 'TIER_1_hp_email_promotion'),
	(PROMO_COMPONENT_SEQ.currval, 'action', 'BUY_BUNDLE'),
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'TIER_1'),
	(PROMO_COMPONENT_SEQ.currval, 'promo_code', 'HPTEST');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10105, PROMO_RULESET_SEQ.currval, 'Message');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'key', 'TIER_2_hp_email_promotion'),
	(PROMO_COMPONENT_SEQ.currval, 'action', 'BUY_BUNDLE'),
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'TIER_2'),
	(PROMO_COMPONENT_SEQ.currval, 'promo_code', 'HPTEST');
	
insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10105, PROMO_RULESET_SEQ.currval, 'Message');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'key', 'TIER_3_hp_email_promotion'),
	(PROMO_COMPONENT_SEQ.currval, 'action', 'BUY_BUNDLE'),
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'TIER_3'),
	(PROMO_COMPONENT_SEQ.currval, 'promo_code', 'HPTEST');