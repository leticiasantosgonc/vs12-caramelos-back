CREATE TABLE PRODUTO (
	ID_PRODUTO NUMBER(10,0) NOT NULL,
	NOME VARCHAR2(100) NOT NULL,
	DESCRICAO VARCHAR2(250) NOT NULL,
	MARCA VARCHAR2(100),
	QUANTIDADE NUMBER(10,0) NOT NULL,
	VEGETARIANO VARCHAR2(50),
	TAMANHO VARCHAR2(50),
	TIPO VARCHAR2(50) NOT NULL,
	PRECO NUMBER(7,2) NOT NULL,
	TIPO_IMG VARCHAR2(10),
	IMAGEM CLOB,
	DADOS_IMG BLOB,
	PRIMARY KEY (ID_PRODUTO)
	)
	CREATE SEQUENCE SEQ_PRODUTO
START WITH 1
INCREMENT BY 1
NOCACHE 
NOCYCLE;