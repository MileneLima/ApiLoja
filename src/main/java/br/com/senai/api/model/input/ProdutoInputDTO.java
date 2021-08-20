package br.com.senai.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoInputDTO {

    @NotNull
    private String nomeProduto;

    @NotNull
    private int quantidade;

    @NotNull
    private double valorUnitario;

}
