delete from PROMO where ID = 60001;

-- Content mmessage keys and rules
insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) 
	values (60001, 'BMI content', 'HP_CONTENT', 'HP_CONTENT_BMI', 'BMI Content keys', 100, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'US');

insert into PROMO_RULESET_SEQ(PROMO_ID) values (60001);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

-- Age restriction (Under / Over age)
insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS, JSON_SERIALIZED) 
	values (60001, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age', 
	'{
		"min": 13,
		"max": 99
	}');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS, JSON_SERIALIZED) 
	values (60001, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.healthPrint.components.BMIMessageKeyGenerator',
	'[{
		"prefix": "bmi",
		"rules": {
			"18.5": "underweight",
			"25": "normalweight",
			"30": "overweight",
			"1000": "obesity"
		}
	}]');