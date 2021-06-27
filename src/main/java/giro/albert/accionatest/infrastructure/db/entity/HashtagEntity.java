package giro.albert.accionatest.infrastructure.db.entity;

import giro.albert.accionatest.domain.model.Tweet;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hashtags")
@Data
public class HashtagEntity {
    @Id
    private String text;
    @ManyToMany
    private List<TweetEntity> tweets;
}
