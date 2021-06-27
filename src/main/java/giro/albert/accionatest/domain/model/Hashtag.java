package giro.albert.accionatest.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
public class Hashtag implements Comparable<Hashtag>{
    private String hashtag;
    private Integer count;

    @Override
    public int hashCode() {
        return Objects.hash(hashtag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hashtag)) return false;
        Hashtag hashtag = (Hashtag) o;
        return this.hashtag.equals(hashtag.hashtag);
    }

    @Override
    public int compareTo(Hashtag o) {
        return o.getCount().compareTo(count);
    }
}
