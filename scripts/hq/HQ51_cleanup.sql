-- ---------------------------------------------------------------
-- Bundle cleanup
-- ---------------------------------------------------------------

delete from PROMO where ID = 10051;

insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) values
(10051, 'HealthQuestionaire Bundle cleanup', 'MC_HQ', 'HQ_CLEANUP', 'Clean bundles', 850, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'US');

-- ---------------------------------------------------------------
-- Remove SKUs from consider that are already in tiers
-- ---------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10051);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10051, @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.Always');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10051, @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.ConsiderDoesNotContainTiers');