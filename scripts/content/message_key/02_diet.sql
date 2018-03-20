delete from PROMO where ID = 60002;

-- Content mmessage keys and rules
insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) 
	values (60002, 'BMI content', 'HP_CONTENT', 'HP_CONTENT_DIET', 'DIET Content keys', 100, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'US');

insert into PROMO_RULESET_SEQ(PROMO_ID) values (60002);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

-- Age restriction (Under / Over age)
insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS, JSON_SERIALIZED) 
	values (60002, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age', 
	'{
		"min": 13,
		"max": 99
	}');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS, JSON_SERIALIZED) 
	values (60002, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.healthPrint.components.DietMessageKeyGenerator',
	'[{
		"prefix": "q",
		"rules": {
			"50": "diet-13-49",
			"70": "diet-50-69",
			"90": "diet-70-89",
			"1000": "diet-90-99"
		},
		"question_rules": {
			"breakfast_times": [1, 9, 17, 24],
			"vegetables": [4, 12, 19, 27],
			"meat_fish": [2, 10, 25, 0],
			"days_fruits": [5, 13, 20, 28],
			"days_dairy": [3, 11, 18, 26],
			"times_sugary_drinks": [30, 22, 15, 7],
			"times_eat_out": [31, 23, 16, 8],
			"snacks_per_day": [29, 21, 14, 6]
		}
	}]');
