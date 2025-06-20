WITH DISCOUNT AS (
    SELECT DURATION_TYPE
         , (100-TO_NUMBER(REGEXP_SUBSTR(DISCOUNT_RATE, '[0-9]+', 1, 1))) / 100 AS RATIO
      FROM CAR_RENTAL_COMPANY_DISCOUNT_PLAN
     WHERE CAR_TYPE = '트럭'
)
SELECT H.HISTORY_ID
     , ROUND(DAILY_FEE * RENTAL_DATE *
             CASE WHEN RENTAL_DATE < 7 THEN 1
                  WHEN RENTAL_DATE < 30 THEN (SELECT RATIO FROM DISCOUNT WHERE DURATION_TYPE = '7일 이상')
                  WHEN RENTAL_DATE < 90 THEN (SELECT RATIO FROM DISCOUNT WHERE DURATION_TYPE = '30일 이상')
                  ELSE (SELECT RATIO FROM DISCOUNT WHERE DURATION_TYPE = '90일 이상')
              END) AS FEE
  FROM (SELECT HIST.HISTORY_ID
             , CAR.DAILY_FEE
             , TRUNC(HIST.END_DATE, 'DD') - TRUNC(HIST.START_DATE, 'DD') + 1 AS RENTAL_DATE
          FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY HIST
          JOIN (SELECT CAR_ID, DAILY_FEE
                  FROM CAR_RENTAL_COMPANY_CAR
                 WHERE CAR_TYPE = '트럭'
               ) CAR
            ON HIST.CAR_ID = CAR.CAR_ID
       ) H
 ORDER BY FEE DESC, HISTORY_ID DESC