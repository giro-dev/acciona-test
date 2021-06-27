package giro.albert.accionatest.infrastructure.db.entity;

import giro.albert.accionatest.domain.model.Tweet;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    private String screenName;
    private String localization;
    @OneToMany
    private List<TweetEntity> tweets;




}
