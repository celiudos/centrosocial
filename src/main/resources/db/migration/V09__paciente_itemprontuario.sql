CREATE TABLE paciente (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    codigo_pessoa BIGINT(20) NOT NULL,
    fone_adicional VARCHAR(20),
    fone_recado VARCHAR(20),
    fone_emergencia VARCHAR(20),    
    FOREIGN KEY (codigo_pessoa) REFERENCES pessoa(codigo),
    UNIQUE (codigo_pessoa)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE itemprontuario (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    codigo_paciente BIGINT(20) NOT NULL,
    codigo_tipoitemprontuario BIGINT(20) NOT NULL,
    valor VARCHAR(255) NULL,
    data_lancamento DATETIME NOT NULL DEFAULT NOW(),
    FOREIGN KEY (codigo_paciente) REFERENCES paciente(codigo),
    FOREIGN KEY (codigo_tipoitemprontuario) REFERENCES tipoitemprontuario(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO paciente (codigo_pessoa, fone_adicional, fone_recado, fone_emergencia) VALUES (1, '(61) 3367-4792', '(61) 3273-5000', '(61) 3273-1558');
INSERT INTO paciente (codigo_pessoa, fone_adicional, fone_recado, fone_emergencia) VALUES (2, '(61) 3367-4792', '(61) 3273-5000', '(61) 3273-1558');
INSERT INTO paciente (codigo_pessoa, fone_adicional, fone_recado, fone_emergencia) VALUES (3, '(61) 3367-4792', '(61) 3273-5000', '(61) 3273-1558');

INSERT INTO itemprontuario (codigo_paciente, codigo_tipoitemprontuario, valor) VALUES (3, 1, "Saudável");
INSERT INTO itemprontuario (codigo_paciente, codigo_tipoitemprontuario, valor) VALUES (3, 3, "Equilibrado, faz amigos, notas boas.");