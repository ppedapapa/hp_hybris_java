delete from PROMO where ID = 10041;

-- ---------------------------------------------------------------
-- Create sub skus
-- ---------------------------------------------------------------

insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) values
(10041, 'HealthQuestionaire Sub Skus', 'MC_HQ', 'HQ_SUB_SKU', 'Load Sub Skus (bundle defaults)', 939, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'US');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10041, PROMO_RULESET_SEQ.nextval,
	'com.shaklee.rulesets.healthQuestionaire.components.Always');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10041, PROMO_RULESET_SEQ.currval,
	'com.shaklee.rulesets.healthQuestionaire.components.LoadSubSkus');

	

-- ---------------------------------------------------------------
-- Substitutions
-- ---------------------------------------------------------------

-- US https://shaktech.atlassian.net/wiki/display/UWT/Bundle+Swap+logic+-+Dietary+Restrictions
-- CA https://shaktech.atlassian.net/wiki/display/UWT/CAN+Bundle+Swap+Logic+-+Dietary+restriction

delete from PROMO where ID = 10042;

insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) values
(10042, 'HealthQuestionaire Sub Sku Substitutions', 'MC_HQ', 'HQ_SUB_SKU_SUBST', 'Sub Sku Substitutions', 938, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'US');


-- SOY General

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10042, PROMO_RULESET_SEQ.nextval,
	'com.shaklee.rulesets.healthQuestionaire.components.DieraryRestriction');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'diet', 'SOY');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10042, PROMO_RULESET_SEQ.currval,
	'com.shaklee.rulesets.healthQuestionaire.components.SubSKUSubstitutions');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21261 21275'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21264 21275'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21267 21275'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21263 21274'),
	
	(PROMO_COMPONENT_SEQ.currval, 'sku', '56261 56278'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '56264 56278'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '56267 56278');


-- Men 13-49

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10042, PROMO_RULESET_SEQ.nextval,
	'com.shaklee.rulesets.healthQuestionaire.components.Age');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min', '13'),
	(PROMO_COMPONENT_SEQ.currval, 'max', '49');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10042, PROMO_RULESET_SEQ.currval,
	'com.shaklee.rulesets.healthQuestionaire.components.Gender');
	
insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'gender', 'M');


insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10042, PROMO_RULESET_SEQ.currval,
	'com.shaklee.rulesets.healthQuestionaire.components.SubSKUSubstitutions');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21294 21293'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20283 20282'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21296 21295'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '57294 57293'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '57460 57465'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '57440 57445');


-- 50+

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10042, PROMO_RULESET_SEQ.nextval,
	'com.shaklee.rulesets.healthQuestionaire.components.Age');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'min', '50');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10042, PROMO_RULESET_SEQ.currval,
	'com.shaklee.rulesets.healthQuestionaire.components.SubSKUSubstitutions');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21294 21293'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20283 20284'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '57294 57293'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '57460 57570'),
--  (PROMO_COMPONENT_SEQ.currval, 'sku', '21296 21298'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '57440 57450');