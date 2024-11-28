package entity;

public class User {
    private Integer id;
    private String username;
    private String usernickname;
    private String uservia;
    private String usersex;
    private String usersignature;
    private String usertimer;

    private String token;

    public User() {}

    public User(String username, String usernickname, String uservia, String usersex, String usersignature, String usertimer) {
        this.username = username;
        this.usernickname = usernickname;
        this.uservia = uservia;
        this.usersex = usersex;
        this.usersignature = usersignature;
        this.usertimer = usertimer;
    }


    public User(String username, String usernickname, String uservia, String usersex, String usersignature, String usertimer, String token) {
        this.username = username;
        this.usernickname = usernickname;
        this.uservia = uservia;
        this.usersex = usersex;
        this.usersignature = usersignature;
        this.usertimer = usertimer;
        this.token = token;
    }


    public String getUsernickname() {
        return usernickname;
    }

    public void setUsernickname(String usernickname) {
        this.usernickname = usernickname;
    }

    public String getUservia() {
        return uservia;
    }

    public void setUservia(String uservia) {
        this.uservia = uservia;
    }

    public String getUsersex() {
        return usersex;
    }

    public void setUsersex(String usersex) {
        this.usersex = usersex;
    }

    public String getUsersignature() {
        return usersignature;
    }

    public void setUsersignature(String usersignature) {
        this.usersignature = usersignature;
    }

    public String getUsertimer() {
        return usertimer;
    }

    public void setUsertimer(String usertimer) {
        this.usertimer = usertimer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User(Integer id, String username, String usernickname, String uservia, String usersex, String usersignature, String usertimer,String token) {
        this.id = id;
        this.username = username;
        this.usernickname = usernickname;
        this.uservia = uservia;
        this.usersex = usersex;
        this.usersignature = usersignature;
        this.usertimer = usertimer;
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", usernickname='" + usernickname + '\'' +
                ", uservia='" + uservia + '\'' +
                ", usersex='" + usersex + '\'' +
                ", usersignature='" + usersignature + '\'' +
                ", usertimer='" + usertimer + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
