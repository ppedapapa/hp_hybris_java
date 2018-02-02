delete from PROMO where ID = 10027;

-- US
insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) values
(10027, 'HealthQuestionaire Consider Lifestyle', 'MC_HQ', 'HQ_CONSIDER_LIFESTYLE', 'HealthQuestionaire Consider options based on lifestyle.', 958, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'US');


-- -------------------------------------------------------------------------------
-- B1 Energy	d. Tired all the time
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.LifeStyleChoice');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question', 'ENERGY'),
	(PROMO_COMPONENT_SEQ.currval, 'value', '0');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20732'),
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'energy');

-- -------------------------------------------------------------------------------
-- B2. Stress	d. Always
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.LifeStyleChoice');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question', 'STRESS'),
	(PROMO_COMPONENT_SEQ.currval, 'value', '3');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20656'),
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'stress');
	
-- -------------------------------------------------------------------------------
-- B4. Memory	d. Deteriorating
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.LifeStyleChoice');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question', 'MEMORY'),
	(PROMO_COMPONENT_SEQ.currval, 'value', '0');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'sku', '22066'),
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'focus');
	
-- -------------------------------------------------------------------------------
-- B3. Sleep	c. Falling asleep
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.LifeStyleChoice');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question', 'SLEEP'),
	(PROMO_COMPONENT_SEQ.currval, 'value', '1');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20603'),
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'sleep');
	
-- -------------------------------------------------------------------------------
-- B1 Energy	d. Tied all the time
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.LifeStyleChoice');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question', 'ENERGY'),
	(PROMO_COMPONENT_SEQ.currval, 'value', '0');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'sku', '22031'),
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'energy');
	
-- -------------------------------------------------------------------------------
-- B4. Memory	c. Could be better
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.LifeStyleChoice');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question', 'MEMORY'),
	(PROMO_COMPONENT_SEQ.currval, 'value', '1');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'sku', '22066'),
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'focus');
	
-- -------------------------------------------------------------------------------
-- B1 Energy	c. Pick me-up
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.LifeStyleChoice');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question', 'ENERGY'),
	(PROMO_COMPONENT_SEQ.currval, 'value', '1');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'sku', '22031'),
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'energy');
	
-- -------------------------------------------------------------------------------
-- B2. Stress	c. Frequently
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.LifeStyleChoice');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question', 'STRESS'),
	(PROMO_COMPONENT_SEQ.currval, 'value', '2');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20656'),
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'stress');
	
-- -------------------------------------------------------------------------------
-- B4. Memory	b. Worry getting worse
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.LifeStyleChoice');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question', 'MEMORY'),
	(PROMO_COMPONENT_SEQ.currval, 'value', '2');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'sku', '22066'),
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'focus');
	
-- -------------------------------------------------------------------------------
-- B3. Sleep	d. Toss and turn
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.LifeStyleChoice');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question', 'SLEEP'),
	(PROMO_COMPONENT_SEQ.currval, 'value', '0');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21216'),
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'bone_sleep');
	
	
-- -------------------------------------------------------------------------------
-- B5. Exercise Freq	d. More than 4 hours
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.LifeStyleChoice');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question', 'EXERCISE_FREQUENCY'),
	(PROMO_COMPONENT_SEQ.currval, 'value', '3');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20158'),
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'energy_pre_workout');

-- -------------------------------------------------------------------------------
-- B6. Exercise Intensity	d. Intense : Only present if answer to B5 on Exercise is 3-5 hours (c) or 6 or more hours (d)
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.LifeStyleChoice');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question', 'EXERCISE_INTENSITY'),
	(PROMO_COMPONENT_SEQ.currval, 'value', '3');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.LifeStyleChoice');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question', 'EXERCISE_FREQUENCY'),
	(PROMO_COMPONENT_SEQ.currval, 'value', '2'),
	(PROMO_COMPONENT_SEQ.currval, 'value', '3');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21309'),
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'hydration');
	
-- -------------------------------------------------------------------------------
-- B6. Exercise Intensity	d. Intense or c. Moderate
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.LifeStyleChoice');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question', 'EXERCISE_INTENSITY'),
	(PROMO_COMPONENT_SEQ.currval, 'value', '2'),
	(PROMO_COMPONENT_SEQ.currval, 'value', '3');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20158'),
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'energy_pre_workout');

-- -------------------------------------------------------------------------------
-- B5. Exercise Freq	c. 2-4 hours
-- -------------------------------------------------------------------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.nextval, 'com.shaklee.rulesets.healthQuestionaire.components.LifeStyleChoice');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question', 'EXERCISE_FREQUENCY'),
	(PROMO_COMPONENT_SEQ.currval, 'value', '2');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min','13');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10027, PROMO_RULESET_SEQ.currval, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20158'),
	(PROMO_COMPONENT_SEQ.currval, 'bundle', 'CONSIDER'),
	(PROMO_COMPONENT_SEQ.currval, 'category', 'energy_pre_workout');
