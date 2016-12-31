CREATE TABLE pessoa (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(80) NOT NULL,
    sexo VARCHAR(1) NOT NULL,
    data_nascimento DATE NOT NULL,
    codigo_naturalidade BIGINT(20),
    nome_pai VARCHAR(80),
    nome_mae VARCHAR(80),
    estado_civil INT(11),
    escolaridade INT(11),
    profissao VARCHAR(80),
    tipo_documento INT(11),
    numero_documento VARCHAR(40),
    telefone VARCHAR(20) NOT NULL,
    email VARCHAR(50) NOT NULL,
    logradouro VARCHAR(50),
    numero VARCHAR(15),
    complemento VARCHAR(20),
    cep VARCHAR(15),
    codigo_cidade BIGINT(20),
    completo BIT(1) NOT NULL DEFAULT FALSE,
    FOREIGN KEY (codigo_cidade) REFERENCES cidade(codigo),
    FOREIGN KEY (codigo_naturalidade) REFERENCES cidade(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE pessoa
	ADD INDEX(numero_documento);
	
ALTER TABLE pessoa
	ADD INDEX(nome);	