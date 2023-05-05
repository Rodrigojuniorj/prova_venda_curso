package resources;

import Entities.Curso;
import jakarta.jws.WebService;
import repositories.CursoRepository;
import java.util.List;

@WebService(endpointInterface = "resources.ICurso")
public class CursoResource implements ICurso {
    private CursoRepository cursoRepository = new CursoRepository();

    @Override
    public void salvarCurso(Curso curso) {
        cursoRepository.salvar(curso);
    }

    @Override
    public List<Curso> listarCurso() {
        List<Curso> cursoList = cursoRepository.listar();
        return cursoList;
    }

    @Override
    public void deleteCurso(int codigo) {
        cursoRepository.delete(codigo);
    }

    @Override
    public void editarCurso(Curso curso) {
        cursoRepository.editar(curso);
    }
}
