SELECT id,line ,datefrom, dateto, downtimename , whofixed, comments from downtimes_production where datefrom>=@_from and dateto<@_to  and line=@line; 