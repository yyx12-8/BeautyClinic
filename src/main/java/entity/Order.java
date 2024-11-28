package entity;

public class Order {
    private Integer order_id;
    private String proid;
    private String pro_num;
    private String pro_price;
    private String pro_coupons;
    private String pro_remark;
    private String status;
    private String username;

    public Order() {}

    public Order(Integer order_id, String proid, String pro_num, String pro_price, String pro_coupons, String pro_remark,  String status, String username) {
        this.order_id = order_id;
        this.proid = proid;
        this.pro_num = pro_num;
        this.pro_price = pro_price;
        this.pro_coupons = pro_coupons;
        this.pro_remark = pro_remark;
        this.status = status;
        this.username = username;
    }

    public Order(String proid, String pro_num, String pro_price, String pro_coupons, String pro_remark, String status, String username) {
        this.proid = proid;
        this.pro_num = pro_num;
        this.pro_price = pro_price;
        this.pro_coupons = pro_coupons;
        this.pro_remark = pro_remark;
        this.status = status;
        this.username = username;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public String getProid() {
        return proid;
    }

    public void setProid(String proid) {
        this.proid = proid;
    }

    public String getPro_num() {
        return pro_num;
    }

    public void setPro_num(String pro_num) {
        this.pro_num = pro_num;
    }

    public String getPro_price() {
        return pro_price;
    }

    public void setPro_price(String pro_price) {
        this.pro_price = pro_price;
    }

    public String getPro_coupons() {
        return pro_coupons;
    }

    public void setPro_coupons(String pro_coupons) {
        this.pro_coupons = pro_coupons;
    }

    public String getPro_remark() {
        return pro_remark;
    }

    public void setPro_remark(String pro_remark) {
        this.pro_remark = pro_remark;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", proid='" + proid + '\'' +
                ", pro_num='" + pro_num + '\'' +
                ", pro_price='" + pro_price + '\'' +
                ", pro_coupons='" + pro_coupons + '\'' +
                ", pro_remark='" + pro_remark + '\'' +
                ", status='" + status + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
