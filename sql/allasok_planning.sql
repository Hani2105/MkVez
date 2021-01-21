
 

select downtime_types.name as Tipus,
SUM(
case when startdate < @muszak_befejezes and stopdate >@muszak_kezdes then
CASE WHEN
startdate<=@muszak_kezdes and stopdate<=@muszak_befejezes then
time_to_sec(timediff(stopdate, @muszak_kezdes ))  / 3600
WHEN
startdate<=@muszak_kezdes and stopdate>@muszak_befejezes then
time_to_sec(timediff(@muszak_befejezes, @muszak_kezdes ))  / 3600
WHEN
startdate>=@muszak_kezdes and stopdate<=@muszak_befejezes then
time_to_sec(timediff(stopdate, startdate ))  / 3600
else
time_to_sec(timediff(@muszak_befejezes, startdate ))  / 3600

end else 0 end) as HET_R,


SUM(
case when startdate < DATE_ADD(@muszak_befejezes, INTERVAL 12 HOUR) and stopdate >DATE_ADD(@muszak_kezdes, INTERVAL 12 HOUR) then
CASE WHEN
startdate<=DATE_ADD(@muszak_kezdes, INTERVAL 12 HOUR) and stopdate<=DATE_ADD(@muszak_befejezes, INTERVAL 12 HOUR) then
time_to_sec(timediff(stopdate, DATE_ADD(@muszak_kezdes, INTERVAL 12 HOUR) ))  / 3600
WHEN
startdate<=DATE_ADD(@muszak_kezdes, INTERVAL 12 HOUR) and stopdate>DATE_ADD(@muszak_befejezes, INTERVAL 12 HOUR) then
time_to_sec(timediff(DATE_ADD(@muszak_befejezes, INTERVAL 12 HOUR), DATE_ADD(@muszak_kezdes, INTERVAL 12 HOUR) ))  / 3600
WHEN
startdate>=DATE_ADD(@muszak_kezdes, INTERVAL 12 HOUR) and stopdate<=DATE_ADD(@muszak_befejezes, INTERVAL 12 HOUR) then
time_to_sec(timediff(stopdate, startdate ))  / 3600
else
time_to_sec(timediff(DATE_ADD(@muszak_befejezes, INTERVAL 12 HOUR), startdate ))  / 3600

end else 0 end) as HET_E,

SUM(
case when startdate < DATE_ADD(@muszak_befejezes, INTERVAL 24 HOUR) and stopdate >DATE_ADD(@muszak_kezdes, INTERVAL 24 HOUR) then
CASE WHEN
startdate<=DATE_ADD(@muszak_kezdes, INTERVAL 24 HOUR) and stopdate<=DATE_ADD(@muszak_befejezes, INTERVAL 24 HOUR) then
time_to_sec(timediff(stopdate, DATE_ADD(@muszak_kezdes, INTERVAL 24 HOUR) ))  / 3600
WHEN
startdate<=DATE_ADD(@muszak_kezdes, INTERVAL 24 HOUR) and stopdate>DATE_ADD(@muszak_befejezes, INTERVAL 24 HOUR) then
time_to_sec(timediff(DATE_ADD(@muszak_befejezes, INTERVAL 24 HOUR), DATE_ADD(@muszak_kezdes, INTERVAL 24 HOUR) ))  / 3600
WHEN
startdate>=DATE_ADD(@muszak_kezdes, INTERVAL 24 HOUR) and stopdate<=DATE_ADD(@muszak_befejezes, INTERVAL 24 HOUR) then
time_to_sec(timediff(stopdate, startdate ))  / 3600
else
time_to_sec(timediff(DATE_ADD(@muszak_befejezes, INTERVAL 24 HOUR), startdate ))  / 3600

end else 0 end) as KED_R,

