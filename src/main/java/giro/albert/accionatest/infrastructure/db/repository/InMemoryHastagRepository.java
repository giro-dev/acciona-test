package giro.albert.accionatest.infrastructure.db.repository;

import giro.albert.accionatest.domain.model.Hashtag;
import giro.albert.accionatest.domain.reposirory.HastagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InMemoryHastagRepository implements HastagRepository {

    private final Set<Hashtag> hastagCounter;

    @Override
    public List<Hashtag> getHastagsTopSorted(Integer topLimit) {
        return hastagCounter.stream()
                .sorted(Hashtag::compareTo)
                .limit(topLimit)
                .collect(Collectors.toList());    }

    @Override
    public void updateHastags(Set<Hashtag> hashtags) {
        hashtags.forEach(hashtag -> {
            if (hastagCounter.contains(hashtag)) {
                Hashtag hashCounter = hastagCounter.stream()
                        .filter(ht -> ht.equals(hashtag))
                        .findFirst().get();
                hashCounter.setCount(hashCounter.getCount() + 1);
            } else {
                hashtag.setCount(1);
                hastagCounter.add(hashtag);
            }
        });
    }
}