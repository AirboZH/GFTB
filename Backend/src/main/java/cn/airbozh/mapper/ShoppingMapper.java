package cn.airbozh.mapper;

import cn.airbozh.pojo.Shopping;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ShoppingMapper {

    @Insert("Insert Into Shopping (commodityId,userId) Values(#{commodityId}, #{userId})")
    int createShopping(Shopping shopping);

    @Select("Select * From Shopping Where shoppingId = #{shoppingId}")
    Shopping getShoppingByShoppingId(int shoppingId);

    @Select("Select * From Shopping Where userId = #{userId}")
    List<Shopping> getShoppingByUserId(int userId);

    @Delete("Delete From Shopping Where shoppingId = #{shoppingId}")
    int deleteShopping(int shoppingId);


}
