package Entities;

import lombok.Data;

@Data
public class Curso {
    private Integer codigo;
    private String nome;
    private Integer cargaHoraria;
    private Double precoUnitario;
    private Instrutor instrutor;
}
