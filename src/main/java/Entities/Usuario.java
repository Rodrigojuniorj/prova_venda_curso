package Entities;

import lombok.Data;

@Data
public class Usuario {
    private Integer codigo;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
}
