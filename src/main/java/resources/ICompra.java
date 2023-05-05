package resources;

import Entities.Compra;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface ICompra {
    @WebMethod
    void salvarCompra(@WebParam (name = "compra") Compra compra);

    @WebMethod
    List<Compra> listarCompra();

    @WebMethod
    void deleteCompra (@WebParam(name = "codigo") int codigo);

    @WebMethod
    void editarCompra (@WebParam(name = "codigo") Compra compra);
}
