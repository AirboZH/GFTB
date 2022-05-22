package cn.airbozh.service;

import cn.airbozh.pojo.Shopping;

import javax.annotation.Resource;
import java.util.List;

public interface ShoppingService {
    boolean createShopping(Shopping shopping);
    Shopping getShoppingByShoppingId (int shoppingId);
    List<Shopping> getShoppingByUserId(int userId);
    boolean deleteShopping(int shoppingId);

}
