package entity;

public class Productman {
    private Integer id;
    private String username;
    private String pro_id;
    private String pro_user;
    private String pro_via;
    private String pro_content;
    private String iscollect;

    public Productman() {}

    public Productman(Integer id, String username, String pro_id, String pro_user, String pro_via, String pro_content, String iscollect) {
        this.id = id;
        this.username = username;
        this.pro_id = pro_id;
        this.pro_user = pro_user;
        this.pro_via = pro_via;
        this.pro_content = pro_content;
        this.iscollect = iscollect;
    }

    public Productman(String username, String pro_id, String pro_user, String pro_via, String pro_content, String iscollect) {
        this.username = username;
        this.pro_id = pro_id;
        this.pro_user = pro_user;
        this.pro_via = pro_via;
        this.pro_content = pro_content;
        this.iscollect = iscollect;
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

    public String getPro_id() {
        return pro_id;
    }

    public void setPro_id(String pro_id) {
        this.pro_id = pro_id;
    }

    public String getPro_user() {
        return pro_user;
    }

    public void setPro_user(String pro_user) {
        this.pro_user = pro_user;
    }

    public String getPro_via() {
        return pro_via;
    }

    public void setPro_via(String pro_via) {
        this.pro_via = pro_via;
    }

    public String getPro_content() {
        return pro_content;
    }

    public void setPro_content(String pro_content) {
        this.pro_content = pro_content;
    }

    public String getIscollect() {
        return iscollect;
    }

    public void setIscollect(String iscollect) {
        this.iscollect = iscollect;
    }

    @Override
    public String toString() {
        return "ProductMan{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", pro_id='" + pro_id + '\'' +
                ", pro_user='" + pro_user + '\'' +
                ", pro_via='" + pro_via + '\'' +
                ", pro_content='" + pro_content + '\'' +
                ", iscollect='" + iscollect + '\'' +
                '}';
    }
}
