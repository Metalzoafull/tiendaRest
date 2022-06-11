package besysoft.tiendaRest.exception;


import javax.management.AttributeNotFoundException;
//Exepcion por si ya existe una entidad con ese codigo
public class EntityCodeException extends AttributeNotFoundException {

    public EntityCodeException(String message) {
        super(message);
    }
}
