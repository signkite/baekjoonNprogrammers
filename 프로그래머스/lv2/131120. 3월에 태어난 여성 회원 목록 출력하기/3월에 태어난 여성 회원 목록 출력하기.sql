-- 코드를 입력하세요
SELECT MEMBER_ID, MEMBER_NAME, GENDER, date_format(DATE_OF_BIRTH, '%Y-%m-%d')
from MEMBER_PROFILE
where TLNO is not NULL
    and GENDER like 'W'
    and date_format(date_of_birth, '%m') like '03'
order by member_id