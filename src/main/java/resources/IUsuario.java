package resources;

import Entities.Usuario;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface IUsuario {
    @WebMethod
    void salvarUsuario(@WebParam (name = "usuario") Usuario usuario);

    @WebMethod
    List<Usuario> listarUsuario();

    @WebMethod
    void deleteUsuario(@WebParam(name = "codigo") int codigo);

    @WebMethod
    void editarUsuario(@WebParam(name = "usuario") Usuario usuario);
}
