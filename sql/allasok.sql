SELECT upper(line) as Sor_Állomás,downtimename as Kategória,whofixed as Javította,  
CASE WHEN datefrom >= @report_date AND dateto < DATE_ADD(@report_date, INTERVAL 12 HOUR) THEN cast(FORMAT(TIME_TO_SEC(TIMEDIFF(dateto,datefrom))/60 ,0) as unsigned) ELSE '' END as HÉT_R,
CASE WHEN datefrom >= DATE_ADD(@report_date, INTERVAL 12 HOUR) AND datefrom < DATE_ADD(@report_date, INTERVAL 24 HOUR) THEN FORMAT(TIME_TO_SEC(TIMEDIFF(dateto,datefrom))/60 ,0) ELSE '' END as HÉT_É,

CASE WHEN datefrom >= DATE_ADD(@report_date, INTERVAL 24 HOUR) AND dateto < DATE_ADD(@report_date, INTERVAL 36 HOUR) THEN FORMAT(TIME_TO_SEC(TIMEDIFF(dateto,datefrom))/60 ,0) ELSE '' END as KEDD_R,
CASE WHEN datefrom >= DATE_ADD(@report_date, INTERVAL 36 HOUR) AND datefrom < DATE_ADD(@report_date, INTERVAL 48 HOUR) THEN FORMAT(TIME_TO_SEC(TIMEDIFF(dateto,datefrom))/60 ,0) ELSE '' END as KEDD_É,

CASE WHEN datefrom >= DATE_ADD(@report_date, INTERVAL 48 HOUR) AND dateto < DATE_ADD(@report_date, INTERVAL 60 HOUR) THEN FORMAT(TIME_TO_SEC(TIMEDIFF(dateto,datefrom))/60 ,0) ELSE '' END as SZER_R,
CASE WHEN datefrom >= DATE_ADD(@report_date, INTERVAL 60 HOUR) AND datefrom < DATE_ADD(@report_date, INTERVAL 72 HOUR) THEN FORMAT(TIME_TO_SEC(TIMEDIFF(dateto,datefrom))/60 ,0) ELSE '' END as SZER_É,

CASE WHEN datefrom >= DATE_ADD(@report_date, INTERVAL 72 HOUR) AND dateto < DATE_ADD(@report_date, INTERVAL 84 HOUR) THEN cast(FORMAT(TIME_TO_SEC(TIMEDIFF(dateto,datefrom))/60 ,0) as unsigned) ELSE '' END as CSÜT_R,
CASE WHEN datefrom >= DATE_ADD(@report_date, INTERVAL 84 HOUR) AND datefrom < DATE_ADD(@report_date, INTERVAL 96 HOUR) THEN cast(FORMAT(TIME_TO_SEC(TIMEDIFF(dateto,datefrom))/60 ,0) as unsigned) ELSE '' END as CSÜT_É,

CASE WHEN datefrom >= DATE_ADD(@report_date, INTERVAL 96 HOUR) AND dateto < DATE_ADD(@report_date, INTERVAL 108 HOUR) THEN cast(FORMAT(TIME_TO_SEC(TIMEDIFF(dateto,datefrom))/60 ,0) as unsigned) ELSE '' END as PÉNT_R,
CASE WHEN datefrom >= DATE_ADD(@report_date, INTERVAL 108 HOUR) AND datefrom < DATE_ADD(@report_date, INTERVAL 120 HOUR) THEN FORMAT(TIME_TO_SEC(TIMEDIFF(dateto,datefrom))/60 ,0) ELSE '' END as PÉNT_É,

CASE WHEN datefrom >= DATE_ADD(@report_date, INTERVAL 120 HOUR) AND dateto < DATE_ADD(@report_date, INTERVAL 132 HOUR) THEN FORMAT(TIME_TO_SEC(TIMEDIFF(dateto,datefrom))/60 ,0) ELSE '' END as SZOMB_R,
CASE WHEN datefrom >= DATE_ADD(@report_date, INTERVAL 132 HOUR) AND datefrom < DATE_ADD(@report_date, INTERVAL 144 HOUR) THEN FORMAT(TIME_TO_SEC(TIMEDIFF(dateto,datefrom))/60 ,0) ELSE '' END as SZOMB_É,

CASE WHEN datefrom >= DATE_ADD(@report_date, INTERVAL 144 HOUR) AND dateto < DATE_ADD(@report_date, INTERVAL 156 HOUR) THEN FORMAT(TIME_TO_SEC(TIMEDIFF(dateto,datefrom))/60 ,0) ELSE '' END as VAS_R,
CASE WHEN datefrom >= DATE_ADD(@report_date, INTERVAL 156 HOUR) AND datefrom < DATE_ADD(@report_date, INTERVAL 168 HOUR) THEN FORMAT(TIME_TO_SEC(TIMEDIFF(dateto,datefrom))/60 ,0) ELSE '' END as VAS_É,
trim(trailing '\n' from   cast(comments as CHAR)) as Komment
FROM downtimes_production 

WHERE datefrom >@report_date and dateto < DATE_ADD(@report_date, INTERVAL 168 HOUR) and line=@sor

ORDER BY line
