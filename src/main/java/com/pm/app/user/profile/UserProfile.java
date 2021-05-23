package com.pm.app.user.profile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pm.app.user.User;
import com.pm.app.utils.RegisterManager;
import com.pm.app.utils.number.NumericHelper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(appliesTo = "user_profile")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserProfile {

    public UserProfile(User owner, String nickname, Gender gender, String description, String image){
        this.user           = owner;
        this.nickname       = nickname == null ? getRandomNickname() : nickname;
        this.gender         = gender == null ? Gender.UNKNOWN : gender;
        this.description    = description == null ? this.description : description;
        this.image          = image == null ? this.image : image;
        this.updateAt       = new Timestamp(System.currentTimeMillis());
    }

    @Transient
    public static final String DEFAULT_IMAGE_LINK = "https://www.pngarts.com/files/5/User-Avatar-Transparent.png";

    @Transient
    public static final String DEFAULT_DESCRIPTION = "Hello, Let's write your adventure here ...";

    @Transient
    public static final String[] MALE_DEFAULT_NICKNAME = {
            "John_",
            "Andy_",
            "Abraham_",
            "Harry_"
    };

    @Transient
    public static final String[] FEMALE_DEFAULT_NICKNAME = {
            "Emily_",
            "Barbara_",
            "Jean_",
            "May_"
    };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="nickname", unique = true)
    private String nickname;

    @Column(name="gender", nullable = false)
    private Gender gender;

    @Column(name="image")
    private String image = DEFAULT_IMAGE_LINK;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @Column(name = "description")
    private String description = DEFAULT_DESCRIPTION;

    @Column(name="update_at")
    private Timestamp updateAt;

    private NumericHelper getNumericHelper() {
        return (NumericHelper)RegisterManager.getInstance().getUtility(RegisterManager.NUMERIC_HELPER);
    }

    public String getRandomNickname(){
        final NumericHelper helper = getNumericHelper();
        int randomInteger = helper.getRandomNumber(0, MALE_DEFAULT_NICKNAME.length);
        String randomNickname = "";

        if(gender == Gender.MALE){
            randomNickname =  MALE_DEFAULT_NICKNAME[randomInteger] + Long.toString(System.currentTimeMillis());
        }else if(gender == Gender.FEMALE){
            randomNickname = FEMALE_DEFAULT_NICKNAME[randomInteger] + Long.toString(System.currentTimeMillis());
        }else {
            if(helper.getRandomNumber(0, 2) % 2 == 0){
                randomNickname = MALE_DEFAULT_NICKNAME[randomInteger] + Long.toString(System.currentTimeMillis());
            }else{
                randomNickname = FEMALE_DEFAULT_NICKNAME[randomInteger] + Long.toString(System.currentTimeMillis());
            }
        }

        return randomNickname;
    }


}
