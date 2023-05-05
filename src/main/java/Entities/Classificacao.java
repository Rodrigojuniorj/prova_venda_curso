package Entities;

import lombok.Data;

@Data
public class Classificacao {
    private Integer codigo;
    private String horario;
    private String titulo;
    private String descricao;
    private Integer nota;
    private Usuario usuario;
    private Curso curso;
}
