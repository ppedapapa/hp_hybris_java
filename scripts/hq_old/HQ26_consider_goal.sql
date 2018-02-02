delete from PROMO where ID = 10026;

-- US
insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) values
(10026, 'HealthQuestionaire Consider Goal', 'MC_HQ', 'HQ_CONSIDER_GOAL', 'HealthQuestionaire Consider options based on goals.', 959, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'US');

-- -------------------------------------------------------------------------------
-- -------------------------------------------------------------------------------
-- Position 1 : health_goals[0]
-- -------------------------------------------------------------------------------
-- -------------------------------------------------------------------------------

-- -------------------------------------------------------------------------------
-- HEART
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'goal','HEART'),
	(PROMO_COMPONENT_SEQ.currval, 'position', '0');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '22077'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'heart');

-- -------------------------------------------------------------------------------
-- JOINT
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'position', '0'),
	(PROMO_COMPONENT_SEQ.currval, 'goal','JOINT');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20281'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'joint');

-- -------------------------------------------------------------------------------
-- BONES
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'position', '0'),
	(PROMO_COMPONENT_SEQ.currval, 'goal','BONE');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21217'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'bones');

-- -------------------------------------------------------------------------------
-- AGING
-- -------------------------------------------------------------------------------

-- Not Kosher

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'position', '0'),
	(PROMO_COMPONENT_SEQ.currval, 'goal','AGING');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Kosher');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'kosher','false');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21501'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'aging');

-- Kosher

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'position', '0'),
	(PROMO_COMPONENT_SEQ.currval, 'goal','AGING');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Kosher');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '22911'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'aging');

-- -------------------------------------------------------------------------------
-- IMMUNE
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'position', '0'),
	(PROMO_COMPONENT_SEQ.currval, 'goal','IMMUNE');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20962'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'immunity');

-- -------------------------------------------------------------------------------
-- BRAIN
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'position', '0'),
	(PROMO_COMPONENT_SEQ.currval, 'goal','BRAIN');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '22066'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'focus');
	
-- -------------------------------------------------------------------------------
-- ENERGY
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'position', '0'),
	(PROMO_COMPONENT_SEQ.currval, 'goal','ENERGY');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21261'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'energy');

-- -------------------------------------------------------------------------------
-- DIGESTIVE
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'position', '0'),
	(PROMO_COMPONENT_SEQ.currval, 'goal','DIGESTIVE');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '80638'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'digestion');

-- -------------------------------------------------------------------------------
-- WEIGHT 
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'position', '0'),
	(PROMO_COMPONENT_SEQ.currval, 'goal','WEIGHT');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21261'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'weight_loss');

-- -------------------------------------------------------------------------------
-- SLEEP
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'position', '0'),
	(PROMO_COMPONENT_SEQ.currval, 'goal','SLEEP');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20603'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'sleep');

-- -------------------------------------------------------------------------------
-- STRESS
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'position', '0'),
	(PROMO_COMPONENT_SEQ.currval, 'goal','STRESS');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20656'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'lower_stress');
	
-- -------------------------------------------------------------------------------
-- FITNESS
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'position', '0'),
	(PROMO_COMPONENT_SEQ.currval, 'goal','FITNESS');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21261'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'fitness');

-- -------------------------------------------------------------------------------
-- -------------------------------------------------------------------------------
-- Position 2 : health_goals[1]
-- -------------------------------------------------------------------------------
-- -------------------------------------------------------------------------------

-- -------------------------------------------------------------------------------
-- HEART
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'goal','HEART'),
	(PROMO_COMPONENT_SEQ.currval, 'position','1');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '22077'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'heart');

-- -------------------------------------------------------------------------------
-- JOINT
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'position','1'),
	(PROMO_COMPONENT_SEQ.currval, 'goal','JOINT');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20281'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'joint');

-- -------------------------------------------------------------------------------
-- BONES
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'position','1'),
	(PROMO_COMPONENT_SEQ.currval, 'goal','BONE');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21217'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'bones');

-- -------------------------------------------------------------------------------
-- AGING
-- -------------------------------------------------------------------------------

-- Not Kosher

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'position','1'),
	(PROMO_COMPONENT_SEQ.currval, 'goal','AGING');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Kosher');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'kosher','false');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21501'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'aging');

-- Kosher

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'position','1'),
	(PROMO_COMPONENT_SEQ.currval, 'goal','AGING');



insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Kosher');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '22911'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'aging');

-- -------------------------------------------------------------------------------
-- IMMUNE
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'position','1'),
	(PROMO_COMPONENT_SEQ.currval, 'goal','IMMUNE');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20962'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'immunity');

-- -------------------------------------------------------------------------------
-- BRAIN
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'position','1'),
	(PROMO_COMPONENT_SEQ.currval, 'goal','BRAIN');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '22066'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'focus');
	
