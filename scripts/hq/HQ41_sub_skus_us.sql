delete from PROMO where ID = 10041;

-- ---------------------------------------------------------------
-- Create sub skus
-- ---------------------------------------------------------------

insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) values
(10041, 'HealthQuestionaire Sub Skus', 'MC_HQ', 'HQ_SUB_SKU', 'Load Sub Skus (bundle defaults)', 939, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'US');

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10041);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10041, @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.Always');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10041, @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.LoadSubSkus');

	

-- ---------------------------------------------------------------
-- Substitutions
-- ---------------------------------------------------------------

-- US https://shaktech.atlassian.net/wiki/display/UWT/Bundle+Swap+logic+-+Dietary+Restrictions
-- CA https://shaktech.atlassian.net/wiki/display/UWT/CAN+Bundle+Swap+Logic+-+Dietary+restriction

delete from PROMO where ID = 10042;

insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) values
(10042, 'HealthQuestionaire Sub Sku Substitutions', 'MC_HQ', 'HQ_SUB_SKU_SUBST', 'Sub Sku Substitutions', 938, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'US');


-- SOY General

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10041);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10042, @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.DieraryRestriction');

set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'diet', 'SOY');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10042, @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.SubSKUSubstitutions');

set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '21261 21275'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '21264 21275'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '21267 21275'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '21263 21274'),
	
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '56261 56278'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '56264 56278'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '56267 56278');


-- Men 13-49

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10041);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10042, @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.Age');

set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min', '13'),
	(@PROMO_COMPONENT_SEQ_VAL, 'max', '49');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10042, @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.Gender');
	
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'gender', 'M');


insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10042, @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.SubSKUSubstitutions');

set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '21294 21293'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '20283 20282'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '21296 21295'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '57294 57293'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '57460 57465'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '57440 57445');


-- 50+

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10041);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10042, @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.Age');

set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min', '50');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10042, @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.SubSKUSubstitutions');

set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '21294 21293'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '20283 20284'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '57294 57293'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '57460 57570'),
--  (@PROMO_COMPONENT_SEQ_VAL, 'sku', '21296 21298'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '57440 57450');