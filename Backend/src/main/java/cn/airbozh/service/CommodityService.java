package cn.airbozh.service;


import cn.airbozh.pojo.Commodity;

import java.util.List;

public interface CommodityService {
    boolean createCommodity(Commodity commodity);
    List<Commodity> getCommodities();
    Commodity findCommodityById(int commodityId);
    Commodity updateCommodity(int commodityId,Commodity commodity);
    boolean deleteCommodity(int commodityId);

}
