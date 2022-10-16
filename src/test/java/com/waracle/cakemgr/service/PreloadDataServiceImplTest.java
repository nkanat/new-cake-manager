package com.waracle.cakemgr.service;

import com.waracle.cakemgr.entity.CakeEntity;
import com.waracle.cakemgr.repository.CakeManagerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PreloadDataServiceImplTest {

    @InjectMocks
    private PreloadDataService preloadDataService=new PreloadDataServiceImpl();

    @Spy
    private RestTemplate restTemplate;
    @Spy
    private CakeManagerRepository cakeManagerRepository;

    @Mock
    ResponseEntity<CakeEntity[]> cakeResponseEntity;
    @Mock
    CakeEntity entity;

    @Test
    void test(){
        ReflectionTestUtils.setField(preloadDataService, "cakeDataPreloadUrl", "value");
        doReturn(cakeResponseEntity).when(restTemplate).getForEntity(anyString(),any());
        when(cakeResponseEntity.getBody()).thenReturn(new CakeEntity[]{entity});
        preloadDataService.preloadCakeData();
        verify(cakeManagerRepository,times(1)).saveAll(anySet());
    }
}
