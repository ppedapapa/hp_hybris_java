delete from PROMO where ID = 3000;

insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) values
(3000, 'HP BMI score', 'JP_HP_SCORE', 'HP_BMI_SCORE', 'HP BMI Score, minimum and maximum desirable weight scores', 100, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'JP');

-- Age restriction (Under / over age)
insert into PROMO_RULESET (ID, PROMO_ID, SHARED, JSON_SERIALIZED) values (PROMO_COMPONENT_SEQ.nextval, 3000, 1,
'[{
	"@class": "com.shaklee.healthPrint.components.Age",
	"min": 13,
	"max": 99
}]');

-- BMI Score
insert into PROMO_RULESET (ID, PROMO_ID, JSON_SERIALIZED) values (PROMO_COMPONENT_SEQ.nextval, 3000, 
'[{
	"@class": "com.shaklee.healthPrint.components.BMIScore"
}]');

-- Mimimum Desirable Weight Score
insert into PROMO_RULESET (ID, PROMO_ID, JSON_SERIALIZED) values (PROMO_COMPONENT_SEQ.nextval, 3000, 
'[{
	"@class": "com.shaklee.healthPrint.components.MinimumDesirableWeightScore"
}]');

-- Maximum Desirable Weight Score
insert into PROMO_RULESET (ID, PROMO_ID, JSON_SERIALIZED) values (PROMO_COMPONENT_SEQ.nextval, 3000, 
'[{
	"@class": "com.shaklee.healthPrint.components.MaximumDesirableWeightScore"
}]');