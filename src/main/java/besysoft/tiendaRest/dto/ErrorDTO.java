package besysoft.tiendaRest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {

    //Un constructor de errores, guarda el status code, mensaje y la ruta que se llamo
    @JsonProperty("status_code")
    private String statusCode;
    @JsonProperty("message")
    private String message;
    @JsonProperty("uri")
    private String uriRequested;
}
