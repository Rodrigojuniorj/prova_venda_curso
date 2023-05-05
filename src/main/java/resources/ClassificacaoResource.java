package resources;

import Entities.Classificacao;
import Entities.Compra;
import jakarta.jws.WebService;
import repositories.ClassificacaoRepository;

import java.util.List;

@WebService(endpointInterface = "resources.IClassificacao")
public class ClassificacaoResource implements IClassificacao {
    private ClassificacaoRepository classificacaoRepository = new ClassificacaoRepository();

    @Override
    public void salvarClassificacao(Classificacao classificacao) {
        classificacaoRepository.salvar(classificacao);
    }

    @Override
    public List<Classificacao> listarClassificacao() {
        List<Classificacao> classificacaoList = classificacaoRepository.listar();
        return classificacaoList;
    }

    @Override
    public void deleteClassificacao(int codigo) {
        classificacaoRepository.delete(codigo);
    }

    @Override
    public void editarClassificacao(Classificacao classificacao) {
        classificacaoRepository.editar(classificacao);
    }
}
