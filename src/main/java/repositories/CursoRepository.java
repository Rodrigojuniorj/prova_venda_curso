package repositories;

import Entities.Curso;
import Entities.Instrutor;
import factories.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoRepository {
    private final String INSERT = String.join("\n", "", "INSERT INTO curso (nome, carga_horaria, preco_unitario, instrutor)", "values (?, ?, ?, ?)");
    private final String LISTAR = String.join("\n","SELECT c.codigo, c.nome, c.carga_horaria, c.preco_unitario, i.codigo, i.nome, i.cpf FROM curso c inner join instrutor i on i.codigo = c.instrutor  ORDER BY c.nome");
    private final String DELETE = String.join("\n", "", "DELETE FROM curso WHERE codigo = ?");
    private final String UPDATE = String.join("\n", "", "UPDATE curso SET nome = ?, carga_horaria = ?, preco_unitario = ?, instrutor = ? WHERE codigo = ?");

    public void salvar(Curso curso) {
        try (Connection conn = ConnectionFactory.connection();
             PreparedStatement preparedStatement = conn.prepareStatement(INSERT);) {

            preparedStatement.setString(1, curso.getNome());
            preparedStatement.setInt(2, curso.getCargaHoraria());
            preparedStatement.setDouble(3, curso.getPrecoUnitario());
            preparedStatement.setInt(4, curso.getInstrutor().getCodigo());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Curso> listar(){
        try (Connection conn = ConnectionFactory.connection();
             PreparedStatement preparedStatement = conn.prepareStatement(LISTAR);
             ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            List<Curso> cursoList = new ArrayList<>();
            while(resultSet.next()){
                Instrutor instrutor = new Instrutor();
                instrutor.setCodigo(resultSet.getInt("i.codigo"));
                instrutor.setNome(resultSet.getString("i.nome"));
                instrutor.setCpf(resultSet.getString("i.cpf"));


                Curso curso = new Curso();
                curso.setCodigo(resultSet.getInt("c.codigo"));
                curso.setNome(resultSet.getString("c.nome"));
                curso.setCargaHoraria(resultSet.getInt("c.carga_horaria"));
                curso.setPrecoUnitario(resultSet.getDouble("c.preco_unitario"));
                curso.setInstrutor(instrutor);

                cursoList.add(curso);
            }
            return cursoList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int codigo){
        try (Connection conn = ConnectionFactory.connection();
             PreparedStatement preparedStatement = conn.prepareStatement(DELETE);) {
            preparedStatement.setInt(1, codigo);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editar(Curso curso){
        try (Connection conn = ConnectionFactory.connection();
             PreparedStatement preparedStatement = conn.prepareStatement(UPDATE);) {
            preparedStatement.setString(1, curso.getNome());
            preparedStatement.setInt(2, curso.getCargaHoraria());
            preparedStatement.setDouble(3, curso.getPrecoUnitario());
            preparedStatement.setInt(4, curso.getInstrutor().getCodigo());
            preparedStatement.setInt(5, curso.getCodigo());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
