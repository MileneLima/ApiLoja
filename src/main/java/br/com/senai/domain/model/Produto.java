package br.com.senai.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String nomeProduto;

	@NotNull
	private int quantidade;

	@NotNull
	private double valorUnitario;

	@NotNull
	private double valorTotalEmEstoque;

}
