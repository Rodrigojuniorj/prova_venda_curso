import jakarta.xml.ws.Endpoint;
import resources.*;

public class Aplicacao {
    public static void main(String[] args) {
        Endpoint.publish (
                "http://localhost:8080/curso-ws",
                new CursoResource()
        );
        Endpoint.publish (
                "http://localhost:8080/instrutor-ws",
                new InstrutorResource()
        );
        Endpoint.publish (
                "http://localhost:8080/usuario-ws",
                new UsuarioResource()
        );
        Endpoint.publish (
                "http://localhost:8080/compra-ws",
                new CompraResource()
        );
        Endpoint.publish (
                "http://localhost:8080/classificacao-ws",
                new ClassificacaoResource()
        );
    }
}
