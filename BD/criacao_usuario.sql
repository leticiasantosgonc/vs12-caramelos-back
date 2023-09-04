CREATE TABLE USUARIO(
	ID_USUARIO NUMBER(10) NOT NULL,
	LOGIN VARCHAR2(50) UNIQUE,
	SENHA VARCHAR2(80) NOT NULL,
	PRIMARY KEY (ID_USUARIO)
)

CREATE SEQUENCE SEQ_USUARIO
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;