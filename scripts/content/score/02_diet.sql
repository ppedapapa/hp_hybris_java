delete from PROMO where ID = 50001;

-- Score calculations and rules
insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) 
	values (50001, 'Diet Scores', 'HP_SCORE', 'HP_SCORE_DIET', 'Diet Calculations', 1, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'US');

insert into PROMO_RULESET_SEQ(PROMO_ID) values (50001);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

-- Age restriction (Under / Over age)
insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS, JSON_SERIALIZED) 
	values (50001, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age', 
	'{
		"min": 13,
		"max": 99
	}');

-- Diet Score
insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS, JSON_SERIALIZED) 
	values (50001, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.content.DietScore',
	'[{
		"bmi_factor": [0.9, 1, 0.9, 0.75],
		"scores": {
			"breakfast_times": [25, 50, 70, 95],
			"vegetables": [25, 50, 85, 95],
			"meat_fish": [50, 60, 75, 95],
			"days_fruits": [30, 75, 80, 95],
			"days_dairy": [25, 50, 85, 95],
			"times_sugary_drinks": [95, 70, 40, 25],
			"times_eat_out": [95, 80, 60, 40],
			"snacks_per_day": [95, 70, 40, 25]
		}
	}]');