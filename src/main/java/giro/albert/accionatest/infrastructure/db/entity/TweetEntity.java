package giro.albert.accionatest.infrastructure.db.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tweets")
@Data
public class TweetEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String text;
    private Boolean validated;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="user", nullable=false)
    private UserEntity user;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "tweet_hastags",
            joinColumns = @JoinColumn(name = "tweetentity_id"),
            inverseJoinColumns = @JoinColumn(name = "hashtagentity_text"))
    private Set<HashtagEntity> hashtags;
}
