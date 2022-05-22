package cn.airbozh.pojo;

import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class Oder {
    private int oderId;
    private int commodityId;
    private int customerId;
    private float oderPrice;
    private String oderAddress;
    private int oderStatus;
    private int oderShow;
    private Date createDate;
    private Date updateDate;

    public Oder() {
    }

    public int getOderId() {
        return oderId;
    }

    public void setOderId(int oderId) {
        this.oderId = oderId;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public float getOderPrice() {
        return oderPrice;
    }

    public void setOderPrice(float oderPrice) {
        this.oderPrice = oderPrice;
    }

    public String getOderAddress() {
        return oderAddress;
    }

    public void setOderAddress(String oderAddress) {
        this.oderAddress = oderAddress;
    }

    public int getOderStatus() {
        return oderStatus;
    }

    public void setOderStatus(int oderStatus) {
        this.oderStatus = oderStatus;
    }

    public int getOderShow() {
        return oderShow;
    }

    public void setOderShow(int oderShow) {
        this.oderShow = oderShow;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
