CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `ies_pessoal` AS
    SELECT 
        `i`.`CO_IES` AS `CO_IES`,
        `i`.`NO_IES` AS `NO_IES`,
        `i`.`DS_CATEGORIA_ADMINISTRATIVA` AS `DS_CATEGORIA_ADMINISTRATIVA`,
        `i`.`QT_TEC_TOTAL` AS `QT_TEC_TOTAL`,
        (SELECT 
                COUNT(0)
            FROM
                `docente` `d`
            WHERE
                ((`d`.`CO_IES_DOCENTE` = `i`.`CO_IES`)
                    AND (`d`.`DS_SITUACAO_DOCENTE` = 'Em exerc?cio'))) AS `QT_DOCENTE_TOTAL`,
        (SELECT 
                COUNT(0)
            FROM
                `aluno` `a`
            WHERE
                ((`a`.`CO_IES_ALUNO` = `i`.`CO_IES`)
                    AND (`a`.`DS_ALUNO_SITUACAO` = 'Cursando'))) AS `QT_ALUNO_TOTAL`
    FROM
        `ies` `i`