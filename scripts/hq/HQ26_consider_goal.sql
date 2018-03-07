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

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','HEART'),
	(@PROMO_COMPONENT_SEQ_VAL, 'position', '0');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '22077'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'heart');

-- -------------------------------------------------------------------------------
-- JOINT
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'position', '0'),
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','JOINT');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '20281'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'joint');

-- -------------------------------------------------------------------------------
-- BONES
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'position', '0'),
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','BONE');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '21217'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'bones');

-- -------------------------------------------------------------------------------
-- AGING
-- -------------------------------------------------------------------------------

-- Not Kosher

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'position', '0'),
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','AGING');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Kosher');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'kosher','false');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '21501'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'aging');

-- Kosher

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'position', '0'),
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','AGING');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Kosher');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '22911'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'aging');

-- -------------------------------------------------------------------------------
-- IMMUNE
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'position', '0'),
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','IMMUNE');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '20962'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'immunity');

-- -------------------------------------------------------------------------------
-- BRAIN
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'position', '0'),
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','BRAIN');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '22066'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'focus');
	
-- -------------------------------------------------------------------------------
-- ENERGY
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'position', '0'),
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','ENERGY');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '21261'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'energy');

-- -------------------------------------------------------------------------------
-- DIGESTIVE
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'position', '0'),
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','DIGESTIVE');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '80638'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'digestion');

-- -------------------------------------------------------------------------------
-- WEIGHT 
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'position', '0'),
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','WEIGHT');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '21261'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'weight_loss');

-- -------------------------------------------------------------------------------
-- SLEEP
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'position', '0'),
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','SLEEP');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '20603'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'sleep');

-- -------------------------------------------------------------------------------
-- STRESS
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'position', '0'),
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','STRESS');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '20656'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'lower_stress');
	
-- -------------------------------------------------------------------------------
-- FITNESS
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'position', '0'),
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','FITNESS');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '21261'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'fitness');

-- -------------------------------------------------------------------------------
-- -------------------------------------------------------------------------------
-- Position 2 : health_goals[1]
-- -------------------------------------------------------------------------------
-- -------------------------------------------------------------------------------

-- -------------------------------------------------------------------------------
-- HEART
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','HEART'),
	(@PROMO_COMPONENT_SEQ_VAL, 'position','1');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '22077'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'heart');

-- -------------------------------------------------------------------------------
-- JOINT
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'position','1'),
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','JOINT');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '20281'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'joint');

-- -------------------------------------------------------------------------------
-- BONES
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'position','1'),
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','BONE');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '21217'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'bones');

-- -------------------------------------------------------------------------------
-- AGING
-- -------------------------------------------------------------------------------

-- Not Kosher

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'position','1'),
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','AGING');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Kosher');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'kosher','false');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '21501'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'aging');

-- Kosher

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'position','1'),
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','AGING');



insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Kosher');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '22911'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'aging');

-- -------------------------------------------------------------------------------
-- IMMUNE
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'position','1'),
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','IMMUNE');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '20962'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'immunity');

-- -------------------------------------------------------------------------------
-- BRAIN
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'position','1'),
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','BRAIN');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '22066'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'focus');
	
-- -------------------------------------------------------------------------------
-- ENERGY
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'position','1'),
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','ENERGY');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '21261'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'energy');

-- -------------------------------------------------------------------------------
-- DIGESTIVE
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'position','1'),
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','DIGESTIVE');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '80638'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'digestion');

-- -------------------------------------------------------------------------------
-- WEIGHT 
-- -------------------------------------------------------------------------------

-- no need for BMI condition, since the weight loss section exclusively eliminates the BMI condition here.

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'position','1'),
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','WEIGHT');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '21261'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'weight_loss');

-- -------------------------------------------------------------------------------
-- SLEEP
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'position','1'),
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','SLEEP');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '20603'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'sleep');

-- -------------------------------------------------------------------------------
-- STRESS
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'position','1'),
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','STRESS');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '20656'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'lower_stress');
	
-- -------------------------------------------------------------------------------
-- FITNESS
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'position','1'),
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','FITNESS');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '21261'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'fitness');

-- -------------------------------------------------------------------------------
-- -------------------------------------------------------------------------------
-- Position 3 : health_goals[2]
-- -------------------------------------------------------------------------------
-- -------------------------------------------------------------------------------

-- -------------------------------------------------------------------------------
-- HEART
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','HEART'),
	(@PROMO_COMPONENT_SEQ_VAL, 'position','2');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '22077'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'heart');

-- -------------------------------------------------------------------------------
-- JOINT
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'position', '2'),
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','JOINT');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '20281'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'joint');

-- -------------------------------------------------------------------------------
-- BONES
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'position', '2'),
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','BONE');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '21217'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'bones');



-- -------------------------------------------------------------------------------
-- AGING
-- -------------------------------------------------------------------------------

-- Not Kosher

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'position', '2'),
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','AGING');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Kosher');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'kosher','false');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '21501'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'aging');

-- Kosher

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'position', '2'),
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','AGING');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Kosher');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '22911'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'aging');

-- -------------------------------------------------------------------------------
-- IMMUNE
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'position', '2'),
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','IMMUNE');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '20962'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'immunity');

-- -------------------------------------------------------------------------------
-- BRAIN
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'position', '2'),
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','BRAIN');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '22066'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'focus');
	
-- -------------------------------------------------------------------------------
-- ENERGY
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'position', '2'),
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','ENERGY');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '21261'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'energy');

-- -------------------------------------------------------------------------------
-- DIGESTIVE
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'position', '2'),
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','DIGESTIVE');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '80638'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'digestion');

-- -------------------------------------------------------------------------------
-- WEIGHT 
-- -------------------------------------------------------------------------------

-- no need for BMI condition, since the weight loss section exclusively eliminates the BMI condition here.

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'position', '2'),
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','WEIGHT');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '21261'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'weight_loss');

-- -------------------------------------------------------------------------------
-- SLEEP
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'position', '2'),
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','SLEEP');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '20603'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'sleep');

-- -------------------------------------------------------------------------------
-- STRESS
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'position', '2'),
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','STRESS');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '20656'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'lower_stress');
	
-- -------------------------------------------------------------------------------
-- FITNESS
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'position', '2'),
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','FITNESS');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '21261'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'fitness');






















-- -------------------------------------------------------------------------------
-- Goals: Heart
-- -------------------------------------------------------------------------------

-- position 1

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10026);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.HealthGoal');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'goal','HEART'),
	(@PROMO_COMPONENT_SEQ_VAL, 'position', '0');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10026, @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '22076'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '22079'),
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '22067'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'heart');