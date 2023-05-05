package resources;

import Entities.Usuario;
import jakarta.jws.WebService;
import repositories.UsuarioRepository;
import java.util.List;

@WebService(endpointInterface = "resources.IUsuario")
public class UsuarioResource implements IUsuario {
    private UsuarioRepository usuarioRepository = new UsuarioRepository();

    @Override
    public void salvarUsuario(Usuario usuario) {
        usuarioRepository.salvar(usuario);
    }

    @Override
    public List<Usuario> listarUsuario() {
        List<Usuario> usuarioList = usuarioRepository.listar();
        return usuarioList;
    }

    @Override
    public void deleteUsuario(int codigo) {
        usuarioRepository.delete(codigo);
    }

    @Override
    public void editarUsuario(Usuario usuario) {
        usuarioRepository.editar(usuario);
    }
}
