delete from PROMO where ID = 10028;

insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) values
(10028, 'HealthQuestionaire Consider Diet', 'MC_HQ', 'HQ_CONSIDER_DIET', 'HealthQuestionaire Consider options based on diet', 957, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'US');


-- -------------------------------------------------------------------------------
-- C5. Fish	a. None	b. Rarely
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10028);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10028, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.DietChoice');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'question', 'healthy_fats'),
	(@PROMO_COMPONENT_SEQ_VAL, 'value', '0'),
	(@PROMO_COMPONENT_SEQ_VAL, 'value', '1');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10028, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10028, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '22077'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'heart');

-- -------------------------------------------------------------------------------
-- C4. Dairy	a. <7 times
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10028);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10028, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.DietChoice');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'question', 'dairy'),
	(@PROMO_COMPONENT_SEQ_VAL, 'value', '0'),
	(@PROMO_COMPONENT_SEQ_VAL, 'value', '1');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10028, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10028, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '21217'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'bone_health');

-- -------------------------------------------------------------------------------
-- C8. Unhealthy snacks	d. >13 times
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10028);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10028, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.DietChoice');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'question', 'junk_food'),
	(@PROMO_COMPONENT_SEQ_VAL, 'value', '3');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10028, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10028, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '22000'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'meal');

-- -------------------------------------------------------------------------------
-- C8. Unhealthy snacks	c. 7-13 times
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10028);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10028, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.DietChoice');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'question', 'junk_food'),
	(@PROMO_COMPONENT_SEQ_VAL, 'value', '2');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10028, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10028, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '22051'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'snack');

-- -------------------------------------------------------------------------------
-- C2. Vegetables	a. <7 times	b. 7-13 times
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10028);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10028, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.DietChoice');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'question', 'vegetables'),
	(@PROMO_COMPONENT_SEQ_VAL, 'value', '0'),
	(@PROMO_COMPONENT_SEQ_VAL, 'value', '1');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10028, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10028, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '20652'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'vision');

-- -------------------------------------------------------------------------------
-- C1. Fruit	a. <7 times
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10028);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10028, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.DietChoice');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'question', 'fruits'),
	(@PROMO_COMPONENT_SEQ_VAL, 'value', '0');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10028, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10028, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '20095'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'antioxidant');
	
-- -------------------------------------------------------------------------------
-- C3. Grains	a. <7 times	b. 7-13 times
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10028);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10028, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.DietChoice');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'question', 'grains'),
	(@PROMO_COMPONENT_SEQ_VAL, 'value', '0'),
	(@PROMO_COMPONENT_SEQ_VAL, 'value', '1');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10028, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10028, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '20435'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'colon');

-- -------------------------------------------------------------------------------
-- C7. Sugar Drinks	d. >6 times c. 3-6 times
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10028);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10028, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.DietChoice');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'question', 'sugar_drinks'),
	(@PROMO_COMPONENT_SEQ_VAL, 'value', '2'),
	(@PROMO_COMPONENT_SEQ_VAL, 'value', '3');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10028, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10028, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '22031'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'drink');
