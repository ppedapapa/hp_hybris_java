CREATE TABLE
    USERS
    (
        USER_LOGIN_ID VARCHAR(20) NOT NULL,
        CONTACT_ID VARCHAR(20) NOT NULL,
        FIRST_NAME VARCHAR(4000),
        LAST_NAME VARCHAR(4000),
        EMAIL VARCHAR(128),
        CREATED TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
        PRIMARY KEY (CONTACT_ID)
    );
    
-- upload data from EXT_USER
select e.USER_LOGIN_ID,
CONCAT(e.USER_LOGIN_ID, '-1') AS CONTACT_ID,
e.P1_FIRST_NAME as FIRST_NAME,
e.P1_LAST_NAME as LAST_NAME,
e.EMAIL,
e.CREATE_TSTAMP
from ext_user e