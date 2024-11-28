package entity;

public class Diaryman {
    private Integer comment_id;
    private String diary_comment_user;
    private String diary_comment_via;
    private String diary_comment_content;
    private String diary_id;
    private String username;
    private String isup;
    private String iscollect;

    public Diaryman() {}

    public Diaryman(String diary_comment_user, String diary_comment_via, String diary_comment_content, String diary_id, String username, String isup, String iscollect) {
        this.diary_comment_user = diary_comment_user;
        this.diary_comment_via = diary_comment_via;
        this.diary_comment_content = diary_comment_content;
        this.diary_id = diary_id;
        this.username = username;
        this.isup = isup;
        this.iscollect = iscollect;
    }

    public Diaryman(String diary_comment_user, String diary_comment_via, String diary_comment_content, String diary_id, String username) {
        this.diary_comment_user = diary_comment_user;
        this.diary_comment_via = diary_comment_via;
        this.diary_comment_content = diary_comment_content;
        this.diary_id = diary_id;
        this.username = username;
    }

    public Integer getComment_id() {
        return comment_id;
    }

    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
    }

    public String getDiary_comment_user() {
        return diary_comment_user;
    }

    public void setDiary_comment_user(String diary_comment_user) {
        this.diary_comment_user = diary_comment_user;
    }

    public String getDiary_comment_via() {
        return diary_comment_via;
    }

    public void setDiary_comment_via(String diary_comment_via) {
        this.diary_comment_via = diary_comment_via;
    }

    public String getDiary_comment_content() {
        return diary_comment_content;
    }

    public void setDiary_comment_content(String diary_comment_content) {
        this.diary_comment_content = diary_comment_content;
    }

    public String getDiary_id() {
        return diary_id;
    }

    public void setDiary_id(String diary_id) {
        this.diary_id = diary_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
