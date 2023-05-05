package repositories;

import Entities.Compra;
import Entities.Curso;
import Entities.Instrutor;
import Entities.Usuario;
import factories.ConnectionFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CompraRepository {
    private final String INSERT = String.join("\n", "",
            "INSERT INTO compra(horario, preco_total, usuario, curso) VALUES (?,?, ?, ?)");
    private final String LISTAR = String.join("\n", "",
            "SELECT c.codigo, c.horario, c.preco_total, u.codigo, u.nome, u.cpf, u.nome, u.email, u.senha," +
                    "cu.codigo, cu.nome, cu.carga_horaria, cu.preco_unitario, cu.instrutor," +
                    "i.codigo, i.nome, i.cpf  FROM compra c " +
                    "inner join usuario u on u.codigo = c.usuario " +
                    "inner join curso cu on cu.codigo = c.curso " +
                    "inner join instrutor i on i.codigo = cu.instrutor ORDER BY c.horario");
    private final String DELETE = String.join("\n", "",
            "DELETE FROM compra WHERE codigo = ?");
    private final String UPDATE = String.join("\n", "",
            "UPDATE compra SET horario = ?, preco_total = ?, usuario = ?, curso = ? WHERE codigo = ?");

    public void salvar(Compra compra) {
        try (Connection conn = ConnectionFactory.connection();
             PreparedStatement preparedStatement = conn.prepareStatement(INSERT);) {

            preparedStatement.setString(1, compra.getHorario());
            preparedStatement.setDouble(2, compra.getPrecoTotal());
            preparedStatement.setInt(3, compra.getUsuario().getCodigo());
            preparedStatement.setInt(4, compra.getCurso().getCodigo());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LocalDateTime horario = LocalDateTime.now();
            System.out.println(horario);
            throw new RuntimeException(e);
        }
    }

    public List<Compra> listar() {
        try (Connection conn = ConnectionFactory.connection();
             PreparedStatement preparedStatement = conn.prepareStatement(LISTAR);
             ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            List<Compra> compraList = new ArrayList<>();
            while (resultSet.next()) {
                Instrutor instrutor = new Instrutor();
                instrutor.setCodigo(resultSet.getInt("i.codigo"));
                instrutor.setNome(resultSet.getString("i.nome"));
                instrutor.setCpf(resultSet.getString("i.cpf"));

                Curso curso = new Curso();
                curso.setCodigo(resultSet.getInt("cu.codigo"));
                curso.setNome(resultSet.getString("cu.nome"));
                curso.setCargaHoraria(resultSet.getInt("cu.carga_horaria"));
                curso.setPrecoUnitario(resultSet.getDouble("cu.preco_unitario"));
                curso.setInstrutor(instrutor);

                Usuario usuario = new Usuario();
                usuario.setCodigo(resultSet.getInt("u.codigo"));
                usuario.setNome(resultSet.getString("u.nome"));
                usuario.setCpf(resultSet.getString("u.cpf"));
                usuario.setEmail(resultSet.getString("u.email"));
                usuario.setSenha(resultSet.getString("u.senha"));

                Compra compra = new Compra();
                compra.setCodigo(resultSet.getInt("c.codigo"));
                compra.setHorario(resultSet.getString("c.horario"));
                compra.setPrecoTotal(resultSet.getDouble("c.preco_total"));
                compra.setUsuario(usuario);
                compra.setCurso(curso);

                compraList.add(compra);
            }
            return compraList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int codigo) {
        try (Connection conn = ConnectionFactory.connection();
             PreparedStatement preparedStatement = conn.prepareStatement(DELETE);) {
            preparedStatement.setInt(1, codigo);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editar(Compra compra) {
        try (Connection conn = ConnectionFactory.connection();
             PreparedStatement preparedStatement = conn.prepareStatement(UPDATE);) {

            preparedStatement.setString(1, compra.getHorario());
            preparedStatement.setDouble(2, compra.getPrecoTotal());
            preparedStatement.setInt(3, compra.getUsuario().getCodigo());
            preparedStatement.setInt(4, compra.getCurso().getCodigo());
            preparedStatement.setInt(5, compra.getCodigo());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
