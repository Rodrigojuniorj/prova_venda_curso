package resources;

import Entities.Compra;
import Entities.Curso;
import jakarta.jws.WebService;
import repositories.CompraRepository;

import java.util.List;

@WebService(endpointInterface = "resources.ICompra")
public class CompraResource implements ICompra {
    private CompraRepository compraRepository = new CompraRepository();

    @Override
    public void salvarCompra(Compra compra) {
        compraRepository.salvar(compra);
    }

    @Override
    public List<Compra> listarCompra() {
        List<Compra> compraList = compraRepository.listar();
        return compraList;
    }

    @Override
    public void deleteCompra(int codigo) {
        compraRepository.delete(codigo);
    }

    @Override
    public void editarCompra(Compra compra) {
        compraRepository.editar(compra);
    }
}
