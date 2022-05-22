package cn.airbozh.pojo;

import org.springframework.stereotype.Repository;

@Repository
public class Commodity {
    private int commodityId;
    private String commodityName;
    private String commodityPic;
    private String commodityTag;
    private String commodityDetail;
    private float commodityPrice;
    private int commodityShow;

    public Commodity() {
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getCommodityPic() {
        return commodityPic;
    }

    public void setCommodityPic(String commodityPic) {
        this.commodityPic = commodityPic;
    }

    public String getCommodityTag() {
        return commodityTag;
    }

    public void setCommodityTag(String commodityTag) {
        this.commodityTag = commodityTag;
    }

    public String getCommodityDetail() {
        return commodityDetail;
    }

    public void setCommodityDetail(String commodityDetail) {
        this.commodityDetail = commodityDetail;
    }

    public float getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(float commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public int getCommodityShow() {
        return commodityShow;
    }

    public void setCommodityShow(int commodityShow) {
        this.commodityShow = commodityShow;
    }
}
