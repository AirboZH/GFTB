package cn.airbozh.service.impl;

import cn.airbozh.mapper.CommodityMapper;
import cn.airbozh.pojo.Commodity;
import cn.airbozh.service.CommodityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {
    @Resource
    CommodityMapper commodityMapper;

    @Override
    public boolean createCommodity(Commodity commodity) {
        if (commodityMapper.findCommodityByName(commodity.getCommodityName()) == null) {
            try {
                commodity.setCommodityShow(1);
                return commodityMapper.createCommodity(commodity) != 0;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public Commodity findCommodityById(int commodityId) {
        try {
            Commodity commodity = commodityMapper.findCommodityById(commodityId);
            if (commodity.getCommodityShow() == 0)
                return null;
            return commodity;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Commodity updateCommodity(int commodityId, Commodity commodity) {
        Commodity dbCommodity = commodityMapper.findCommodityById(commodityId);
        boolean flag = false;
        if (commodity.getCommodityName() != null) {
            dbCommodity.setCommodityName(commodity.getCommodityName());
            flag = true;
        }
        if (commodity.getCommodityPic() != null) {
            dbCommodity.setCommodityPic(commodity.getCommodityPic());
            flag = true;
        }
        if (commodity.getCommodityTag() != null) {
            dbCommodity.setCommodityTag(commodity.getCommodityTag());
            flag = true;
        }
        if (commodity.getCommodityDetail() != null) {
            dbCommodity.setCommodityDetail(commodity.getCommodityDetail());
            flag = true;
        }
        if (commodity.getCommodityPrice() != 0) {
            dbCommodity.setCommodityPrice(commodity.getCommodityPrice());
            flag = true;
        }
        if (commodity.getCommodityShow() != 0) {
            dbCommodity.setCommodityShow(commodity.getCommodityShow());
            flag = true;
        }
        if (flag) {
            if (commodityMapper.updateCommodity(dbCommodity) != 0)
                return commodityMapper.findCommodityById(commodityId);
        }
        return null;
    }

    @Override
    public boolean deleteCommodity(int commodityId) {
        try {
            return commodityMapper.deleteCommodity(commodityId) != 0;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public List<Commodity> getCommodities() {
        try {
            return commodityMapper.getCommodities();
        } catch (Exception e) {
            return null;
        }
    }
}
