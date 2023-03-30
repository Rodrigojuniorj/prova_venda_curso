package repositories;

import factories.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstrutorRepository {
    private final String INSERT = String.join("\n", "", "INSERT INTO categorias(nome)", "values (?)");
    private final String LISTAR = String.join("\n","SELECT * FROM categorias ORDER BY nome");
    private final String DELETE = String.join("\n", "", "DELETE FROM categorias WHERE codigo = ?");
}