SUM(
case when startdate < DATE_ADD(@muszak_befejezes, INTERVAL 36 HOUR) and stopdate >DATE_ADD(@muszak_kezdes, INTERVAL 36 HOUR) then
CASE WHEN
startdate<=DATE_ADD(@muszak_kezdes, INTERVAL 36 HOUR) and stopdate<=DATE_ADD(@muszak_befejezes, INTERVAL 36 HOUR) then
time_to_sec(timediff(stopdate, DATE_ADD(@muszak_kezdes, INTERVAL 36 HOUR) ))  / 3600
WHEN
startdate<=DATE_ADD(@muszak_kezdes, INTERVAL 36 HOUR) and stopdate>DATE_ADD(@muszak_befejezes, INTERVAL 36 HOUR) then
time_to_sec(timediff(DATE_ADD(@muszak_befejezes, INTERVAL 36 HOUR), DATE_ADD(@muszak_kezdes, INTERVAL 36 HOUR) ))  / 3600
WHEN
startdate>=DATE_ADD(@muszak_kezdes, INTERVAL 36 HOUR) and stopdate<=DATE_ADD(@muszak_befejezes, INTERVAL 36 HOUR) then
time_to_sec(timediff(stopdate, startdate ))  / 3600
else
time_to_sec(timediff(DATE_ADD(@muszak_befejezes, INTERVAL 36 HOUR), startdate ))  / 3600

end else 0 end) as KED_E,

SUM(
case when startdate < DATE_ADD(@muszak_befejezes, INTERVAL 48 HOUR) and stopdate >DATE_ADD(@muszak_kezdes, INTERVAL 48 HOUR) then
CASE WHEN
startdate<=DATE_ADD(@muszak_kezdes, INTERVAL 48 HOUR) and stopdate<=DATE_ADD(@muszak_befejezes, INTERVAL 48 HOUR) then
time_to_sec(timediff(stopdate, DATE_ADD(@muszak_kezdes, INTERVAL 48 HOUR) ))  / 3600
WHEN
startdate<=DATE_ADD(@muszak_kezdes, INTERVAL 48 HOUR) and stopdate>DATE_ADD(@muszak_befejezes, INTERVAL 48 HOUR) then
time_to_sec(timediff(DATE_ADD(@muszak_befejezes, INTERVAL 48 HOUR), DATE_ADD(@muszak_kezdes, INTERVAL 48 HOUR) ))  / 3600
WHEN
startdate>=DATE_ADD(@muszak_kezdes, INTERVAL 48 HOUR) and stopdate<=DATE_ADD(@muszak_befejezes, INTERVAL 48 HOUR) then
time_to_sec(timediff(stopdate, startdate ))  / 3600
else
time_to_sec(timediff(DATE_ADD(@muszak_befejezes, INTERVAL 48 HOUR), startdate ))  / 3600

end else 0 end) as SZER_R,

SUM(
case when startdate < DATE_ADD(@muszak_befejezes, INTERVAL 60 HOUR) and stopdate >DATE_ADD(@muszak_kezdes, INTERVAL 60 HOUR) then
CASE WHEN
startdate<=DATE_ADD(@muszak_kezdes, INTERVAL 60 HOUR) and stopdate<=DATE_ADD(@muszak_befejezes, INTERVAL 60 HOUR) then
time_to_sec(timediff(stopdate, DATE_ADD(@muszak_kezdes, INTERVAL 60 HOUR) ))  / 3600
WHEN
startdate<=DATE_ADD(@muszak_kezdes, INTERVAL 60 HOUR) and stopdate>DATE_ADD(@muszak_befejezes, INTERVAL 60 HOUR) then
time_to_sec(timediff(DATE_ADD(@muszak_befejezes, INTERVAL 60 HOUR), DATE_ADD(@muszak_kezdes, INTERVAL 60 HOUR) ))  / 3600
WHEN
startdate>=DATE_ADD(@muszak_kezdes, INTERVAL 60 HOUR) and stopdate<=DATE_ADD(@muszak_befejezes, INTERVAL 60 HOUR) then
time_to_sec(timediff(stopdate, startdate ))  / 3600
else
time_to_sec(timediff(DATE_ADD(@muszak_befejezes, INTERVAL 60 HOUR), startdate ))  / 3600

end else 0 end) as SZER_E,

