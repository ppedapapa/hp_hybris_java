DROP TABLE PROMO_COMPONENT;

CREATE TABLE
    PROMO_COMPONENT
    (
        ID INTEGER NOT NULL AUTO_INCREMENT,
        PROMO_ID INTEGER NOT NULL,
        RULESET_ID INTEGER NOT NULL,
        IMPLEMENTING_CLASS VARCHAR(128) NOT NULL,
        JSON_SERIALIZED VARCHAR(1028),
        PRIMARY KEY (ID),
        CONSTRAINT PROMO_COMPONENT_PROMO_FK FOREIGN KEY (PROMO_ID) REFERENCES PROMO (ID) ON
    DELETE
        CASCADE
    );