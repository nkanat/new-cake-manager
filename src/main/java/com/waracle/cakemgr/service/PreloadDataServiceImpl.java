package com.waracle.cakemgr.service;

import com.waracle.cakemgr.entity.CakeEntity;
import com.waracle.cakemgr.model.Cake;
import com.waracle.cakemgr.repository.CakeManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class PreloadDataServiceImpl implements PreloadDataService{

    @Autowired
    private RestTemplate restTemplate;

    @Value("${cake-data.preload-url}")
    private String cakeDataPreloadUrl;

    @Autowired
    private CakeManagerRepository cakeManagerRepository;


    @PostConstruct
    @Override
    public void preloadCakeData() {
        ResponseEntity<CakeEntity[]> cakes = restTemplate.getForEntity(cakeDataPreloadUrl,CakeEntity[].class);
        Set<CakeEntity> cakeSet = new HashSet<>(Arrays.asList(cakes.getBody()));
        cakeManagerRepository.saveAll(cakeSet);
    }
}
