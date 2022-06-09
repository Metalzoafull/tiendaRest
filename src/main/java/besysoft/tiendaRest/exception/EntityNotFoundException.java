package besysoft.tiendaRest.exception;

import lombok.Data;

import javax.management.AttributeNotFoundException;

@Data
public class EntityNotFoundException extends AttributeNotFoundException {


    public EntityNotFoundException(String message) {
        super(message);
    }
}
