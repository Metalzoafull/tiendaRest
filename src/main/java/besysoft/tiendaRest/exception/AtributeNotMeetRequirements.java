package besysoft.tiendaRest.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class AtributeNotMeetRequirements extends Exception {

    private String code;
    private HttpStatus status;

    public AtributeNotMeetRequirements(String message, String code, HttpStatus status) {
        super(message);
        this.code = code;
        this.status = status;
    }
}
