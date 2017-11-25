CREATE DEFINER=`root`@`localhost` PROCEDURE `update_ies_table`()
BEGIN
	if ((SELECT count(*) FROM information_schema.COLUMNS 
		WHERE 	TABLE_SCHEMA = 'csv_bd' 
				AND TABLE_NAME = 'ies' 
				AND COLUMN_NAME = 'QT_DOCENTE_TOTAL') = 0 ) then
	begin
		ALTER TABLE ies 
		ADD COLUMN QT_DOCENTE_TOTAL bigint(21) default null,
		ADD COLUMN QT_ALUNO_TOTAL bigint(21) default null;
    end;
    end if;
    
    begin
		declare i int(4);
        select count(*) into i from ies where QT_DOCENTE_TOTAL is null;
        
        if(i <> 0)then
			BEGIN
			CREATE TABLE if not exists temp_tab as select * from ies_pessoal;
			
			while(i != 0)do
			begin
				declare cod_ies int(4);
				
				select CO_IES into cod_ies from ies where QT_DOCENTE_TOTAL is null limit 0,1;
				
				update ies
				set QT_DOCENTE_TOTAL = (select QT_DOCENTE_TOTAL from temp_tab where CO_IES = cod_ies),
					QT_ALUNO_TOTAL = (select QT_ALUNO_TOTAL from temp_tab where CO_IES = cod_ies)
				where CO_IES = cod_ies;
				
				select i-1 into i;
			end;
			end while;
			
			drop table temp_tab;
			end;
		end if;
        
    end;
    
END