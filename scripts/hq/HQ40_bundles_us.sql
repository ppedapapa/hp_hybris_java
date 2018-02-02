delete from PROMO where ID = 10040;

-- ---------------------------------------------------------------
-- Create bundles and remove duplicates
-- ---------------------------------------------------------------

insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) values
(10040, 'HealthQuestionaire Bundles', 'MC_HQ', 'HQ_BUNDLES', 'Create bundles', 940, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'US');

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10040);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT ( PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values ( 10040, @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.Always');
	
insert into PROMO_COMPONENT ( PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values ( 10040,  @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.CreateBundles');

	
-- Similar SKUs that need to be removed
insert into PROMO_RULESET_SEQ(PROMO_ID) values (10040);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT ( PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values ( 10040, @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.Always');

insert into PROMO_COMPONENT ( PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values ( 10040,  @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.RemoveSimilarSKUs');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '21293 21501 22911'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '21294 21501 22911');