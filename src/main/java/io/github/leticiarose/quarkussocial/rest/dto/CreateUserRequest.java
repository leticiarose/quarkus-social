package io.github.leticiarose.quarkussocial.rest.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateUserRequest {
    @NotBlank(message= "Name is required")//verifica se a string é nula ou vazia - o vazio no java é contado como caracter, logo uma string " " é como se possuisse informação e isso não deve ser válido para o campo
    public String name;
    @NotNull (message = "Age is required")
    public Integer age;


}
