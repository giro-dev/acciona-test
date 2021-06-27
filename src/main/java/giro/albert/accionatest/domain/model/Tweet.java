package giro.albert.accionatest.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class Tweet {
    private Long id;
    private String text;
    @JsonIgnore
    private String lang;
    private boolean valid;
    private User user;
    @JsonIgnore
    private Set<Hashtag> hashtags;
}
