package giro.albert.accionatest.infrastructure.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatchRequestBody {
    private Boolean validated;
}
