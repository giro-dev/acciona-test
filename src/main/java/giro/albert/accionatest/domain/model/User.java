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
    private Long userId;
    private String localization;
    @JsonProperty("userName")
    private String screenName;
    @JsonIgnore
    private int followersCount;
}
