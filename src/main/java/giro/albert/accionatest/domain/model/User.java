package giro.albert.accionatest.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;


import java.util.Set;

@Data
@Builder
public class User {
    @JsonProperty("userName")
    private String screenName;
    private String localization;
    @JsonIgnore
    private int followersCount;
}
