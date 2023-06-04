package cn.airbozh.service.impl;

import cn.airbozh.mapper.OderMapper;
import cn.airbozh.mapper.UserMapper;
import cn.airbozh.pojo.Oder;
import cn.airbozh.service.OderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class OderServiceImpl implements OderService {
    @Resource
    OderMapper oderMapper;
    @Resource
    UserMapper userMapper;
    @Override
    public boolean createOder(Oder oder) {
        oder.setCreateDate(new Date());
        oder.setUpdateDate(new Date());
        oder.setOderStatus(0);
        oder.setOderAddress(userMapper.findUserById(oder.getCustomerId()).getAddress());
        return oderMapper.createOder(oder);
    }

    @Override
    public List<Oder> getOderByCustomerId() {
        List<Oder> list = oderMapper.getOderByCustomerId();
        return list;
    }

    @Override
    public boolean updateOderStatus(int oderId, int status) {
        return oderMapper.updateOderStatus(oderId, status);
    }
}
