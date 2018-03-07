-- ---------------------------------------------------------------
-- Consider filler
-- ---------------------------------------------------------------

delete from PROMO where ID = 10052;

insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) values
(10052, 'HealthQuestionaire Consider filler', 'MC_HQ', 'HQ_FILLER', 'If consider less than 4 products, 3 products may be added to consider for US ONLY', 849, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'US');

-- ---------------------------------------------------------------
-- Not Pregnant - non kosher
-- ---------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10052);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10052, @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.Country');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'country_code', 'US');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10052, @PROMO_RULESET_SEQ_VAL, 
	'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min', '13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10052, @PROMO_RULESET_SEQ_VAL, 
	'com.shaklee.rulesets.healthQuestionaire.components.NotPregnant');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10052, @PROMO_RULESET_SEQ_VAL, 
	'com.shaklee.rulesets.healthQuestionaire.components.Kosher');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'kosher','false');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10052, @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.FillBundleIfBundleSizeGreaterEqualThan');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle_name', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '21501'),
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle_size', 1),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'cellular_health');

-- ---------------------------------------------------------------
-- Not Pregnant - kosher
-- ---------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10052);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10052, @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.Country');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'country_code', 'US');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10052, @PROMO_RULESET_SEQ_VAL, 
	'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min', '13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10052, @PROMO_RULESET_SEQ_VAL, 
	'com.shaklee.rulesets.healthQuestionaire.components.NotPregnant');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10052, @PROMO_RULESET_SEQ_VAL, 
	'com.shaklee.rulesets.healthQuestionaire.components.Kosher');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'kosher','true');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10052, @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.FillBundleIfBundleSizeGreaterEqualThan');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle_name', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '22911'),
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle_size', 1),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'cellular_health');

-- ---------------------------------------------------------------
-- Pregnant does not matter
-- ---------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10052);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10052, @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.Country');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'country_code', 'US');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10052, @PROMO_RULESET_SEQ_VAL, 
	'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min', '13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10052, @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.FillBundleIfBundleSizeGreaterEqualThan');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle_name', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '22073'),
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle_size', 1),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'immunity');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10052, @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.FillBundleIfBundleSizeGreaterEqualThan');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle_name', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '32565'),
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle_size', 1),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'sunscreen');