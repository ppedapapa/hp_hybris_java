delete from PROMO where ID = 10030;

-- US
insert into PROMO (ID, NAME, RULESET_GROUP, CODE, DESCRIPTION, PRIORITY, START_TS, END_TS, ACTIVE, COUNTRY_CODE) values
(10030, 'HealthQuestionaire Dietary Restrictions', 'MC_HQ', 'HQ_RESTRICTIONS', 'Dietary Restrictions', 950, '2016-01-01 00:00:00', '2050-01-01 00:00:00', 1, 'US');

-----------------------
-- Kosher substitutions and elimination
-----------------------

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10030, PROMO_RULESET_SEQ.nextval,
	'com.shaklee.rulesets.healthQuestionaire.components.DieraryRestriction');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'diet', 'KOSHER');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10030, PROMO_RULESET_SEQ.currval,
	'com.shaklee.rulesets.healthQuestionaire.components.SKUSubstitutions');

-- exclusion list US

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20002'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20076'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20158'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20281'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20282'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20283'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20284'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20495'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20608'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20645'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20652'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21293'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21294'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21501 22911'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '22000'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '22051'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '22054'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '22067'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '22076'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '22077'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '22079'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '80638'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '89280'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '89383'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '89384');
	
-- exclusion list CA

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'sku', '57860');

-- Health Goals

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'sku', '32564'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '50916'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '89280');

-----------------------
-- Gluten
-----------------------
	
insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10030, PROMO_RULESET_SEQ.nextval,
	'com.shaklee.rulesets.healthQuestionaire.components.DieraryRestriction');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'diet', 'GLUTEN');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10030, PROMO_RULESET_SEQ.currval,
	'Message');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'key', 'gluten_free');

-----------------------
-- Soy
-----------------------
	
insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10030, PROMO_RULESET_SEQ.nextval,
	'com.shaklee.rulesets.healthQuestionaire.components.DieraryRestriction');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'diet', 'SOY');


insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10030, PROMO_RULESET_SEQ.currval,
	'com.shaklee.rulesets.healthQuestionaire.components.SKUSubstitutions');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21261 21275'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21263 21274'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21264'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21267');
	
-----------------------
-- Nuts
-----------------------
	
insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10030, PROMO_RULESET_SEQ.nextval,
	'com.shaklee.rulesets.healthQuestionaire.components.DieraryRestriction');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'diet', 'NUTS');


insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10030, PROMO_RULESET_SEQ.currval,
	'com.shaklee.rulesets.healthQuestionaire.components.SKUSubstitutions');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'sku', '22000'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '22051'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '22054'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '89280'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '89426');
	
-----------------------
-- Dairy
-----------------------
	
insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10030, PROMO_RULESET_SEQ.nextval,
	'com.shaklee.rulesets.healthQuestionaire.components.DieraryRestriction');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'diet', 'DAIRY');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10030, PROMO_RULESET_SEQ.currval,
	'com.shaklee.rulesets.healthQuestionaire.components.SKUSubstitutions');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20495'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20435');
	
-----------------------
-- Vegetarian
-----------------------
	
insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10030, PROMO_RULESET_SEQ.nextval,
	'com.shaklee.rulesets.healthQuestionaire.components.DieraryRestriction');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'diet', 'VEGETERIAN');

insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10030, PROMO_RULESET_SEQ.currval,
	'Message');

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'key', 'veg_gel_caps');
	
	
-----------------------
-- Pregnant
-----------------------
	
insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10030, PROMO_RULESET_SEQ.nextval,
	'com.shaklee.rulesets.healthQuestionaire.components.Pregnant');
insert into PROMO_COMPONENT (ID, PROMO_ID, RULESET_ID, IMPLEMENTING_CLASS) values (PROMO_COMPONENT_SEQ.nextval, 10030, PROMO_RULESET_SEQ.currval,
	'com.shaklee.rulesets.healthQuestionaire.components.SKUSubstitutions');

-- exclusion list

insert into PROMO_COMPONENT_PARAM (PROMO_COMPONENT_ID, NAME, VAL) values
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20002'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20076'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20281'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20282'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20284'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20286'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20290'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20608'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20645'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20656'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20962'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21293'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21294'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21501'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '22066'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '22067'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '22076'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '22079'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '89383'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20495'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '20001'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21295'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '21298'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '89402'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '89404'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '89403'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '89384'),
	(PROMO_COMPONENT_SEQ.currval, 'sku', '22911');