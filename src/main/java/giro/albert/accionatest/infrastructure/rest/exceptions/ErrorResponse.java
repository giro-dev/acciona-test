package giro.albert.accionatest.infrastructure.rest.exceptions;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorResponse {
    private String error;
}
