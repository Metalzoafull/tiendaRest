package besysoft.tiendaRest.controller;

import besysoft.tiendaRest.dto.ErrorDTO;
import besysoft.tiendaRest.exception.AtributeNotMeetRequirements;
import besysoft.tiendaRest.exception.EntityCodeException;
import besysoft.tiendaRest.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler {


    //Controler para manejar las Exepciones
    //Si el body que se agrega no cumple con los requisitos minimos puestos en su clase aparecera este error
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> methodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException ex) {

        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        StringBuilder errorMessage = new StringBuilder();
        fieldErrors.forEach(f -> errorMessage.append(f.getField()).append(": ").append(f.getDefaultMessage()).append(", "));

        ErrorDTO errorDTO = new ErrorDTO(HttpStatus.BAD_REQUEST.value(), errorMessage.toString(), request.getRequestURI());
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);

    }


    //Si no se encuentra la entidad se manejara este Error
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDTO> EntityNotFoundException(HttpServletRequest request, EntityNotFoundException ex){
        ErrorDTO errorDTO = ErrorDTO.builder().statusCode(HttpStatus.NOT_FOUND.value()).message(ex.getMessage()).uriRequested(request.getRequestURI()).build();
        return new ResponseEntity<>(errorDTO,HttpStatus.NOT_FOUND);

    }


    @ExceptionHandler(AtributeNotMeetRequirements.class)
    public ResponseEntity<ErrorDTO> AtributeNotMeetRequirements(HttpServletRequest request, AtributeNotMeetRequirements ex){
        ErrorDTO errorDTO = ErrorDTO.builder().statusCode(HttpStatus.BAD_REQUEST.value()).message(ex.getMessage()).uriRequested(request.getRequestURI()).build();
        return new ResponseEntity<>(errorDTO,HttpStatus.BAD_REQUEST);

    }

    //Si el codigo ya existe en lista de vendedores o la lista de productos se manejara este errror
    @ExceptionHandler(EntityCodeException.class)
    public ResponseEntity<ErrorDTO> EntityCodeException(HttpServletRequest request, EntityCodeException ex){
        ErrorDTO errorDTO = ErrorDTO.builder().statusCode(HttpStatus.BAD_REQUEST.value()).message(ex.getMessage()).uriRequested(request.getRequestURI()).build();
        return new ResponseEntity<>(errorDTO,HttpStatus.BAD_REQUEST);

    }


}
