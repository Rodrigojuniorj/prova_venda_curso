package resources;

import Entities.Classificacao;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface IClassificacao {
    @WebMethod
    void salvarClassificacao(@WebParam(name = "classificacao") Classificacao classificacao);

    @WebMethod
    List<Classificacao> listarClassificacao();

    @WebMethod
    void deleteClassificacao(@WebParam(name = "codigo") int codigo);

    @WebMethod
    void editarClassificacao(@WebParam(name = "classificacao") Classificacao classificacao);
}
