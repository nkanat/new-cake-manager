package com.waracle.cakemgr.service;

import com.waracle.cakemgr.entity.CakeEntity;
import com.waracle.cakemgr.mapper.CakeMapper;
import com.waracle.cakemgr.model.Cake;
import com.waracle.cakemgr.repository.CakeManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CakeManagerServiceImpl implements CakeManagerService {

    @Autowired
    private CakeManagerRepository cakeManagerRepository;

    @Autowired
    private CakeMapper cakeMapper;

    @Override
    public List<Cake> getCakes() {
        List<CakeEntity> cakeEntities =cakeManagerRepository.findAll();
        return cakeMapper.getCakes(cakeEntities);
    }

    @Override
    public void addCake(Cake cake) {

    }
}
