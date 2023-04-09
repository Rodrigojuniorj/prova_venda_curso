package repositories;

import Entities.Instrutor;
import factories.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstrutorRepository {
    private final String INSERT = String.join("\n", "", "INSERT INTO instrutor(nome, cpf)", "values (?, ?)");
    private final String LISTAR = String.join("\n","SELECT * FROM instrutor ORDER BY nome");
    private final String DELETE = String.join("\n", "", "DELETE FROM instrutor WHERE codigo = ?");
    private final String UPDATE = String.join("\n", "", "UPDATE instrutor SET nome = ?, cpf = ? WHERE codigo = ?");

    public void salvar(Instrutor instrutor) {
        try (Connection conn = ConnectionFactory.connection();
             PreparedStatement preparedStatement = conn.prepareStatement(INSERT);) {
            preparedStatement.setString(1, instrutor.getNome());
            preparedStatement.setString(2, instrutor.getCpf());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Instrutor> listar(){
        try (Connection conn = ConnectionFactory.connection();
             PreparedStatement preparedStatement = conn.prepareStatement(LISTAR);
             ResultSet resultSet = preparedStatement.executeQuery();
        ) {

            List<Instrutor> instrutorList = new ArrayList<>();
            while(resultSet.next()){
                Instrutor instrutor = new Instrutor();
                instrutor.setCodigo(resultSet.getInt("codigo"));
                instrutor.setNome(resultSet.getString("nome"));
                instrutor.setCpf(resultSet.getString("cpf"));
                instrutorList.add(instrutor);
            }
            return instrutorList;
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

    public void editar(Instrutor instrutor){
        try (Connection conn = ConnectionFactory.connection();
             PreparedStatement preparedStatement = conn.prepareStatement(UPDATE);) {
            preparedStatement.setString(1, instrutor.getNome());
            preparedStatement.setString(2, instrutor.getCpf());
            preparedStatement.setInt(3, instrutor.getCodigo());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
