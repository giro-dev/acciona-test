package giro.albert.accionatest.infrastructure.db.mapper;

import giro.albert.accionatest.domain.model.Hashtag;
import giro.albert.accionatest.infrastructure.db.entity.HashtagEntity;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class HashtagMapper {
    public Hashtag fromHashtagEntity(HashtagEntity hashtagEntity){
        return Hashtag.builder().hashtag(hashtagEntity.getText()).build();
    }

    public Set<Hashtag> fromHashtagEntity(Collection<HashtagEntity> hashtagEntity){
        return hashtagEntity.stream()
                .map(this::fromHashtagEntity)
                .collect(Collectors.toSet());
    }

    public HashtagEntity toHashtagEntity(Hashtag hashtag) {
        HashtagEntity hashtagEntity = new HashtagEntity();
        hashtagEntity.setText(hashtag.getHashtag());
        return hashtagEntity;
    }

    public Set<HashtagEntity> toHashtagEntity(Set<Hashtag> hashtag){
        return hashtag.stream()
                .map(this::toHashtagEntity)
                .collect(Collectors.toSet());
    }
}
