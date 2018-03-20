delete from PROMO where ID = 50002;

-- Score calculations and rules
insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) 
	values (50002, 'Life Style Scores', 'HP_SCORE', 'HP_SCORE_LIFESTYLE', 'Life Style Calculations', 1, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'US');

insert into PROMO_RULESET_SEQ(PROMO_ID) values (50002);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

-- Age restriction (Under / Over age)
insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS, JSON_SERIALIZED) 
	values (50002, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age', 
	'{
		"min": 13,
		"max": 99
	}');

-- Diet Score
insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS, JSON_SERIALIZED) 
	values (50002, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.content.DietScore',
	'[{
		"bmi_factor": [0.9, 1, 0.9, 0.75],
		"scores": {
			"energy": [95, 80, 60, 35],
			"stress": [95, 80, 60, 35],
			"sleep": [95, 75, 60, 50],
			"memory": [95, 90, 80, 50],
			"exercise_frequency": [30, 40, 80, 95],
			"exercise_intensity": [60, 80, 95, 80],
			"smoke": [95, 35, 25],
			"alcohol": [95, 80, 65, 50]
		}
	}]');