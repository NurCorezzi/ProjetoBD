CREATE DEFINER=`root`@`localhost` TRIGGER check_person_count BEFORE UPDATE ON ies  
FOR EACH ROW
BEGIN
	if ((SELECT count(*) FROM information_schema.COLUMNS 
		WHERE 	TABLE_SCHEMA = 'csv_bd' 
				AND TABLE_NAME = 'ies' 
				AND COLUMN_NAME = 'QT_DOCENTE_TOTAL') <> 0 ) then
	begin
		if (NEW.QT_DOCENTE_TOTAL <= 0 OR NEW.QT_ALUNO_TOTAL <= 0 OR NEW.QT_TEC_TOTAL <= 0) THEN
		begin
			SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'Valor de alunos, tecnicos e docentes não pode ser menor ou igual a zero ', MYSQL_ERRNO = 1001;	
		end;
        end if;
    end;
    end if;
END