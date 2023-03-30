package Entities;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Compra {
    private Integer codigo;
    private LocalDateTime horario;
    private Double precoTotal;
    private Usuario usuario;
    private Curso curso;
}
