delete from PROMO where ID = 10020;

-- US
insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) values
(10020, 'HealthQuestionaire Women', 'MC_HQ', 'HQ_WOMEN', 'HealthQuestionaire Women', 979, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'US');

-- -------------------------------------------------------------------------------
-- PMS
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10020);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10020, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Pregnant');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'pregnant', 'PMS');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10020, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Gender');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'gender', 'F');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10020, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10020, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'TIER_1'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '20608'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'pms');
	
insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10020, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'TIER_2'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '20608'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'pms');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10020, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'TIER_3'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '20608'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'pms');

-- Consider in HQ_CONSIDER
	
	
-- -------------------------------------------------------------------------------
-- Menopause
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10020);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10020, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Pregnant');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'pregnant', 'MENOPAUSE');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10020, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Gender');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'gender', 'F');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10020, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10020, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'TIER_1'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '20645'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '22077'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'menopause');
	
insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10020, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'TIER_2'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '20645'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'menopause');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10020, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'TIER_3'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '20645'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'menopause');
