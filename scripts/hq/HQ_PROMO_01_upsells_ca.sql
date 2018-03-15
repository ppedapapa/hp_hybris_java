delete from PROMO where ID = 10101;

-- ---------------------------------------------------------------
-- Life Shake
-- ---------------------------------------------------------------

insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) values
(10101, 'HealthQuestionaire Promotions', 'HQ_PROMOS', 'HQ_LIFE_SHAKE', 'Life plan promo calculated after the bundles are done', 1000, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'US');

-- Soy dietary restriction ------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10101);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10101, @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.Country');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'country_code', 'CA');


insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10101, @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.DieraryRestriction');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'diet', 'SOY');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10101, @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.PacksContains');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'contains', 'false'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '79351'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '56261'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '56261'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '56264'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '56267'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '79280'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '56278'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '79352'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '79353'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '79354'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '79350'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '56264'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '56278'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '56277');
	
	
insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10101, @PROMO_RULESET_SEQ_VAL,
	'Message');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'key', 'life_shake_upsell'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '56278'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '56277');

	
-- NO SOY restriction ------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10101);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10101, @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.Country');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'country_code', 'CA');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10101, @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.DieraryRestriction');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'restriction', 'false'),
	(@PROMO_COMPONENT_SEQ_VAL, 'diet', 'SOY');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10101, @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.PacksContains');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'contains', 'false'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '79351'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '56261'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '56261'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '56264'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '56267'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '79280'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '56278'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '79352'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '79353'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '79354'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '79350'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '56264'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '56278'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '56277');

	
insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10101, @PROMO_RULESET_SEQ_VAL,
	'Message');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'key', 'life_shake_upsell'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '56261'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '56264'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '56267'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '56270');
