package cn.airbozh.mapper;

import cn.airbozh.pojo.Shopping;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ShoppingMapper {

    @Insert("Insert Into Shopping (commodityId,userId) Values(#{commodityId}, #{userId})")
    int createShopping(Shopping shopping);

    @Update("Update Shopping Set commodityNum = #{commodityNum} Where shoppingId = #{shoppingId}")
    int updateShoppingByShoppingId(Shopping shopping);

    @Select("Select * From Shopping Where commodityId = #{commodityId} And userId = #{userId}")
    Shopping getShoppingByUserIdAndCommodityId(Shopping shopping);

    @Select("Select * From Shopping Where shoppingId = #{shoppingId}")
    Shopping getShoppingByShoppingId(int shoppingId);

    @Select("Select s.shoppingId,c.commodityId,c.commodityName,c.commodityPic,c.commodityPrice,s.commodityNum From (Select * From Shopping Where userId = #{userId}) s INNER JOIN Commodity c Where c.commodityId = s.commodityId")
    List<Shopping> getShoppingByUserId(int userId);

    @Delete("Delete From Shopping Where shoppingId = #{shoppingId}")
    int deleteShopping(int shoppingId);

}
