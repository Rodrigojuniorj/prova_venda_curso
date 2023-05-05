package Entities;

import lombok.Data;


@Data
public class Compra {
    private Integer codigo;
    private String horario;
    private Double precoTotal;
    private Usuario usuario;
    private Curso curso;
}
