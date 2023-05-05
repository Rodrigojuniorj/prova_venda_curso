package repositories;

import Entities.*;
import factories.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ClassificacaoRepository {
    private final String INSERT = String.join("\n", "",
            "INSERT INTO classificacao(horario, titulo, descricao, nota, usuario, curso) VALUES (?,?,?,?,?,?)");
    private final String LISTAR = String.join("\n", "",
            "SELECT c.codigo, c.horario, c.titulo, c.descricao, c.nota, " +
                    "u.codigo, u.nome, u.cpf, u.email, u.senha, " +
                    "cu.codigo, cu.nome, cu.carga_horaria, cu.preco_unitario," +
                    "i.codigo, i.nome, i.cpf FROM classificacao c " +
                    "inner join usuario u on u.codigo = c.usuario " +
                    "inner join curso cu on cu.codigo = c.curso " +
                    "inner join instrutor i on i.codigo = cu.instrutor " +
                    "ORDER BY c.horario;");
    private final String DELETE = String.join("\n", "",
            "DELETE FROM classificacao WHERE codigo = ?");
    private final String UPDATE = String.join("\n", "",
            "UPDATE classificacao SET horario = ?, titulo = ?, descricao = ?, nota = ?, usuario = ?, curso = ? WHERE codigo = ?");

    public void salvar(Classificacao classificacao) {
        try (Connection conn = ConnectionFactory.connection();
             PreparedStatement preparedStatement = conn.prepareStatement(INSERT);) {

            preparedStatement.setString(1, classificacao.getHorario());
            preparedStatement.setString(2, classificacao.getTitulo());
            preparedStatement.setString(3, classificacao.getDescricao());
            preparedStatement.setInt(4, classificacao.getNota());
            preparedStatement.setInt(5, classificacao.getUsuario().getCodigo());
            preparedStatement.setInt(6, classificacao.getCurso().getCodigo());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LocalDateTime horario = LocalDateTime.now();
            System.out.println(horario);
            throw new RuntimeException(e);
        }
    }

    public List<Classificacao> listar() {
        try (Connection conn = ConnectionFactory.connection();
             PreparedStatement preparedStatement = conn.prepareStatement(LISTAR);
             ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            List<Classificacao> classificacaoList = new ArrayList<>();
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

                Classificacao classificacao = new Classificacao();
                classificacao.setCodigo(resultSet.getInt("c.codigo"));
                classificacao.setHorario(resultSet.getString("c.horario"));
                classificacao.setTitulo(resultSet.getString("c.titulo"));
                classificacao.setDescricao(resultSet.getString("c.descricao"));
                classificacao.setNota(resultSet.getInt("c.nota"));
                classificacao.setUsuario(usuario);
                classificacao.setCurso(curso);

                classificacaoList.add(classificacao);
            }
            return classificacaoList;
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

    public void editar(Classificacao classificacao) {
        try (Connection conn = ConnectionFactory.connection();
             PreparedStatement preparedStatement = conn.prepareStatement(UPDATE);) {

            preparedStatement.setString(1, classificacao.getHorario());
            preparedStatement.setString(2, classificacao.getTitulo());
            preparedStatement.setString(3, classificacao.getDescricao());
            preparedStatement.setInt(4, classificacao.getNota());
            preparedStatement.setInt(5, classificacao.getUsuario().getCodigo());
            preparedStatement.setInt(6, classificacao.getCurso().getCodigo());
            preparedStatement.setInt(7, classificacao.getCodigo());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
