package br.com.senai.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UsuarioInputDTO {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String senha;

    @NotNull
    private int tipo;

}
