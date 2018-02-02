delete from PROMO where ID = 10028;

insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) values
(10028, 'HealthQuestionaire Consider Diet', 'MC_HQ', 'HQ_CONSIDER_DIET', 'HealthQuestionaire Consider options based on diet', 957, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'US');


-- -------------------------------------------------------------------------------
-- C5. Fish	a. None	b. Rarely
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10028, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.DietChoice');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question', 'healthy_fats'),
	(PROMO_COMPONENT_SEQ.currval, 'value', '0'),
	(PROMO_COMPONENT_SEQ.currval, 'value', '1');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10028, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10028, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '22077'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'heart');

-- -------------------------------------------------------------------------------
-- C4. Dairy	a. <7 times
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10028, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.DietChoice');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question', 'dairy'),
	(PROMO_COMPONENT_SEQ.currval, 'value', '0'),
	(PROMO_COMPONENT_SEQ.currval, 'value', '1');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10028, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10028, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21217'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'bone_health');

-- -------------------------------------------------------------------------------
-- C8. Unhealthy snacks	d. >13 times
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10028, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.DietChoice');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question', 'junk_food'),
	(PROMO_COMPONENT_SEQ.currval, 'value', '3');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10028, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10028, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '22000'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'meal');

-- -------------------------------------------------------------------------------
-- C8. Unhealthy snacks	c. 7-13 times
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10028, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.DietChoice');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question', 'junk_food'),
	(PROMO_COMPONENT_SEQ.currval, 'value', '2');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10028, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10028, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '22051'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'snack');

-- -------------------------------------------------------------------------------
-- C2. Vegetables	a. <7 times	b. 7-13 times
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10028, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.DietChoice');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question', 'vegetables'),
	(PROMO_COMPONENT_SEQ.currval, 'value', '0'),
	(PROMO_COMPONENT_SEQ.currval, 'value', '1');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10028, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10028, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20652'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'vision');

-- -------------------------------------------------------------------------------
-- C1. Fruit	a. <7 times
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10028, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.DietChoice');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question', 'fruits'),
	(PROMO_COMPONENT_SEQ.currval, 'value', '0');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10028, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10028, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20095'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'antioxidant');
	
-- -------------------------------------------------------------------------------
-- C3. Grains	a. <7 times	b. 7-13 times
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10028, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.DietChoice');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question', 'grains'),
	(PROMO_COMPONENT_SEQ.currval, 'value', '0'),
	(PROMO_COMPONENT_SEQ.currval, 'value', '1');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10028, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10028, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20435'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'colon');

-- -------------------------------------------------------------------------------
-- C7. Sugar Drinks	d. >6 times c. 3-6 times
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10028, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.DietChoice');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question', 'sugar_drinks'),
	(PROMO_COMPONENT_SEQ.currval, 'value', '2'),
	(PROMO_COMPONENT_SEQ.currval, 'value', '3');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10028, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10028, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '22031'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'drink');