SUM(
case when startdate < DATE_ADD(@muszak_befejezes, INTERVAL 72 HOUR) and stopdate >DATE_ADD(@muszak_kezdes, INTERVAL 72 HOUR) then
CASE WHEN
startdate<=DATE_ADD(@muszak_kezdes, INTERVAL 72 HOUR) and stopdate<=DATE_ADD(@muszak_befejezes, INTERVAL 72 HOUR) then
time_to_sec(timediff(stopdate, DATE_ADD(@muszak_kezdes, INTERVAL 72 HOUR) ))  / 3600
WHEN
startdate<=DATE_ADD(@muszak_kezdes, INTERVAL 72 HOUR) and stopdate>DATE_ADD(@muszak_befejezes, INTERVAL 72 HOUR) then
time_to_sec(timediff(DATE_ADD(@muszak_befejezes, INTERVAL 72 HOUR), DATE_ADD(@muszak_kezdes, INTERVAL 72 HOUR) ))  / 3600
WHEN
startdate>=DATE_ADD(@muszak_kezdes, INTERVAL 72 HOUR) and stopdate<=DATE_ADD(@muszak_befejezes, INTERVAL 72 HOUR) then
time_to_sec(timediff(stopdate, startdate ))  / 3600
else
time_to_sec(timediff(DATE_ADD(@muszak_befejezes, INTERVAL 72 HOUR), startdate ))  / 3600

end else 0 end) as CSUT_R,

SUM(
case when startdate < DATE_ADD(@muszak_befejezes, INTERVAL 84 HOUR) and stopdate >DATE_ADD(@muszak_kezdes, INTERVAL 84 HOUR) then
CASE WHEN
startdate<=DATE_ADD(@muszak_kezdes, INTERVAL 84 HOUR) and stopdate<=DATE_ADD(@muszak_befejezes, INTERVAL 84 HOUR) then
time_to_sec(timediff(stopdate, DATE_ADD(@muszak_kezdes, INTERVAL 84 HOUR) ))  / 3600
WHEN
startdate<=DATE_ADD(@muszak_kezdes, INTERVAL 84 HOUR) and stopdate>DATE_ADD(@muszak_befejezes, INTERVAL 84 HOUR) then
time_to_sec(timediff(DATE_ADD(@muszak_befejezes, INTERVAL 84 HOUR), DATE_ADD(@muszak_kezdes, INTERVAL 84 HOUR) ))  / 3600
WHEN
startdate>=DATE_ADD(@muszak_kezdes, INTERVAL 84 HOUR) and stopdate<=DATE_ADD(@muszak_befejezes, INTERVAL 84 HOUR) then
time_to_sec(timediff(stopdate, startdate ))  / 3600
else
time_to_sec(timediff(DATE_ADD(@muszak_befejezes, INTERVAL 84 HOUR), startdate ))  / 3600

end else 0 end) as CSUT_E,

SUM(
case when startdate < DATE_ADD(@muszak_befejezes, INTERVAL 96 HOUR) and stopdate >DATE_ADD(@muszak_kezdes, INTERVAL 96 HOUR) then
CASE WHEN
startdate<=DATE_ADD(@muszak_kezdes, INTERVAL 96 HOUR) and stopdate<=DATE_ADD(@muszak_befejezes, INTERVAL 96 HOUR) then
time_to_sec(timediff(stopdate, DATE_ADD(@muszak_kezdes, INTERVAL 96 HOUR) ))  / 3600
WHEN
startdate<=DATE_ADD(@muszak_kezdes, INTERVAL 96 HOUR) and stopdate>DATE_ADD(@muszak_befejezes, INTERVAL 96 HOUR) then
time_to_sec(timediff(DATE_ADD(@muszak_befejezes, INTERVAL 96 HOUR), DATE_ADD(@muszak_kezdes, INTERVAL 96 HOUR) ))  / 3600
WHEN
startdate>=DATE_ADD(@muszak_kezdes, INTERVAL 96 HOUR) and stopdate<=DATE_ADD(@muszak_befejezes, INTERVAL 96 HOUR) then
time_to_sec(timediff(stopdate, startdate ))  / 3600
else
time_to_sec(timediff(DATE_ADD(@muszak_befejezes, INTERVAL 96 HOUR), startdate ))  / 3600

end else 0 end) as PEN_R,

