package tw.com.mbproject.yeol.cache.items;

import java.io.Serializable;

//@RedisHash("Member")
public class MemberCacheItem implements Serializable {

    public final static String NAME = "Member";

    public enum Gender {
        MALE, FEMALE
    }

    private String id;
    private String name;
    private Gender gender;
    private int grade;
}
