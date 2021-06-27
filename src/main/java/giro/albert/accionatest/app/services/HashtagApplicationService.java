package giro.albert.accionatest.app.services;

import giro.albert.accionatest.domain.model.Hashtag;
import giro.albert.accionatest.domain.reposirory.HastagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class HashtagApplicationService {

    private final HastagRepository hastagRepository;

    public Collection<Hashtag> getTopHashTags(Integer topLimit) {
        return hastagRepository.getHastagsTopSorted(topLimit);
    }
}
