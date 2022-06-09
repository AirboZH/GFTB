package cn.airbozh.service.impl;

import cn.airbozh.mapper.ShoppingMapper;
import cn.airbozh.pojo.Shopping;
import cn.airbozh.service.ShoppingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ShoppingServiceImpl implements ShoppingService {
    @Resource
    ShoppingMapper shoppingMapper;

    @Override
    public boolean createShopping(Shopping shopping) {
        try {
            Shopping dbShopping = shoppingMapper.getShoppingByUserIdAndCommodityId(shopping);
            if(dbShopping != null){
                dbShopping.setCommodityNum(dbShopping.getCommodityNum()+1);
                shoppingMapper.updateShoppingByShoppingId(dbShopping);
                return true;
            }
            if (shoppingMapper.createShopping(shopping) != 0)
                return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public Shopping getShoppingByShoppingId(int shoppingId) {
        try {
            return shoppingMapper.getShoppingByShoppingId(shoppingId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Shopping> getShoppingByUserId(int shoppingId) {
        try {
            return shoppingMapper.getShoppingByUserId(shoppingId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteShopping(int shoppingId) {
        try {
            if (shoppingMapper.deleteShopping(shoppingId) != 0)
                return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
