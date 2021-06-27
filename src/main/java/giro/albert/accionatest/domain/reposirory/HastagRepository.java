package giro.albert.accionatest.domain.reposirory;

import giro.albert.accionatest.domain.model.Hashtag;

import java.util.List;
import java.util.Set;

public interface HastagRepository {

    List<Hashtag> getHastagsTopSorted(Integer topLimit);

    void updateHastags(Set<Hashtag> hashtags);
}
