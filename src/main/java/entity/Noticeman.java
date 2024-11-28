package entity;

public class Noticeman {
    private Integer id;
    private String username;
    private String no_id;
    private String notice_user;
    private String notice_via;
    private String notice_content;
    private String isup;
    private String iscollect;

    public Noticeman() {}

    public Noticeman(String username, String no_id, String notice_user, String notice_via, String notice_content, String isup, String iscollect) {
        this.username = username;
        this.no_id = no_id;
        this.notice_user = notice_user;
        this.notice_via = notice_via;
        this.notice_content = notice_content;
        this.isup = isup;
        this.iscollect = iscollect;
    }

    public Noticeman(String username, String no_id, String notice_user, String notice_via, String notice_content) {
        this.username = username;
        this.no_id = no_id;
        this.notice_user = notice_user;
        this.notice_via = notice_via;
        this.notice_content = notice_content;
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

    public String getNo_id() {
        return no_id;
    }

    public void setNo_id(String no_id) {
        this.no_id = no_id;
    }

    public String getNotice_user() {
        return notice_user;
    }

    public void setNotice_user(String notice_user) {
        this.notice_user = notice_user;
    }

    public String getNotice_via() {
        return notice_via;
    }

    public void setNotice_via(String notice_via) {
        this.notice_via = notice_via;
    }

    public String getNotice_content() {
        return notice_content;
    }

    public void setNotice_content(String notice_content) {
        this.notice_content = notice_content;
    }

    public String getIsup() {
        return isup;
    }

    public void setIsup(String isup) {
        this.isup = isup;
    }

    public String getIscollect() {
        return iscollect;
    }

    public void setIscollect(String iscollect) {
        this.iscollect = iscollect;
    }

    @Override
    public String toString() {
        return "Noticeman{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", no_id='" + no_id + '\'' +
                ", notice_user='" + notice_user + '\'' +
                ", notice_via='" + notice_via + '\'' +
                ", notice_content='" + notice_content + '\'' +
                ", isup='" + isup + '\'' +
                ", iscollect='" + iscollect + '\'' +
                '}';
    }
}
