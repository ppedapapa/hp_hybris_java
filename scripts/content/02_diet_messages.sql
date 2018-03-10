delete from PROMO where ID = 4002;

insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) values
(4002, 'HP Diet content', 'JP_HP_CONTENT', 'HP_DIET_CONTENT', 'HP Diet content', 10000, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'JP');

-- Age restriction (Under / over age)
insert into PROMO_RULESET (ID, PROMO_ID, SHARED, JSON_SERIALIZED) values (PROMO_COMPONENT_SEQ.nextval, 4002, 1,
'[{
	"@class": "com.shaklee.healthPrint.components.Age",
	"min": 13,
	"max": 99
}]');

insert into PROMO_RULESET (ID, PROMO_ID, JSON_SERIALIZED) values (PROMO_COMPONENT_SEQ.nextval, 4002, 
'[{
	"@class": "com.shaklee.healthPrint.components.CreateDietMessages",
	"prefix": "q",
	"header_rules": {
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
