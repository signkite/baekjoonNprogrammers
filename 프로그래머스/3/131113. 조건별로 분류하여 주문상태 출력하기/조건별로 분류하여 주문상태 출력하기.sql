SELECT ORDER_ID,
       PRODUCT_ID,
       TO_CHAR(OUT_DATE, 'YYYY-MM-DD'),
       (CASE
        WHEN OUT_DATE IS NULL THEN '출고미정'
        WHEN OUT_DATE < TO_DATE('2022-05-02', 'YYYY-MM-DD') THEN '출고완료'
        ELSE '출고대기'
        END) AS "출고여부"
    
FROM FOOD_ORDER
ORDER BY ORDER_ID ASC;
