package mat.mat_t.domain.user;

import javax.persistence.Entity;

@Entity
public class User {
    private String studentId;
    private String password;
    private String name;
    private String nickname;
    private int phoneNumber;
    private String email;
    private int gender;
    private int age;
    private int auth;
}