SUM(
case when startdate < DATE_ADD(@muszak_befejezes, INTERVAL 108 HOUR) and stopdate >DATE_ADD(@muszak_kezdes, INTERVAL 108 HOUR) then
CASE WHEN
startdate<=DATE_ADD(@muszak_kezdes, INTERVAL 108 HOUR) and stopdate<=DATE_ADD(@muszak_befejezes, INTERVAL 108 HOUR) then
time_to_sec(timediff(stopdate, DATE_ADD(@muszak_kezdes, INTERVAL 108 HOUR) ))  / 3600
WHEN
startdate<=DATE_ADD(@muszak_kezdes, INTERVAL 108 HOUR) and stopdate>DATE_ADD(@muszak_befejezes, INTERVAL 108 HOUR) then
time_to_sec(timediff(DATE_ADD(@muszak_befejezes, INTERVAL 108 HOUR), DATE_ADD(@muszak_kezdes, INTERVAL 108 HOUR) ))  / 3600
WHEN
startdate>=DATE_ADD(@muszak_kezdes, INTERVAL 108 HOUR) and stopdate<=DATE_ADD(@muszak_befejezes, INTERVAL 108 HOUR) then
time_to_sec(timediff(stopdate, startdate ))  / 3600
else
time_to_sec(timediff(DATE_ADD(@muszak_befejezes, INTERVAL 108 HOUR), startdate ))  / 3600

end else 0 end) as PEN_E,

SUM(
case when startdate < DATE_ADD(@muszak_befejezes, INTERVAL 120 HOUR) and stopdate >DATE_ADD(@muszak_kezdes, INTERVAL 120 HOUR) then
CASE WHEN
startdate<=DATE_ADD(@muszak_kezdes, INTERVAL 120 HOUR) and stopdate<=DATE_ADD(@muszak_befejezes, INTERVAL 120 HOUR) then
time_to_sec(timediff(stopdate, DATE_ADD(@muszak_kezdes, INTERVAL 120 HOUR) ))  / 3600
WHEN
startdate<=DATE_ADD(@muszak_kezdes, INTERVAL 120 HOUR) and stopdate>DATE_ADD(@muszak_befejezes, INTERVAL 120 HOUR) then
time_to_sec(timediff(DATE_ADD(@muszak_befejezes, INTERVAL 120 HOUR), DATE_ADD(@muszak_kezdes, INTERVAL 120 HOUR) ))  / 3600
WHEN
startdate>=DATE_ADD(@muszak_kezdes, INTERVAL 120 HOUR) and stopdate<=DATE_ADD(@muszak_befejezes, INTERVAL 120 HOUR) then
time_to_sec(timediff(stopdate, startdate ))  / 3600
else
time_to_sec(timediff(DATE_ADD(@muszak_befejezes, INTERVAL 120 HOUR), startdate ))  / 3600

end else 0 end) as SZO_R,

