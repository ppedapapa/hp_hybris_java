delete from PROMO where ID = 4000;

insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) values
(4000, 'HP BMI content', 'JP_HP_CONTENT', 'HP_BMI_CONTENT', 'HP BMI content', 10000, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'JP');

-- Age restriction (Under / over age)
insert into PROMO_RULESET (ID, PROMO_ID, SHARED, JSON_SERIALIZED) values (PROMO_COMPONENT_SEQ.nextval, 4000, 1,
'[{
	"@class": "com.shaklee.healthPrint.components.Age",
	"min": 13,
	"max": 100
}]');

insert into PROMO_RULESET (ID, PROMO_ID, JSON_SERIALIZED) values (PROMO_COMPONENT_SEQ.nextval, 4000, 
'[{
	"@class": "com.shaklee.healthPrint.components.CreateBMIMessages",
	"header_rules": {
		"18.5": "bmi-underweight",
		"25": "bmi-normalweight",
		"30": "bmi-overweight",
		"1000": "bmi-obesity"
	}
}]');
