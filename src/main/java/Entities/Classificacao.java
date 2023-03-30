package Entities;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Classificacao {
    private Integer codigo;
    private LocalDate horario;
    private String titulo;
    private String descricao;
    private Integer nota;
    private Usuario usuario;
    private Curso curso;
}
