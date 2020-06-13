package twitsec.tweetservice.model;

import java.util.Date;
import java.util.List;

public class Profile {

    private int id;

    private String username;

    private List<Profile> following;

    private List<Profile> followers;

    private String bio;

    private String websiteUrl;

    private Date createdAt;
}
