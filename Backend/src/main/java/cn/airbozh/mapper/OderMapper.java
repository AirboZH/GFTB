package cn.airbozh.mapper;

import cn.airbozh.pojo.Oder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OderMapper {
    @Insert("insert into Oder (commodityId,customerId,oderNumber,oderAddress,oderStatus,createDate,updateDate) values (#{commodityId},#{customerId},#{oderNumber},#{oderAddress},#{oderStatus},#{createDate},#{updateDate})")
    boolean createOder(Oder oder);

    @Select("select * from Oder")
    List<Oder> getOderByCustomerId();
}
