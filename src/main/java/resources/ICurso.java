package resources;

import Entities.Curso;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface ICurso {
    @WebMethod
    void salvarCurso(@WebParam (name = "curso") Curso curso);

    @WebMethod
    List<Curso> listarCurso();

    @WebMethod
    void deleteCurso (@WebParam(name = "codigo") int codigo);

    @WebMethod
    void editarCurso (@WebParam(name = "curso") Curso curso);
}
