ALTER TABLE USER ADD COLUMN (USER_LOGIN_ID VARCHAR(40) NOT NULL);

ALTER TABLE USER ADD COLUMN  ACCOUNT_TYPE VARCHAR(20);

ALTER TABLE USER ADD COLUMN   COUNTRY VARCHAR(4);

ALTER TABLE USER ADD COLUMN   PREFERRED_LANGUAGE VARCHAR(6);