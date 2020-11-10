CREATE TABLE cliente (  
id int NOT NULL AUTO_INCREMENT,  
cpf varchar(15) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,  
nome varchar(30) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,  
sobrenome varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,  
PRIMARY KEY (id),  UNIQUE KEY cpf_UNIQUE (cpf)) 
ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE pedido (  
id int NOT NULL AUTO_INCREMENT,  
data datetime NOT NULL,  
id_cliente int DEFAULT NULL,  
PRIMARY KEY (id),  KEY id_cliente_idx (id_cliente),  
CONSTRAINT id_cliente FOREIGN KEY (id_cliente) REFERENCES cliente (id)) 
ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE item_do_pedido (  
id_pedido int NOT NULL,  
id_produto int NOT NULL,  
qtdade int NOT NULL,  
PRIMARY KEY (id_pedido,id_produto)) 
ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE produto (  
descricao varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,  
PRIMARY KEY (id)) 
ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE usuario (
  login int NOT NULL,
  senha varchar(45) DEFAULT NULL,
  PRIMARY KEY (login)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


