package repositories;

import Entities.Usuario;
import factories.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {
    private final String INSERT = String.join("\n", "", "INSERT INTO usuario(nome, cpf, email, senha)", "values (?, ?, ?, ?)");
    private final String LISTAR = String.join("\n","SELECT * FROM usuario ORDER BY codigo");
    private final String DELETE = String.join("\n", "", "DELETE FROM usuario WHERE codigo = ?");
    private final String UPDATE = String.join("\n", "", "UPDATE usuario SET nome = ?, cpf = ?, email = ?, senha = ? WHERE codigo = ?");

    public void salvar(Usuario usuario) {
        try (Connection conn = ConnectionFactory.connection();
             PreparedStatement preparedStatement = conn.prepareStatement(INSERT);) {
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getCpf());
            preparedStatement.setString(3, usuario.getEmail());
            preparedStatement.setString(4, usuario.getSenha());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Usuario> listar(){
        try (Connection conn = ConnectionFactory.connection();
             PreparedStatement preparedStatement = conn.prepareStatement(LISTAR);
             ResultSet resultSet = preparedStatement.executeQuery();
        ) {

            List<Usuario> usuarioList = new ArrayList<>();
            while(resultSet.next()){
                Usuario usuario = new Usuario();
                usuario.setCodigo(resultSet.getInt("codigo"));
                usuario.setNome(resultSet.getString("nome"));
                usuario.setNome(resultSet.getString("cpf"));
                usuarioList.add(usuario);
            }
            return usuarioList;
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

    public void editar(Usuario usuario){
        try (Connection conn = ConnectionFactory.connection();
             PreparedStatement preparedStatement = conn.prepareStatement(UPDATE);) {
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getCpf());
            preparedStatement.setString(3, usuario.getEmail());
            preparedStatement.setString(4, usuario.getSenha());
            preparedStatement.setInt(5, usuario.getCodigo());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
