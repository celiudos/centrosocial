ALTER TABLE usuario
	ADD codigo_pessoa BIGINT(20);

ALTER TABLE usuario	
	ADD CONSTRAINT
	 FOREIGN KEY (codigo_pessoa) REFERENCES pessoa(codigo);