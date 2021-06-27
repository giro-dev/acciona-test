package giro.albert.accionatest.app.services;

import giro.albert.accionatest.domain.model.Hashtag;
import giro.albert.accionatest.domain.reposirory.HastagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class HashtagApplicationService {

    private final HastagRepository hastagRepository;

    public Collection<Hashtag> getTopHashTags(Integer topLimit) {
        return hastagRepository.getHastagsTopSorted(topLimit);
    }
}
