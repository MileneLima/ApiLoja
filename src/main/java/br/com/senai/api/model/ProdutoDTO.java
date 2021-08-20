package br.com.senai.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProdutoDTO {

    private Long id;

    private String nomeProduto;

    private int quantidade;

    private double valorUnitario;

    private double valorTotalEmEstoque;


}