SUM(
case when startdate < DATE_ADD(@muszak_befejezes, INTERVAL 132 HOUR) and stopdate >DATE_ADD(@muszak_kezdes, INTERVAL 132 HOUR) then
CASE WHEN
startdate<=DATE_ADD(@muszak_kezdes, INTERVAL 132 HOUR) and stopdate<=DATE_ADD(@muszak_befejezes, INTERVAL 132 HOUR) then
time_to_sec(timediff(stopdate, DATE_ADD(@muszak_kezdes, INTERVAL 132 HOUR) ))  / 3600
WHEN
startdate<=DATE_ADD(@muszak_kezdes, INTERVAL 132 HOUR) and stopdate>DATE_ADD(@muszak_befejezes, INTERVAL 132 HOUR) then
time_to_sec(timediff(DATE_ADD(@muszak_befejezes, INTERVAL 132 HOUR), DATE_ADD(@muszak_kezdes, INTERVAL 132 HOUR) ))  / 3600
WHEN
startdate>=DATE_ADD(@muszak_kezdes, INTERVAL 132 HOUR) and stopdate<=DATE_ADD(@muszak_befejezes, INTERVAL 132 HOUR) then
time_to_sec(timediff(stopdate, startdate ))  / 3600
else
time_to_sec(timediff(DATE_ADD(@muszak_befejezes, INTERVAL 132 HOUR), startdate ))  / 3600

end else 0 end) as SZO_E,

SUM(
case when startdate < DATE_ADD(@muszak_befejezes, INTERVAL 144 HOUR) and stopdate >DATE_ADD(@muszak_kezdes, INTERVAL 144 HOUR) then
CASE WHEN
startdate<=DATE_ADD(@muszak_kezdes, INTERVAL 144 HOUR) and stopdate<=DATE_ADD(@muszak_befejezes, INTERVAL 144 HOUR) then
time_to_sec(timediff(stopdate, DATE_ADD(@muszak_kezdes, INTERVAL 144 HOUR) ))  / 3600
WHEN
startdate<=DATE_ADD(@muszak_kezdes, INTERVAL 144 HOUR) and stopdate>DATE_ADD(@muszak_befejezes, INTERVAL 144 HOUR) then
time_to_sec(timediff(DATE_ADD(@muszak_befejezes, INTERVAL 144 HOUR), DATE_ADD(@muszak_kezdes, INTERVAL 144 HOUR) ))  / 3600
WHEN
startdate>=DATE_ADD(@muszak_kezdes, INTERVAL 144 HOUR) and stopdate<=DATE_ADD(@muszak_befejezes, INTERVAL 144 HOUR) then
time_to_sec(timediff(stopdate, startdate ))  / 3600
else
time_to_sec(timediff(DATE_ADD(@muszak_befejezes, INTERVAL 144 HOUR), startdate ))  / 3600

end else 0 end) as VAS_R,

SUM(
case when startdate < DATE_ADD(@muszak_befejezes, INTERVAL 156 HOUR) and stopdate >DATE_ADD(@muszak_kezdes, INTERVAL 156 HOUR) then
CASE WHEN
startdate<=DATE_ADD(@muszak_kezdes, INTERVAL 156 HOUR) and stopdate<=DATE_ADD(@muszak_befejezes, INTERVAL 156 HOUR) then
time_to_sec(timediff(stopdate, DATE_ADD(@muszak_kezdes, INTERVAL 156 HOUR) ))  / 3600
WHEN
startdate<=DATE_ADD(@muszak_kezdes, INTERVAL 156 HOUR) and stopdate>DATE_ADD(@muszak_befejezes, INTERVAL 156 HOUR) then
time_to_sec(timediff(DATE_ADD(@muszak_befejezes, INTERVAL 156 HOUR), DATE_ADD(@muszak_kezdes, INTERVAL 156 HOUR) ))  / 3600
WHEN
startdate>=DATE_ADD(@muszak_kezdes, INTERVAL 156 HOUR) and stopdate<=DATE_ADD(@muszak_befejezes, INTERVAL 156 HOUR) then
time_to_sec(timediff(stopdate, startdate ))  / 3600
else
time_to_sec(timediff(DATE_ADD(@muszak_befejezes, INTERVAL 156 HOUR), startdate ))  / 3600

end else 0 end) as VAS_E




from downtimes left join downtime_types on downtimes.downtime_types_id = downtime_types.id left join stations on downtimes.stations_id = stations.id 
where stations.name=@sor group by Tipus ;