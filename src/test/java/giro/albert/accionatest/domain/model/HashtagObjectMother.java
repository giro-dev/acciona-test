package giro.albert.accionatest.domain.model;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;

public class HashtagObjectMother {

    public static Hashtag getRandom(){
        return Hashtag.builder().hashtag(RandomStringUtils.randomAlphanumeric(10)).build();
    }

    public static List<Hashtag> getRandomCollection(Integer size){
        List<Hashtag> hashtag = new ArrayList<>();
        for (int i = 0; i<size; i++){
            hashtag.add(getRandom());
        }
        return hashtag;
    }
}
