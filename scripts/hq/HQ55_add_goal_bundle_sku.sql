delete from PROMO where ID = 12775;

-- US
insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) values
(12775, 'Add goal tag to bundle skus', 'MC_HQ', 'HQ_GOAL_TAG_US', 'Goal-Tag', 850, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'US');

insert into PROMO_RULESET_SEQ(PROMO_ID) values (12775);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (12775, @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.Country');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'country_code', 'US');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS, JSON_SERIALIZED) values (12775, @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.AddGoalToBundleSku',
        '{
				"goal_skus": {
					"OVERALL": ["20282", "20283", "20284", "21293", "21294", "21295", "21296", "21298", "89383", "89384", "89402", "89403", "89404", "89426"],
					"DIGESTIVE": ["20282", "20283", "20284", "20435", "21261", "21263", "80638", "89383", "89384", "89403", "89404", "89426", "89280", "21275", "21274"],
					"HEART": ["20282", "20283", "20284", "21293", "21294", "22067", "22076", "22077", "22079", "89383", "89384", "89402", "89403"],
					"BONE": ["21214", "21217"],
					"WEIGHT": ["21261", "21263", "21274", "22000", "22054", "89280", "89383", "89384", "89402", "89403", "89404", "89426", "22051", "21275"],
					"ENERGY": ["20158", "20491", "21309", "20732", "22031", "89280"],
					"AGING": ["21293", "21294", "21501", "89383", "89403"],
					"JOINT": ["20281"],
					"IMMUNE": ["20962", "22073"],
					"BRAIN": ["22066"],
					"STRESS": ["20656"],
					"SLEEP": ["21216", "20603"],
					"FITNESS": ["21261"]
				}
		}'
);

--delete from PROMO where ID = 12776;

-- CA
--insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) values
--(12776, 'Add goal tag to bundle skus', 'MC_HQ', 'HQ_GOAL_TAG_CA', 'Goal-Tag', 850, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'US');

insert into PROMO_RULESET_SEQ(PROMO_ID) values (12775);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (12775, @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.Country');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'country_code', 'CA');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS, JSON_SERIALIZED) values (12775, @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.AddGoalToBundleSku',
        '{
				"goal_skus": {
					"OVERALL": ["57465", "57460", "57570", "57293", "57294", "57445", "57440", "57450", "79351", "79350", "79352", "79354", "79353", "79426"],
					"DIGESTIVE": ["57465", "57460", "57570", "57095", "56261", "56261", "57760", "79351", "79350", "79354", "79353", "79426", "79280", "56278", "56278"],
					"HEART": ["57465", "57460", "57570", "57293", "57294", "57067", "57315", "57695", "56219", "79351", "79350", "79352", "79354"],
					"BONE": ["57260", "57400"],
					"WEIGHT": ["56261", "56261", "56278", "56000", "56054", "79280", "79351", "79350", "79352", "79354", "79353", "79426", "56051", "56278"],
					"ENERGY": ["54158", "57710", "54309", "57815", "56031", "79280"],
					"AGING": ["57293", "57294", "57501", "79351", "79354"],
					"JOINT": ["57880"],
					"IMMUNE": ["57690", "57073"],
					"BRAIN": ["57066"],
					"STRESS": ["57680"],
					"SLEEP": ["57390", "57875"],
					"FITNESS": ["56261"]
				}
		}'
);
