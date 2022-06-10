package besysoft.tiendaRest.exception;

import javax.management.AttributeNotFoundException;


public class EntityNotFoundException extends AttributeNotFoundException {

    public EntityNotFoundException(String message) {
        super(message);
    }
}
