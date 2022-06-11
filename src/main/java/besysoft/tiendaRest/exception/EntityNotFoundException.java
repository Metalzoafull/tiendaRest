package besysoft.tiendaRest.exception;

import javax.management.AttributeNotFoundException;

//Exepcion creada para funcionar como un entity not found, en caso de que no se encuentra la entidad en alguna peticion
public class EntityNotFoundException extends AttributeNotFoundException {

    public EntityNotFoundException(String message) {
        super(message);
    }
}
