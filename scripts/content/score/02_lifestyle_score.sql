delete from PROMO where ID = 3002;

insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) values
(3002, 'HP Lifestyle score', 'JP_HP_SCORE', 'HP_LIFESTYLE_SCORE', 'HP Lifestyle score', 99, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'JP');

-- Age restriction (Under / over age)
insert into PROMO_RULESET (ID, PROMO_ID, SHARED, JSON_SERIALIZED) values (PROMO_COMPONENT_SEQ.nextval, 3002, 1,
'[{
	"@class": "com.shaklee.healthPrint.components.Age",
	"min": 13,
	"max": 99
}]');

-- BMI Score
insert into PROMO_RULESET (ID, PROMO_ID, JSON_SERIALIZED) values (PROMO_COMPONENT_SEQ.nextval, 3002, 
'[{
	"@class": "com.shaklee.healthPrint.components.LifeStyleScore",
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
