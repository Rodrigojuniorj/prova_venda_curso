package resources;

import Entities.Instrutor;
import jakarta.jws.WebService;
import repositories.InstrutorRepository;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import java.util.List;

@WebService(endpointInterface = "resources.IInstrutor")
public class InstrutorResource implements IInstrutor{
    private InstrutorRepository instrutorRepository = new InstrutorRepository();
    @Override
    public void salvarInstrutor(Instrutor instrutor) {
        instrutorRepository.salvar(instrutor);
    }

    @Override
    public List<Instrutor> listarInstrutor() {
        List<Instrutor> instrutorList = instrutorRepository.listar();
        return instrutorList;
    }

    @Override
    public void deleteInstrutor(int codigo) {
        instrutorRepository.delete(codigo);
    }

    @Override
    public void editarInstrutor(Instrutor instrutor) {
        instrutorRepository.editar(instrutor);
    }

}
