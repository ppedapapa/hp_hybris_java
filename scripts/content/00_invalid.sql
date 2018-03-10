delete from PROMO where ID = 50000;

insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) values
(50000, 'HP Invalid Cases', 'HP_CONTENT', 'HP_CONTENT_INVALID', 'HP Invalid Cases', 10000, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'US');

-- Under / over age

insert into PROMO_RULESET_SEQ(PROMO_ID) values (50000);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS, JSON_SERIALIZED) 
	values (50000, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age', '{
		"max": 13
	}');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS, JSON_SERIALIZED) 
	values (50000, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.promo.basic.Message', '{
		"key": "hp-under-age"
	}');

set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS, JSON_SERIALIZED) 
	values (50000, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age', '{
		"min": 100
	}');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS, JSON_SERIALIZED) 
	values (50000, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.promo.basic.Message', '{
		"key": "hp-over-age"
	}');
