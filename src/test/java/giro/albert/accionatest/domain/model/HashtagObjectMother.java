package giro.albert.accionatest.domain.model;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;

public class HashtagObjectMother {

    public static HashTag getRandom(){
        HashTag hashtag = new HashTag();
        hashtag.setText(RandomStringUtils.randomAlphanumeric(10));
        return hashtag;
    }

    public static List<HashTag> getRandomCollection(Integer size){
        List<HashTag> hashtag = new ArrayList<>();
        for (int i = 0; i<size; i++){
            hashtag.add(getRandom());
        }
        return hashtag;
    }
}
