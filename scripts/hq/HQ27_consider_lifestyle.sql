delete from PROMO where ID = 10027;

-- US
insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) values
(10027, 'HealthQuestionaire Consider Lifestyle', 'MC_HQ', 'HQ_CONSIDER_LIFESTYLE', 'HealthQuestionaire Consider options based on lifestyle.', 958, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'US');


-- -------------------------------------------------------------------------------
-- B1 Energy	d. Tired all the time
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10027);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.LifeStyleChoice');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'question', 'ENERGY'),
	(@PROMO_COMPONENT_SEQ_VAL, 'value', '0');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '20732'),
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'energy');

-- -------------------------------------------------------------------------------
-- B2. Stress	d. Always
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10027);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.LifeStyleChoice');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'question', 'STRESS'),
	(@PROMO_COMPONENT_SEQ_VAL, 'value', '3');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '20656'),
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'stress');
	
-- -------------------------------------------------------------------------------
-- B4. Memory	d. Deteriorating
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10027);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.LifeStyleChoice');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'question', 'MEMORY'),
	(@PROMO_COMPONENT_SEQ_VAL, 'value', '0');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '22066'),
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'focus');
	
-- -------------------------------------------------------------------------------
-- B3. Sleep	c. Falling asleep
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10027);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.LifeStyleChoice');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'question', 'SLEEP'),
	(@PROMO_COMPONENT_SEQ_VAL, 'value', '1');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '20603'),
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'sleep');
	
-- -------------------------------------------------------------------------------
-- B1 Energy	d. Tied all the time
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10027);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.LifeStyleChoice');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'question', 'ENERGY'),
	(@PROMO_COMPONENT_SEQ_VAL, 'value', '0');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '22031'),
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'energy');
	
-- -------------------------------------------------------------------------------
-- B4. Memory	c. Could be better
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10027);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.LifeStyleChoice');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'question', 'MEMORY'),
	(@PROMO_COMPONENT_SEQ_VAL, 'value', '1');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '22066'),
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'focus');
	
-- -------------------------------------------------------------------------------
-- B1 Energy	c. Pick me-up
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10027);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.LifeStyleChoice');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'question', 'ENERGY'),
	(@PROMO_COMPONENT_SEQ_VAL, 'value', '1');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '22031'),
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'energy');
	
-- -------------------------------------------------------------------------------
-- B2. Stress	c. Frequently
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10027);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.LifeStyleChoice');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'question', 'STRESS'),
	(@PROMO_COMPONENT_SEQ_VAL, 'value', '2');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '20656'),
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'stress');
	
-- -------------------------------------------------------------------------------
-- B4. Memory	b. Worry getting worse
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10027);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.LifeStyleChoice');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'question', 'MEMORY'),
	(@PROMO_COMPONENT_SEQ_VAL, 'value', '2');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '22066'),
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'focus');
	
-- -------------------------------------------------------------------------------
-- B3. Sleep	d. Toss and turn
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10027);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.LifeStyleChoice');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'question', 'SLEEP'),
	(@PROMO_COMPONENT_SEQ_VAL, 'value', '0');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '21216'),
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'bone_sleep');
	
	
-- -------------------------------------------------------------------------------
-- B5. Exercise Freq	d. More than 4 hours
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10027);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.LifeStyleChoice');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'question', 'EXERCISE_FREQUENCY'),
	(@PROMO_COMPONENT_SEQ_VAL, 'value', '3');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '20158'),
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'energy_pre_workout');

-- -------------------------------------------------------------------------------
-- B6. Exercise Intensity	d. Intense : Only present if answer to B5 on Exercise is 3-5 hours (c) or 6 or more hours (d)
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10027);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.LifeStyleChoice');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'question', 'EXERCISE_INTENSITY'),
	(@PROMO_COMPONENT_SEQ_VAL, 'value', '3');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.LifeStyleChoice');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'question', 'EXERCISE_FREQUENCY'),
	(@PROMO_COMPONENT_SEQ_VAL, 'value', '2'),
	(@PROMO_COMPONENT_SEQ_VAL, 'value', '3');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '21309'),
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'hydration');
	
-- -------------------------------------------------------------------------------
-- B6. Exercise Intensity	d. Intense or c. Moderate
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10027);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.LifeStyleChoice');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'question', 'EXERCISE_INTENSITY'),
	(@PROMO_COMPONENT_SEQ_VAL, 'value', '2'),
	(@PROMO_COMPONENT_SEQ_VAL, 'value', '3');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '20158'),
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'energy_pre_workout');

-- -------------------------------------------------------------------------------
-- B5. Exercise Freq	c. 2-4 hours
-- -------------------------------------------------------------------------------

insert into PROMO_RULESET_SEQ(PROMO_ID) values (10027);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.LifeStyleChoice');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'question', 'EXERCISE_FREQUENCY'),
	(@PROMO_COMPONENT_SEQ_VAL, 'value', '2');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10027, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.AddSku');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'sku', '20158'),
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'CONSIDER'),
	(@PROMO_COMPONENT_SEQ_VAL, 'category', 'energy_pre_workout');
