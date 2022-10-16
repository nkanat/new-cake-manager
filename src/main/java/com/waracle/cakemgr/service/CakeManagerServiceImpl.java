package com.waracle.cakemgr.service;

import com.waracle.cakemgr.entity.CakeEntity;
import com.waracle.cakemgr.exception.DuplicateCakeTitleException;
import com.waracle.cakemgr.mapper.CakeMapper;
import com.waracle.cakemgr.model.Cake;
import com.waracle.cakemgr.repository.CakeManagerRepository;
import lombok.extern.slf4j.Slf4j;
import org.hsqldb.HsqlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CakeManagerServiceImpl implements CakeManagerService {

    @Autowired
    private CakeManagerRepository cakeManagerRepository;

    @Autowired
    private CakeMapper cakeMapper;

    @Override
    public List<Cake> getCakes() {
        List<CakeEntity> cakeEntities = cakeManagerRepository.findAll();
        return cakeMapper.getCakes(cakeEntities);
    }

    @Override
    public void addCake(Cake cake) {
        try {
            cakeManagerRepository.save(cakeMapper.getCakeEntity(cake));
        } catch (DataIntegrityViolationException ex) {
            log.error("DB error while adding cake :{}", ex.getMessage());
            throw new DuplicateCakeTitleException("Cake title : " + cake.getTitle() + " already available in DB");
        }
    }
}
