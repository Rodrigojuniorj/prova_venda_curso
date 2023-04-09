package resources;

import Entities.Instrutor;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface IInstrutor {
    @WebMethod
    void salvarInstrutor(@WebParam (name = "instrutor") Instrutor instrutor);

    @WebMethod
    List<Instrutor> listarInstrutor();

    @WebMethod
    void deleteInstrutor (@WebParam(name = "codigo") int codigo);

    @WebMethod
    void editarInstrutor (@WebParam(name = "codigo") Instrutor instrutor);
}
