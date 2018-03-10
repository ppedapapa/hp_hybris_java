delete from PROMO where ID = 3001;

insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) values
(3001, 'HP Diet Score', 'JP_HP_SCORE', 'HP_DIET_SCORE', 'HP Diet score', 99, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'JP');

-- Age restriction (Under / over age)
insert into PROMO_RULESET (ID, PROMO_ID, SHARED, JSON_SERIALIZED) values (PROMO_COMPONENT_SEQ.nextval, 3001, 1,
'[{
	"@class": "com.shaklee.healthPrint.components.Age",
	"min": 13,
	"max": 99
}]');

-- BMI Score
insert into PROMO_RULESET (ID, PROMO_ID, JSON_SERIALIZED) values (PROMO_COMPONENT_SEQ.nextval, 3001, 
'[{
	"@class": "com.shaklee.healthPrint.components.DietScore",
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