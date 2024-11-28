package entity;

public class Coupons {
    private Integer id;
    private String username;
    private String type;
    private String denomination;
    private String money_off;
    private String coupons_type;
    private String status;
    private String due_time;
    private String token;

    public Coupons() {}

    public Coupons(Integer id, String username, String type, String denomination, String money_off, String coupons_type, String status, String due_time, String token) {
        this.id = id;
        this.username = username;
        this.type = type;
        this.denomination = denomination;
        this.money_off = money_off;
        this.coupons_type = coupons_type;
        this.status = status;
        this.due_time = due_time;
        this.token = token;
    }

    public Coupons(String type, String denomination, String money_off, String coupons_type, String status, String due_time) {
        this.type = type;
        this.denomination = denomination;
        this.money_off = money_off;
        this.coupons_type = coupons_type;
        this.status = status;
        this.due_time = due_time;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getMoney_off() {
        return money_off;
    }

    public void setMoney_off(String money_off) {
        this.money_off = money_off;
    }

    public String getCoupons_type() {
        return coupons_type;
    }

    public void setCoupons_type(String coupons_type) {
        this.coupons_type = coupons_type;
    }

    public String getDue_time() {
        return due_time;
    }

    public void setDue_time(String due_time) {
        this.due_time = due_time;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Coupons{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", type='" + type + '\'' +
                ", denomination='" + denomination + '\'' +
                ", money_off='" + money_off + '\'' +
                ", coupons_type='" + coupons_type + '\'' +
                ", status='" + status + '\'' +
                ", due_time='" + due_time + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
