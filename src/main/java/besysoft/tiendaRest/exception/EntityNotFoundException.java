package besysoft.tiendaRest.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import javax.management.AttributeNotFoundException;

//Exepcion creada para funcionar como un entity not found, en caso de que no se encuentra la entidad en alguna peticion
@Data
public class EntityNotFoundException extends AttributeNotFoundException {

    private String code;
    private HttpStatus status;

    public EntityNotFoundException(String message, String code, HttpStatus status) {
        super(message);
        this.code = code;
        this.status = status;
    }
}
