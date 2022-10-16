package com.waracle.cakemgr.mapper;

import com.waracle.cakemgr.entity.CakeEntity;
import com.waracle.cakemgr.model.Cake;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CakeMapper {

    public List<Cake> getCakes(List<CakeEntity> cakeEntities) {
        return cakeEntities.stream()
                .map(entity -> new Cake(entity.getTitle(), entity.getDesc(), entity.getImage()))
                .collect(Collectors.toList());
    }

    public CakeEntity getCakeEntity(Cake cake) {
        return new CakeEntity(0,cake.getTitle(),cake.getDesc(), cake.getImage());
    }
}
