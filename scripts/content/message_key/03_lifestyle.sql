delete from PROMO where ID = 60003;

-- Content mmessage keys and rules
insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) 
	values (60003, 'Life Style content', 'HP_CONTENT', 'HP_CONTENT_LIFESTYLE', 'Life Style Content keys', 100, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'US');

insert into PROMO_RULESET_SEQ(PROMO_ID) values (60003);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

-- Age restriction (Under / Over age)
insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS, JSON_SERIALIZED) 
	values (60003, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age', 
	'{
		"min": 13,
		"max": 99
	}');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS, JSON_SERIALIZED) 
	values (60003, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.healthPrint.components.DietMessageKeyGenerator',
	'[{
		"prefix": "q",
		"rules": {
			"50": "lifestyle-13-49",
			"70": "lifestyle-50-69",
			"90": "lifestyle-70-89",
			"1000": "lifestyle-90-99"
		},
		"question_rules": {
			"energy": [24, 17, 9, 1],
			"stress": [25, 18, 10, 2],
			"sleep": [28, 21, 13, 5],
			"memory": [29, 22, 14, 6],
			"exercise_frequency": [3, 11, 19, 26],
			"exercise_intensity": [4, 12, 20, 27],
			"smoke": [31, 16, 8],
			"alcohol": [30, 23, 15, 7]
		}
	}]');