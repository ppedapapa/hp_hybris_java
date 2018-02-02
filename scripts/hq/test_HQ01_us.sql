delete from PROMO where ID = 10001;

insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) values
(10001, 'HealthQuestionaire Base Rules', 'MC_HQ', 'HQ_KIT', 'HealthQuestionaire Base Kit', 999, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'US');


insert into PROMO_RULESET_SEQ(PROMO_ID) values (10001);

set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10001, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min', '4'),
	(@PROMO_COMPONENT_SEQ_VAL, 'max', '12');

insert into PROMO_COMPONENT ( PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10001, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Kosher');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'kosher', 'false');

insert into PROMO_COMPONENT ( PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10001, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Country');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'country_code', 'US');

insert into PROMO_COMPONENT ( PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10001, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'TIER_1'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '20002'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'kids_multi_vitamin');

insert into PROMO_COMPONENT ( PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10001, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'TIER_2'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '20002'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'kids_multi_vitamin');

insert into PROMO_COMPONENT ( PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10001, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'TIER_2'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '20076'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'dha');

insert into PROMO_COMPONENT ( PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10001, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'TIER_3'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '20002'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'kids_multi_vitamin');

insert into PROMO_COMPONENT ( PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10001, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'TIER_3'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '20076'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'dha');

insert into PROMO_COMPONENT ( PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10001, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'TIER_3'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '21261'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'protein');

insert into PROMO_COMPONENT ( PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10001, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '20096'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'immunity');

insert into PROMO_COMPONENT ( PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10001, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '21264'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '21267'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'protein');
