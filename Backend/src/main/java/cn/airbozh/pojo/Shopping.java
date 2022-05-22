package cn.airbozh.pojo;

import org.springframework.stereotype.Repository;

@Repository
public class Shopping {
    private int shoppingId;
    private int commodityId;
    private int userId;

    public Shopping() {
    }

    @Override
    public String toString() {
        return "Shopping{" +
                "shoppingId=" + shoppingId +
                ", commodityId=" + commodityId +
                ", userId=" + userId +
                '}';
    }

    public int getShoppingId() {
        return shoppingId;
    }

    public void setShoppingId(int shoppingId) {
        this.shoppingId = shoppingId;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
