package cn.airbozh.mapper;

import cn.airbozh.pojo.Commodity;
import cn.airbozh.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CommodityMapper {
    @Insert("Insert Into Commodity (commodityName,commodityPic,commodityTag,commodityDetail,commodityPrice,commodityShow) Values (#{commodityName},#{commodityPic},#{commodityTag},#{commodityDetail},#{commodityPrice},#{commodityShow})")
    int createCommodity(Commodity commodity);

    @Select("select * from Commodity Where commodityId = #{commodityId}")
    Commodity findCommodityById(int commodityId);

    @Select("select * from Commodity Where commodityName = #{commodityName}")
    Commodity findCommodityByName(String  commodityName);

    @Select("select * from Commodity Where commodityShow = 1")
    List<Commodity> getCommodities();

    @Update("Update Commodity Set commodityName=#{commodityName}, commodityPic=#{commodityPic}, commodityTag=#{commodityTag},commodityDetail=#{commodityDetail} ,commodityPrice=#{commodityPrice}, commodityShow=#{commodityShow} Where commodityId = #{commodityId}")
    int updateCommodity(Commodity commodity);

    @Update("Update Commodity Set commodityShow=0 Where commodityId = #{commodityId}")
    int deleteCommodity(int commodityId);
}
