package giro.albert.accionatest.infrastructure.db.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "tweets")
@Data
public class TweetEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String text;
    private String lang;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="user", nullable=false)
    private UserEntity user;
    private Boolean validated;
}
