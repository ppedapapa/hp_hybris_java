delete from PROMO where ID = 10020;

-- US
insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) values
(10020, 'HealthQuestionaire Women', 'MC_HQ', 'HQ_WOMEN', 'HealthQuestionaire Women', 979, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'US');

-- -------------------------------------------------------------------------------
-- PMS
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10020, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.Pregnant');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'pregnant', 'PMS');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10020, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Gender');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'gender', 'F');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10020, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10020, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'TIER_1'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20608'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'pms');
	
insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10020, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'TIER_2'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20608'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'pms');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10020, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'TIER_3'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20608'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'pms');

-- Consider in HQ_CONSIDER
	
	
-- -------------------------------------------------------------------------------
-- Menopause
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10020, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.Pregnant');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'pregnant', 'MENOPAUSE');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10020, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Gender');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'gender', 'F');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10020, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10020, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'TIER_1'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20645'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '22077'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'menopause');
	
insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10020, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'TIER_2'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20645'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'menopause');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10020, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'TIER_3'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20645'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'menopause');
