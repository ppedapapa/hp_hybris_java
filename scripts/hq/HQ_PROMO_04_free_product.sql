delete from PROMO where ID = 10104;

-- ---------------------------------------------------------------
-- Add basic H is the user did not already get this promo
-- ---------------------------------------------------------------

insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) values
(10104, 'Health Print Promotion Free Product and Shipping', 'HQ_PROMOS', 'HP_FREE_PRODUCT', 'Free basic H when buying comprehensive bundle', 1000, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'US');

-- free product
insert into PROMO_RULESET_SEQ(PROMO_ID) values (10104);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10104, @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');
	
insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10104, @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.DidNotUseHpPromo');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'promo_id', '10300'),
	(@PROMO_COMPONENT_SEQ_VAL, 'promo_id', '10350'),
	(@PROMO_COMPONENT_SEQ_VAL, 'promo_id', '10700'),
	(@PROMO_COMPONENT_SEQ_VAL, 'promo_id', '10750');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10104, @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.UserRankSmallerThan');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'rank', '7');
	
insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10104, @PROMO_RULESET_SEQ_VAL, 'Message');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'key', 'TIER_2_free_product'),
	(@PROMO_COMPONENT_SEQ_VAL, 'action', 'BUY_BUNDLE'),
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'TIER_2'),
	(@PROMO_COMPONENT_SEQ_VAL, 'promo_code', 'HPFRP2');
	
insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10104, @PROMO_RULESET_SEQ_VAL, 'Message');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'key', 'TIER_3_free_product'),
	(@PROMO_COMPONENT_SEQ_VAL, 'action', 'BUY_BUNDLE'),
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'TIER_3'),
	(@PROMO_COMPONENT_SEQ_VAL, 'promo_code', 'HPFRP2');

-- free shipping
insert into PROMO_RULESET_SEQ(PROMO_ID) values (10104);
set @PROMO_RULESET_SEQ_VAL = LAST_INSERT_ID();

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10104, @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.Age');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'min','13');
	
insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10104, @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.DidNotUseHpPromo');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'promo_id', '10600'),
	(@PROMO_COMPONENT_SEQ_VAL, 'promo_id', '10650');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10104, @PROMO_RULESET_SEQ_VAL,
	'com.shaklee.rulesets.healthQuestionaire.components.UserRankSmallerThan');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'rank', '7');

--insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10104, @PROMO_RULESET_SEQ_VAL, 'Message');
--insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	--(@PROMO_COMPONENT_SEQ_VAL, 'key', 'TIER_2_free_shipping'),
	--(@PROMO_COMPONENT_SEQ_VAL, 'action', 'BUY_BUNDLE'),
	--(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'TIER_2'),
	--(@PROMO_COMPONENT_SEQ_VAL, 'promo_code', 'FRSHHP');

insert into PROMO_COMPONENT (PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (10104, @PROMO_RULESET_SEQ_VAL, 'Message');
set @PROMO_COMPONENT_SEQ_VAL = LAST_INSERT_ID();
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(@PROMO_COMPONENT_SEQ_VAL, 'key', 'TIER_3_free_shipping'),
	(@PROMO_COMPONENT_SEQ_VAL, 'action', 'BUY_BUNDLE'),
	(@PROMO_COMPONENT_SEQ_VAL, 'bundle', 'TIER_3'),
	(@PROMO_COMPONENT_SEQ_VAL, 'promo_code', 'FRSHHP');
	