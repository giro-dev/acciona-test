package giro.albert.accionatest.infrastructure.db.repository;

import giro.albert.accionatest.domain.model.Tweet;
import giro.albert.accionatest.infrastructure.db.entity.HashtagEntity;
import giro.albert.accionatest.infrastructure.db.entity.TweetEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JPATweetRepository extends JpaRepository<TweetEntity, Long> {

    @Query("FROM TweetEntity t where t.user.screenName = :id and t.validated = :valid")
    List<TweetEntity> findValidatedByUser(@Param("id") String id, @Param("valid") Boolean validated);

}