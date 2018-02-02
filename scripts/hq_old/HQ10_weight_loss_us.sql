delete from PROMO where ID = 10010;

-- US
insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) values
(10010, 'HealthQuestionaire Weight Loss', 'MC_HQ', 'HQ_WEIGHT_LOSS', 'HealthQuestionaire Weight Loss Health Goal', 989, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'US');

-- -------------------------------------------------------------------------------
-- Weight Loss: 
-- -------------------------------------------------------------------------------

-- Nuts allergy (Nut free)

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10010, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.WeightLoss');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10010, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10010, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.DieraryRestriction');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'diet', 'NUTS');
	
insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10010, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'TIER_2'),
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'TIER_3'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '89384'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'weight_loss');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10010, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'TIER_3'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '22031'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'energy');

-- Soy Free

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10010, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.WeightLoss');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10010, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10010, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.DieraryRestriction');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'diet', 'SOY');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10010, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'TIER_1'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21274'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'weight_loss');

	
-- Everyone

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10010, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.WeightLoss');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10010, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10010, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'TIER_1'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21263'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'weight_loss');
	
insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10010, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'TIER_2'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '89426'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'weight_loss');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10010, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'TIER_3'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '89280'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'weight_loss');

-- Men 

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10010, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.WeightLoss');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10010, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Gender');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'gender', 'M');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10010, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13'),
	(PROMO_COMPONENT_SEQ.currval, 'max','49');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10010, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'TIER_1'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21295'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'nutrition');

-- Women 

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10010, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.WeightLoss');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10010, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Gender');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'gender', 'F');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10010, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13'),
	(PROMO_COMPONENT_SEQ.currval, 'max','49');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10010, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.NotPregnant');
	
insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10010, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'TIER_1'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21296'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'nutrition');
	
-- Seniors 

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10010, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.WeightLoss');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10010, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','50');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10010, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'TIER_1'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21298'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'nutrition');