-- -------------------------------------------------------------------------------
-- ENERGY
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'position','1'),
	(PROMO_COMPONENT_SEQ.currval, 'goal','ENERGY');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21261'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'energy');

-- -------------------------------------------------------------------------------
-- DIGESTIVE
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'position','1'),
	(PROMO_COMPONENT_SEQ.currval, 'goal','DIGESTIVE');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '80638'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'digestion');

-- -------------------------------------------------------------------------------
-- WEIGHT 
-- -------------------------------------------------------------------------------

-- no need for BMI condition, since the weight loss section exclusively eliminates the BMI condition here.

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'position','1'),
	(PROMO_COMPONENT_SEQ.currval, 'goal','WEIGHT');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21261'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'weight_loss');

-- -------------------------------------------------------------------------------
-- SLEEP
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'position','1'),
	(PROMO_COMPONENT_SEQ.currval, 'goal','SLEEP');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20603'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'sleep');

-- -------------------------------------------------------------------------------
-- STRESS
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'position','1'),
	(PROMO_COMPONENT_SEQ.currval, 'goal','STRESS');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20656'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'lower_stress');
	
-- -------------------------------------------------------------------------------
-- FITNESS
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'position','1'),
	(PROMO_COMPONENT_SEQ.currval, 'goal','FITNESS');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21261'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'fitness');

-- -------------------------------------------------------------------------------
-- -------------------------------------------------------------------------------
-- Position 3 : health_goals[2]
-- -------------------------------------------------------------------------------
-- -------------------------------------------------------------------------------

-- -------------------------------------------------------------------------------
-- HEART
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'goal','HEART'),
	(PROMO_COMPONENT_SEQ.currval, 'position','2');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '22077'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'heart');

-- -------------------------------------------------------------------------------
-- JOINT
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'position', '2'),
	(PROMO_COMPONENT_SEQ.currval, 'goal','JOINT');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20281'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'joint');

-- -------------------------------------------------------------------------------
-- BONES
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'position', '2'),
	(PROMO_COMPONENT_SEQ.currval, 'goal','BONE');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21217'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'bones');



-- -------------------------------------------------------------------------------
-- AGING
-- -------------------------------------------------------------------------------

-- Not Kosher

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'position', '2'),
	(PROMO_COMPONENT_SEQ.currval, 'goal','AGING');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Kosher');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'kosher','false');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21501'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'aging');

-- Kosher

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'position', '2'),
	(PROMO_COMPONENT_SEQ.currval, 'goal','AGING');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Kosher');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '22911'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'aging');

-- -------------------------------------------------------------------------------
-- IMMUNE
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'position', '2'),
	(PROMO_COMPONENT_SEQ.currval, 'goal','IMMUNE');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20962'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'immunity');

-- -------------------------------------------------------------------------------
-- BRAIN
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'position', '2'),
	(PROMO_COMPONENT_SEQ.currval, 'goal','BRAIN');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '22066'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'focus');
	
-- -------------------------------------------------------------------------------
-- ENERGY
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'position', '2'),
	(PROMO_COMPONENT_SEQ.currval, 'goal','ENERGY');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21261'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'energy');

-- -------------------------------------------------------------------------------
-- DIGESTIVE
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'position', '2'),
	(PROMO_COMPONENT_SEQ.currval, 'goal','DIGESTIVE');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '80638'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'digestion');

-- -------------------------------------------------------------------------------
-- WEIGHT 
-- -------------------------------------------------------------------------------

-- no need for BMI condition, since the weight loss section exclusively eliminates the BMI condition here.

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'position', '2'),
	(PROMO_COMPONENT_SEQ.currval, 'goal','WEIGHT');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21261'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'weight_loss');

-- -------------------------------------------------------------------------------
-- SLEEP
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'position', '2'),
	(PROMO_COMPONENT_SEQ.currval, 'goal','SLEEP');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20603'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'sleep');

-- -------------------------------------------------------------------------------
-- STRESS
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'position', '2'),
	(PROMO_COMPONENT_SEQ.currval, 'goal','STRESS');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20656'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'lower_stress');
	
-- -------------------------------------------------------------------------------
-- FITNESS
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'position', '2'),
	(PROMO_COMPONENT_SEQ.currval, 'goal','FITNESS');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21261'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'fitness');






















-- -------------------------------------------------------------------------------
-- Goals: Heart
-- -------------------------------------------------------------------------------

-- position 1

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.nextval,
	'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'goal','HEART'),
	(PROMO_COMPONENT_SEQ.currval, 'position', '0');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval,
	'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10026, PROMO_RULESET_SEQ.currval,
	'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '22076'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '22079'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '22067'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'heart');