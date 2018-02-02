-- ---------------------------------------------------------------
-- Bundle Price Limit
-- ---------------------------------------------------------------

delete from PROMO where ID = 10050;

insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) values
(10050, 'HealthQuestionaire Bundles', 'MC_HQ', 'HQ_PRICE_LIMIT', 'Create bundles', 900, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'US');

-- ---------------------------------------------------------------
-- US
-- ---------------------------------------------------------------

-- guest

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10050, PROMO_RULESET_SEQ.nextval,
	'com.shaklee.rulesets.healthQuestionaire.components.Country');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'country_code', 'US');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10050, PROMO_RULESET_SEQ.currval,
	'com.shaklee.rulesets.healthQuestionaire.components.IsMember');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'member', 'true');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10050, PROMO_RULESET_SEQ.currval,
	'com.shaklee.rulesets.healthQuestionaire.components.BundlePriceLimit');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'tier_1_max', '100'),
	(PROMO_COMPONENT_SEQ.currval, 'tier_2_max', '175'),
	(PROMO_COMPONENT_SEQ.currval, 'tier_3_max', '265'),
	(PROMO_COMPONENT_SEQ.currval, 'price_step', '10');

--member

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10050, PROMO_RULESET_SEQ.nextval,
	'com.shaklee.rulesets.healthQuestionaire.components.Country');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'country_code', 'US');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10050, PROMO_RULESET_SEQ.currval,
	'com.shaklee.rulesets.healthQuestionaire.components.IsMember');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'member', 'false');
	
insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10050, PROMO_RULESET_SEQ.currval,
	'com.shaklee.rulesets.healthQuestionaire.components.BundlePriceLimit');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'tier_1_max', '100'),
	(PROMO_COMPONENT_SEQ.currval, 'tier_2_max', '195'),
	(PROMO_COMPONENT_SEQ.currval, 'tier_3_max', '285'),
	(PROMO_COMPONENT_SEQ.currval, 'price_step', '10');
	
-- ---------------------------------------------------------------
-- CA
-- ---------------------------------------------------------------

-- guest

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10050, PROMO_RULESET_SEQ.nextval,
	'com.shaklee.rulesets.healthQuestionaire.components.Country');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'country_code', 'CA');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10050, PROMO_RULESET_SEQ.currval,
	'com.shaklee.rulesets.healthQuestionaire.components.IsMember');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'member', 'true');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10050, PROMO_RULESET_SEQ.currval,
	'com.shaklee.rulesets.healthQuestionaire.components.BundlePriceLimit');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'tier_1_max', '125'),
	(PROMO_COMPONENT_SEQ.currval, 'tier_2_max', '200'),
	(PROMO_COMPONENT_SEQ.currval, 'tier_3_max', '290'),
	(PROMO_COMPONENT_SEQ.currval, 'price_step', '10');

--member

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10050, PROMO_RULESET_SEQ.nextval,
	'com.shaklee.rulesets.healthQuestionaire.components.Country');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'country_code', 'CA');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10050, PROMO_RULESET_SEQ.currval,
	'com.shaklee.rulesets.healthQuestionaire.components.IsMember');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'member', 'false');
	
insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10050, PROMO_RULESET_SEQ.currval,
	'com.shaklee.rulesets.healthQuestionaire.components.BundlePriceLimit');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'tier_1_max', '125'),
	(PROMO_COMPONENT_SEQ.currval, 'tier_2_max', '220'),
	(PROMO_COMPONENT_SEQ.currval, 'tier_3_max', '310'),
	(PROMO_COMPONENT_SEQ.currval, 'price_step', '10');
	