import jakarta.xml.ws.Endpoint;
import resources.InstrutorResource;
public class Aplicacao {
    public static void main(String[] args) {
        Endpoint.publish (
                "http://localhost:8080/instrutor-ws",
                new InstrutorResource()
        );
    }
}
