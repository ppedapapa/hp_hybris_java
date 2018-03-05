-- This sql is for exporting data from EXT_USER - HQ_USER_DATA_STORAGE

create table TEMP_HQ_USER_DATA_STORAGE like HQ_USER_DATA_STORAGE;

insert into TEMP_HQ_USER_DATA_STORAGE (select * from HQ_USER_DATA_STORAGE);

-- old_data - populate first_name, last_name, email from EXT_USER

-- Step 1: look at the data 
SELECT S.USER_ID, S.EMAIL, S.FIRST_NAME AS s_first_name, S.LAST_NAME  AS s_last_name, S.EMAIL AS s_email, S.OPT_IN, 
S.ANSWERS_JSON, S.HEALTH_PROFILE_ID, U.P1_FIRST_NAME AS e_first_name, U.P1_LAST_NAME AS e_last_name, U.EMAIL AS e_email, S.SHARE_WITH_DISTRIBUTORS 
FROM HQ_USER_DATA_STORAGE S LEFT JOIN EXT_USER U ON S.USER_ID=U.USER_LOGIN_ID 

-- Step 2: merge the first_name, last_name, email from EXT_user if they do not exist
MERGE INTO TEMP_HQ_USER_DATA_STORAGE AS S
USING EXT_USER U ON S.USER_ID = U.USER_LOGIN_ID 
WHEN MATCHED THEN UPDATE SET S.FIRST_NAME  =  U.P1_FIRST_NAME ,S.LAST_NAME = U.P1_LAST_NAME, S.EMAIL =  U.EMAIL
