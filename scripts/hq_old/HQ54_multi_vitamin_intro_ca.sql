delete from PROMO where ID = 10055;

-- CA
insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) values
(10055, 'HealthQuestionaire Multi-Vitamin Intro', 'MC_HQ', 'HQ_MV_INTRO', 'Multi-Vitamin Intro', 851, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'US');

-----------------------
-- is any SKUs are in the bundle and first place
-----------------------

-- HQ_MULTI_V
insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10055, PROMO_RULESET_SEQ.nextval,
	'com.shaklee.rulesets.healthQuestionaire.components.Country');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'country_code', 'CA');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10055, PROMO_RULESET_SEQ.currval,
	'com.shaklee.rulesets.healthQuestionaire.components.content.MultiVitaminIntro');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'promo_code', 'HQ_MULTI_V'),
	(PROMO_COMPONENT_SEQ.currval, 'action_class', 'SKUs');
-- diet
	--fruits
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'fruits 0 1 1 mvl_fruits_1 57440 57445 57450 57455 57465 57460 57570 57575 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'fruits 1 1 1 mvl_fruits_2 57440 57445 57450 57455 57465 57460 57570 57575 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'fruits 2 1 2 mvl_fruits_3 57440 57445 57450 57455 57465 57460 57570 57575 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'fruits 3 1 2 mvl_fruits_4 57440 57445 57450 57455 57465 57460 57570 57575 57293 57294');
	--vegetables
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'vegetables 0 1 1 mvl_vegetables_1 57440 57445 57450 57455 57465 57460 57570 57575 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'vegetables 1 1 1 mvl_vegetables_2 57440 57445 57450 57455 57465 57460 57570 57575 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'vegetables 2 1 1 mvl_vegetables_3 57440 57445 57450 57455 57465 57460 57570 57575 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'vegetables 3 1 2 mvl_vegetables_4 57440 57445 57450 57455 57465 57460 57570 57575 57293 57294');
	--grains
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'grains 0 1 1 mvl_grains_1 57440 57445 57450 57455 57465 57460 57570 57575 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'grains 1 1 1 mvl_grains_2 57440 57445 57450 57455 57465 57460 57570 57575 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'grains 2 1 1 mvl_grains_3 57440 57445 57450 57455 57465 57460 57570 57575 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'grains 3 1 2 mvl_grains_4 57440 57445 57450 57455 57465 57460 57570 57575 57293 57294');
	--dairy
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'dairy 0 1 1 mvl_dairy_1 57440 57445 57450 57455 57465 57460 57570 57575 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'dairy 1 1 1 mvl_dairy_2 57440 57445 57450 57455 57465 57460 57570 57575 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'dairy 2 1 1 mvl_dairy_3 57440 57445 57450 57455 57465 57460 57570 57575 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'dairy 3 1 2 mvl_dairy_4 57440 57445 57450 57455 57465 57460 57570 57575 57293 57294');
	--healthy_fats
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'healthy_fats 0 2 3 mvl_healthy_fats_1 57465 57460 57570 57575 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'healthy_fats 1 2 3 mvl_healthy_fats_2 57465 57460 57570 57575 57293 57294');
	--junk_food
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'junk_food 2 3 8 mvl_junk_food_3 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'junk_food 3 3 8 mvl_junk_food_4 57293 57294');
	--breakfast
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'breakfast 2 4 9 mvl_breakfast_3 57293 57294');
-- lifestyle
	--energy
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'energy 0 5 4 mvl_energy_4 57465 57460 57570 57575 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'energy 1 5 4 mvl_energy_3 57465 57460 57570 57575 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'energy 2 5 4 mvl_energy_2 57465 57460 57570 57575 57293 57294');
	--exercise_frequency
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'exercise_frequency 2 6 7 mvl_exercise_freq_3 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'exercise_frequency 3 6 7 mvl_exercise_freq_4 57293 57294');
	--exercise_intensity
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'exercise_intensity 2 7 7 mvl_exercise_intensity_3 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'exercise_intensity 3 7 7 mvl_exercise_intensity_4 57293 57294');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	-- goals
	(PROMO_COMPONENT_SEQ.currval, 'goal_rules', 'ENERGY 0 50 4 mvl_goal_energy 57465 57460 57570 57575 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'goal_rules', 'IMMUNE 0 51 5 mvl_goal_immune 57465 57460 57570 57575 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'goal_rules', 'FITNESS 0 52 7 mvl_goal_fit 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'goal_rules', 'AGING 0 53 10 mvl_goal_age 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'goal_rules', 'OVERALL 0 54 11 mvl_goal_overall 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'goal_rules', 'JOINT 0 55 12 mvl_goal_joint 57293 57294');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	-- filler
	(PROMO_COMPONENT_SEQ.currval, 'filler_messages', 'vitalizer 0 56 6 mvl_vitalizer_filler 57465 57460 57570 57575 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'filler_messages', 'life_strip 0 57 13 mvl_life_strip_filler 57293 57294');

-- HQ_OVERALL
insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10055, PROMO_RULESET_SEQ.nextval,
	'com.shaklee.rulesets.healthQuestionaire.components.Country');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'country_code', 'CA');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10055, PROMO_RULESET_SEQ.currval,
	'com.shaklee.rulesets.healthQuestionaire.components.content.MultiVitaminIntro');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'promo_code', 'HQ_OVERALL'),
	(PROMO_COMPONENT_SEQ.currval, 'action_class', 'SKUs');
