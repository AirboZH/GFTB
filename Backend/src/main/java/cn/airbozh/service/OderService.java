package cn.airbozh.service;

import cn.airbozh.pojo.Oder;

import java.util.List;

public interface OderService {
    boolean createOder(Oder oder);

    List<Oder> getOderByCustomerId();
}
