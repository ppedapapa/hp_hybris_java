-- US arbitrator ----------------------------------------------------------------------------
 
DELETE from PROMO_ARBITRATOR where id = 10000;

INSERT INTO PROMO_ARBITRATOR (ID, RULESET_GROUP, IMPLEMENTING_CLASS, JSON_SERIALIZED, COUNTRY_CODE, TYPE)
  VALUES (10000, 'MC_HQ', 'com.shaklee.rulesets.healthQuestionaire.components.HQArbitrator', '{
    "groups": [
        {
            "arbitration": "ONE_OF",
            "promos": [
                "HQ_KIT",
                "HQ_KOSHER",
                "HQ_WEIGHT_LOSS",
                "HQ_PERFORMANCE",
                "HQ_ACTIVE",
                "HQ_OVERALL",
                "HQ_MULTI_V"
            ]
        },
        {
            "arbitration": "LAST",
            "exclude_priority_min": 961,
            "exclude_priority_max": 999,
            "promos": [
                "HQ_KIT",
                "HQ_WEIGHT_LOSS",
                "HQ_ACTIVE"
            ]
        },
        {
            "arbitration": "ONE_OF",
            "promos": [
            	"HQ_KIT",
                "HQ_WEIGHT_LOSS",
                "HQ_ACTIVE",
                "HQ_PRICE_LIMIT"
            ]
        }
    ]
}', 'US', 'ACTIONS');

-- first is the elimination of all the branches on the left of the diagram, they are all exclusive, so we need to keep only one.
-- second is the terminal branches. If a user ends there, its over.
-- third is elimination of price limits on certain branches.
-- why not multi?