CREATE TABLE registroprofissional (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    sigla VARCHAR(10) NOT NULL,
    nome VARCHAR(80) NOT NULL 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO registroprofissional (sigla, nome) VALUES ('CORECON', 'Conselho Regional de Economia');
INSERT INTO registroprofissional (sigla, nome) VALUES ('COREN', 'Conselho Regional de Enfermagem');
INSERT INTO registroprofissional (sigla, nome) VALUES ('CRA', 'Conselho Regional de Administração');
INSERT INTO registroprofissional (sigla, nome) VALUES ('CRBIO', 'Conselho Regional de Biololgia');
INSERT INTO registroprofissional (sigla, nome) VALUES ('CRBM', 'Conselho Regional de Biomedicina');
INSERT INTO registroprofissional (sigla, nome) VALUES ('CRC', 'Conselho Regional de Contabilidade');
INSERT INTO registroprofissional (sigla, nome) VALUES ('CREA', 'Conselho Regional de Engenharia e Arquitetura');
INSERT INTO registroprofissional (sigla, nome) VALUES ('CRC', 'Conselho Regional de Contabilidade');
INSERT INTO registroprofissional (sigla, nome) VALUES ('CRECI', 'Conselho Regional de Corretores de Imóveis');
INSERT INTO registroprofissional (sigla, nome) VALUES ('CREFITO', 'Conselho Regional de Fisioterapia e Terapia Ocupacional');
INSERT INTO registroprofissional (sigla, nome) VALUES ('CRM', 'Conselho Regional de Medicina');
INSERT INTO registroprofissional (sigla, nome) VALUES ('CRF', 'Conselho Regional de Farmácia');
INSERT INTO registroprofissional (sigla, nome) VALUES ('CRFA', 'Conselho Regional de Fonoaudiologia');
INSERT INTO registroprofissional (sigla, nome) VALUES ('CRMV', 'Conselho Regional de Medicina Veterinária');
INSERT INTO registroprofissional (sigla, nome) VALUES ('CRN', 'Conselho Regional de Nutricionistas');
INSERT INTO registroprofissional (sigla, nome) VALUES ('CRO', 'Conselho Regional de Odontologia');
INSERT INTO registroprofissional (sigla, nome) VALUES ('CRP', 'Conselho Regional de Psicologia');
INSERT INTO registroprofissional (sigla, nome) VALUES ('OAB', 'Ordem Dos Advogados do Brasil');