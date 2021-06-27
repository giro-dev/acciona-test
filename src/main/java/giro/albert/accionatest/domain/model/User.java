package giro.albert.accionatest.domain.model;

import lombok.Builder;
import lombok.Data;


import java.util.Set;

@Data
@Builder
public class User {
    private Long userId;
    private String name;
    private String screenName;
    private int followersCount;
}
