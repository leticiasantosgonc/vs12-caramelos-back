CREATE TABLE PEDIDO 
(	
   ID_PEDIDO NUMBER(10,0) NOT NULL, 
   QUANTIDADE NUMBER(10,0) NOT NULL , 
   PRECO NUMBER(7,2) NOT NULL, 
   CPF VARCHAR2(11 CHAR), 
   DATA DATE NOT NULL , 
   OBSERVACAO VARCHAR2(255), 
   STATUS VARCHAR2(8) NOT NULL , 
   ID_SESSION VARCHAR2(70), 
   GAME VARCHAR2(5), 
   PRIMARY KEY (ID_PEDIDO)
);

CREATE SEQUENCE SEQ_PEDIDO
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;