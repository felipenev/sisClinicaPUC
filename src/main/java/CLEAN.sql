
--TABLES--
DROP TABLE IF EXISTS agendamedico CASCADE;
DROP TABLE IF EXISTS dataatendimento CASCADE;
DROP TABLE IF EXISTS medicamento CASCADE;
DROP TABLE IF EXISTS medico CASCADE;
DROP TABLE IF EXISTS paciente CASCADE;
DROP TABLE IF EXISTS receitamedica CASCADE;
DROP TABLE IF EXISTS receitamedica_medicamento CASCADE;
DROP TABLE IF EXISTS tipoexame CASCADE;
DROP TABLE IF EXISTS recepcionista CASCADE;
DROP TABLE IF EXISTS usuario CASCADE;
DROP TABLE IF EXISTS exame CASCADE;
DROP TABLE IF EXISTS exame_tipoexame CASCADE;
DROP TABLE IF EXISTS examesolicitado CASCADE;
DROP TABLE IF EXISTS agendamedico_dataatendimento CASCADE;
DROP TABLE IF EXISTS consulta CASCADE;
DROP TABLE IF EXISTS historico CASCADE;


DROP TABLE IF EXISTS exame_examesolicitado CASCADE;
DROP TABLE IF EXISTS medico_agendamedico CASCADE;

--SEQUENCES--
DROP SEQUENCE IF EXISTS hibernate_sequence;
DROP SEQUENCE IF EXISTS sequence_solicitacao_exame;
DROP SEQUENCE IF EXISTS sequence_receitamedica;
DROP SEQUENCE IF EXISTS sequence_exame;
DROP SEQUENCE IF EXISTS sequence_consulta;
DROP SEQUENCE IF EXISTS sequence_historico;
DROP SEQUENCE IF EXISTS sequence_usuario;