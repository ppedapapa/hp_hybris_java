delete from PROMO where ID = 60000;

-- Content provider rules

insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) values
(60000, 'Invalid Cases', 'HP_CONTENT', 'HP_CONTENT_INVALID', 'Content Provider Invalid Cases', 100, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'US');

insert into PROMO_RULESET_SEQ(PROMO_ID) values (60000);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

-- Under age
insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS, JSON_SERIALIZED) 
	values (60000, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age', '{
		"max": 13
	}');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS, JSON_SERIALIZED) 
	values (60000, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.promo.basic.Message', '{
		"key": "hp-under-age"
	}');

set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

-- Over age
insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS, JSON_SERIALIZED) 
	values (60000, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.rulesets.healthQuestionaire.components.Age', '{
		"min": 99
	}');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS, JSON_SERIALIZED) 
	values (60000, @PROMO_RULESET_SEQ_VAL, 'com.shaklee.promo.basic.Message', '{
		"key": "hp-over-age"
	}');
