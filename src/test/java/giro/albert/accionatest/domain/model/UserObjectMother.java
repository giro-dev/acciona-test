package giro.albert.accionatest.domain.model;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.apache.commons.lang3.RandomUtils.nextLong;

public class UserObjectMother {

    public static User getRandomUser() {
        return getRandomUser(10, 10000);
    }
    public static User getRandomUser(int minimumFollowers, int maximumFollowers){
    return User.builder()
                    .screenName(randomAlphanumeric(12))
                    .followersCount(nextInt(minimumFollowers, maximumFollowers))
                    .localization(randomAlphanumeric(50))
                    .build();
    }
}