-- diet
	--fruits
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'fruits 0 1 1 mvl_fruits_1 57440 57445 57450 57455 57465 57460 57570 57575 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'fruits 1 1 1 mvl_fruits_2 57440 57445 57450 57455 57465 57460 57570 57575 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'fruits 2 1 2 mvl_fruits_3 57440 57445 57450 57455 57465 57460 57570 57575 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'fruits 3 1 2 mvl_fruits_4 57440 57445 57450 57455 57465 57460 57570 57575 57293 57294');
	--vegetables
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'vegetables 0 1 1 mvl_vegetables_1 57440 57445 57450 57455 57465 57460 57570 57575 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'vegetables 1 1 1 mvl_vegetables_2 57440 57445 57450 57455 57465 57460 57570 57575 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'vegetables 2 1 1 mvl_vegetables_3 57440 57445 57450 57455 57465 57460 57570 57575 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'vegetables 3 1 2 mvl_vegetables_4 57440 57445 57450 57455 57465 57460 57570 57575 57293 57294');
	--grains
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'grains 0 1 1 mvl_grains_1 57440 57445 57450 57455 57465 57460 57570 57575 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'grains 1 1 1 mvl_grains_2 57440 57445 57450 57455 57465 57460 57570 57575 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'grains 2 1 1 mvl_grains_3 57440 57445 57450 57455 57465 57460 57570 57575 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'grains 3 1 2 mvl_grains_4 57440 57445 57450 57455 57465 57460 57570 57575 57293 57294');
	--dairy
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'dairy 0 1 1 mvl_dairy_1 57440 57445 57450 57455 57465 57460 57570 57575 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'dairy 1 1 1 mvl_dairy_2 57440 57445 57450 57455 57465 57460 57570 57575 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'dairy 2 1 1 mvl_dairy_3 57440 57445 57450 57455 57465 57460 57570 57575 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'dairy 3 1 2 mvl_dairy_4 57440 57445 57450 57455 57465 57460 57570 57575 57293 57294');
	--healthy_fats
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'healthy_fats 0 2 3 mvl_healthy_fats_1 57465 57460 57570 57575 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'healthy_fats 1 2 3 mvl_healthy_fats_2 57465 57460 57570 57575 57293 57294');
	--junk_food
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'junk_food 2 3 8 mvl_junk_food_3 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'junk_food 3 3 8 mvl_junk_food_4 57293 57294');
	--breakfast
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'breakfast 2 4 9 mvl_breakfast_3 57293 57294');
-- lifestyle
	--energy
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'energy 0 5 4 mvl_energy_4 57465 57460 57570 57575 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'energy 1 5 4 mvl_energy_3 57465 57460 57570 57575 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'energy 2 5 4 mvl_energy_2 57465 57460 57570 57575 57293 57294');
	--exercise_frequency
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'exercise_frequency 2 6 7 mvl_exercise_freq_3 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'exercise_frequency 3 6 7 mvl_exercise_freq_4 57293 57294');
	--exercise_intensity
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'exercise_intensity 2 7 7 mvl_exercise_intensity_3 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'question_rules', 'exercise_intensity 3 7 7 mvl_exercise_intensity_4 57293 57294');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	-- goals
	(PROMO_COMPONENT_SEQ.currval, 'goal_rules', 'ENERGY 0 50 4 mvl_goal_energy 57465 57460 57570 57575 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'goal_rules', 'IMMUNE 0 51 5 mvl_goal_immune 57465 57460 57570 57575 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'goal_rules', 'FITNESS 0 52 7 mvl_goal_fit 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'goal_rules', 'AGING 0 53 10 mvl_goal_age 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'goal_rules', 'OVERALL 0 54 11 mvl_goal_overall 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'goal_rules', 'JOINT 0 55 12 mvl_goal_joint 57293 57294');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	-- filler
	(PROMO_COMPONENT_SEQ.currval, 'filler_messages', 'vitalizer 0 56 6 mvl_vitalizer_filler 57465 57460 57570 57575 57293 57294'),
	(PROMO_COMPONENT_SEQ.currval, 'filler_messages', 'life_strip 0 57 13 mvl_life_strip_filler 57293 57294');

-- HQ_MULTI_V LIMIT
insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10055, PROMO_RULESET_SEQ.nextval,
	'com.shaklee.rulesets.healthQuestionaire.components.Country');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'country_code', 'CA');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10055, PROMO_RULESET_SEQ.currval,
	'com.shaklee.rulesets.healthQuestionaire.components.content.MultiVitaminIntroLimit');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'promo_code', 'HQ_MULTI_V'),
	(PROMO_COMPONENT_SEQ.currval, 'action_class', 'SKUs');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'limits', 'TIER_1 1'),
	(PROMO_COMPONENT_SEQ.currval, 'limits', 'TIER_2 3'),
	(PROMO_COMPONENT_SEQ.currval, 'limits', 'TIER_3 5');

-- HQ_OVERALL LIMIT
insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10055, PROMO_RULESET_SEQ.nextval,
	'com.shaklee.rulesets.healthQuestionaire.components.Country');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'country_code', 'CA');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10055, PROMO_RULESET_SEQ.currval,
	'com.shaklee.rulesets.healthQuestionaire.components.content.MultiVitaminIntroLimit');
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'promo_code', 'HQ_OVERALL'),
	(PROMO_COMPONENT_SEQ.currval, 'action_class', 'SKUs');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'limits', 'TIER_1 3'),
	(PROMO_COMPONENT_SEQ.currval, 'limits', 'TIER_2 3'),
	(PROMO_COMPONENT_SEQ.currval, 'limits', 'TIER_3 5');