select PT_NAME, PT_NO, GEND_CD, AGE, ifnull(TLNO, 'NONE') as TLNO
from PATIENT
where GEND_CD like 'W' and AGE <= 12
order by AGE desc, PT_NAME