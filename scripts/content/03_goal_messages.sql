delete from PROMO where ID = 4003;

insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) values
(4003, 'HP Life Style content', 'JP_HP_CONTENT', 'HP_LIFESTYLE_CONTENT', 'HP Life Style content', 10000, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'JP');

-- Age restriction (Under / over age)
insert into PROMO_RULESET (ID, PROMO_ID, SHARED, JSON_SERIALIZED) values (PROMO_COMPONENT_SEQ.nextval, 4003, 1,
'[{
	"@class": "com.shaklee.healthPrint.components.Age",
	"min": 13,
	"max": 99
}]');

insert into PROMO_RULESET (ID, PROMO_ID, JSON_SERIALIZED) values (PROMO_COMPONENT_SEQ.nextval, 4003, 
'[{
	"@class": "com.shaklee.healthPrint.components.CreateGoalMessages",
	"prefix": "goal",
	"goal_rules": {
		"OVERALL":{
				"stress": [19, 15, 11, 4],
				"sleep": [20, 16, 12, 5],
				"exercise_frequency": [1, 8, 0, 0],
				"vegetables": [6, 13, 0, 0],
				"days_fruits": [7, 17, 0, 0],
				"snacks_per_day": [18, 14, 10, 3]
		},
		"HEART":{
				"stress": [0, 0, 13, 3],
				"sleep": [0, 0, 0, 6],
				"exercise_frequency": [2, 12, 21, 24],
				"smoke": [22, 14, 4, 0],
				"vegetables": [9, 18, 0, 0],
				"meat_fish": [1, 11, 20, 25],
				"days_fruits": [10, 19, 0, 0],
				"times_sugary_drinks": [0, 0, 17, 8],
				"snacks_per_day": [0, 23, 16, 7]
		},
		"EYE":{
				"breakfast_times": [3, 6, 9, 12],
				"vegetables": [2, 5, 8, 11],
				"days_fruits": [1, 4, 7, 10]
		},
		"JOINT":{
				"exercise_frequency": [1, 9, 15, 18],
				"exercise_intensity": [8, 0, 0, 0],
				"vegetables": [6, 14, 0, 0],
				"meat_fish": [3, 11, 16, 19],
				"days_fruits": [7, 0, 0, 0],
				"times_sugary_drinks": [0, 0, 13, 5],
				"snacks_per_day": [19, 17, 12, 4]
		},
		"BONE":{
				"exercise_frequency": [2, 8, 12, 15],
				"exercise_intensity": [5, 0, 0, 0],
				"breakfast_times": [6, 0, 0, 0],
				"vegetables": [3, 9, 13, 16],
				"days_fruits": [4, 10, 0, 0],
				"days_dairy": [1, 7, 11, 14]
		},
		"AGING":{
				"stress": [0, 0, 12, 1],
				"sleep": [0, 0, 15, 4],
				"memory": [26, 23, 17, 6],
				"exercise_frequency": [3, 14, 21, 25],
				"smoke": [22, 16, 5],
				"breakfast_times": [10, 0, 0, 0],
				"vegetables": [8, 19, 0, 0],
				"meat_fish": [2, 13, 0, 0],
				"days_fruits": [9, 20, 0, 0],
				"snacks_per_day": [27, 24, 18, 7]
		},
		"IMMUNE":{
				"stress": [20, 16, 10, 1],
				"sleep": [21, 17, 11, 2],
				"exercise_frequency": [3, 0, 0, 0],
				"vegetables": [4, 12, 18, 22],
				"meat_fish": [6, 14, 0, 0],
				"days_fruits": [5, 13, 19, 23],
				"times_sugary_drinks": [0, 0, 0, 8],
				"snacks_per_day": [0, 0, 15, 7]
		},
		"BRAIN":{
				"stress": [0, 0, 14, 4],
				"sleep": [0, 0, 0, 5],
				"memory": [21, 18, 11, 1],
				"exercise_frequency": [3, 13, 19, 22],
				"vegetables": [6, 15, 20, 23],
				"meat_fish": [2, 12, 0, 0],
				"days_fruits": [7, 16, 0, 0],
				"times_sugary_drinks": [0, 0, 0, 9],
				"snacks_per_day": [0, 0, 17, 8]
		},
		"ENERGY":{
				"energy": [22, 18, 10, 1],
				"stress": [0, 0, 13, 4],
				"sleep": [24, 20, 12, 3],
				"exercise_frequency": [6, 15, 0, 0],
				"breakfast_times": [2, 11, 19, 23],
				"vegetables": [7, 16, 0, 0],
				"days_fruits": [8, 17, 0, 0],
				"snacks_per_day": [25, 21, 14, 5]
		},
		"DIGESTIVE":{
				"stress": [16, 13, 8, 1],
				"exercise_frequency": [6, 0, 0, 0],
				"breakfast_times": [3, 0, 0, 0],
				"vegetables": [2, 9, 14, 17],
				"days_fruits": [4, 10, 0, 0],
				"snacks_per_day": [18, 15, 11, 5]
		},
		"WEIGHT":{
				"energy": [0, 0, 0, 9],
				"sleep": [0, 0, 0, 10],
				"exercise_frequency": [1, 11, 17, 20],
				"breakfast_times": [5, 0, 0, 0],
				"vegetables": [6, 14, 0, 0],
				"days_fruits": [7, 15, 0, 0],
				"times_sugary_drinks": [0, 0, 0, 4],
				"times_eat_out": [22, 19, 16, 8],
				"snacks_per_day": [21, 18, 13, 3]
		},
		"DETOX":{
				"smoke": [7, 4, 1],
				"alcohol": [10, 8, 5, 2],
				"vegetables": [3, 6, 9, 11]
		},
		"SLEEP":{
				"stress": [12, 9, 6, 2],
				"sleep": [11, 8, 5, 1],
				"exercise_frequency": [3, 7, 10, 13]
		},
		"STRESS":{
				"energy": [0, 0, 0, 7],
				"stress": [18, 15, 9, 1],
				"sleep": [20, 17, 11, 3],
				"exercise_frequency": [2, 10, 16, 19],
				"vegetables": [5, 13, 0, 0],
				"meat_fish": [4, 12, 0, 0],
				"days_fruits": [6, 14, 0, 0]
		},
		"SKIN":{
				"stress": [11, 8, 5, 2],
				"sleep": [10, 7, 4, 1],
				"vegetables": [3, 6, 9, 12]
		}
	},
	"bmi_rules": {
		"OVERALL": [0, 0, 9, 2],
		"HEART":[0, 0, 15, 5],
		"JOINT": [0, 0, 10, 2], 
		"AGING": [0, 0, 0, 11], 
		"IMMUNE": [0, 0, 0, 9], 
		"BRAIN": [0, 0, 0, 10], 
		"ENERGY": [0, 0, 0, 9], 
		"DIGESTIVE": [0, 0, 12, 7], 
		"WEIGHT": [0, 0, 12, 2], 
		"SLEEP": [0, 0, 0, 4], 
		"STRESS": [0, 0, 0, 8]
	}
}]');