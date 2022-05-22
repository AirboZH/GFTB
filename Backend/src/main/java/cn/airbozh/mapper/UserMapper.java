package cn.airbozh.mapper;

import cn.airbozh.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {
    @Select("select * from Users where userId = #{userId}")
    User findUserById(int userId);

    @Select("select * from Users")
    List<User> getUsers();

    @Select("select * from Users where userName = #{userName}")
    User findUserByName(String userName);

    @Insert("Insert Into Users (username,password,role,address,money,createDate,updateDate) Values (#{userName},#{password},#{role},#{address},#{money},#{createDate},#{updateDate})")
    int createUser(User user);

    @Update("Update Users Set userName=#{userName}, address=#{address}, updateDate=#{updateDate} Where userId = #{userId}")
    int updateUser(User user);

    @Delete("Delete from Users Where userId = #{userId}")
    int deleteUser(int userId);

    @Update("Update Users Set money=#{money} Where userId = #{userId}")
    void updateMoney(User user);
}
