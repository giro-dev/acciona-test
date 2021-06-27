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
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long userId;
    private String name;
    private String screenName;
    private int followersCount;
    @OneToMany
    private List<TweetEntity> tweets;




}
