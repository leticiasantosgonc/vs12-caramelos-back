CREATE TABLE "EQUIPE_PF_4"."ITEM_PEDIDO" 
   (
   ID_PEDIDO NUMBER(10,0) NOT NULL, 
   ID_PRODUTO NUMBER(10,0) NOT NULL,
   FOREIGN KEY (ID_PEDIDO) REFERENCES PEDIDO(ID_PEDIDO),
   FOREIGN KEY (ID_PRODUTO) REFERENCES PRODUTO(ID_PRODUTO)
   )