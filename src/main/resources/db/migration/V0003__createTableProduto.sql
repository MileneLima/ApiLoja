CREATE TABlE produto(
	id int not null auto_increment,
	nome_produto varchar(45) not null,
	quantidade int not null,
	valor_unitario decimal(10,2) not null,
	valor_total_em_estoque decimal(10,2) not null,
	PRIMARY KEY (id)
);
