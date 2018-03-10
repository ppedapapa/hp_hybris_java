delete from PROMO where ID = 4001;

insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) values
(4001, 'HP Life Style content', 'JP_HP_CONTENT', 'HP_LIFESTYLE_CONTENT', 'HP Life Style content', 10000, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'JP');

-- Age restriction (Under / over age)
insert into PROMO_RULESET (ID, PROMO_ID, SHARED, JSON_SERIALIZED) values (PROMO_COMPONENT_SEQ.nextval, 4001, 1,
'[{
	"@class": "com.shaklee.healthPrint.components.Age",
	"min": 13,
	"max": 99
}]');

insert into PROMO_RULESET (ID, PROMO_ID, JSON_SERIALIZED) values (PROMO_COMPONENT_SEQ.nextval, 4001, 
'[{
	"@class": "com.shaklee.healthPrint.components.CreateLifeStyleMessages",
	"prefix": "q",
	"header_rules": {
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