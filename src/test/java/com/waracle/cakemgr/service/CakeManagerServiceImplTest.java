package com.waracle.cakemgr.service;


import com.waracle.cakemgr.entity.CakeEntity;
import com.waracle.cakemgr.exception.DuplicateCakeTitleException;
import com.waracle.cakemgr.mapper.CakeMapper;
import com.waracle.cakemgr.model.Cake;
import com.waracle.cakemgr.repository.CakeManagerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CakeManagerServiceImplTest {

    @InjectMocks
    private CakeManagerService cakeManagerService= new CakeManagerServiceImpl();
    @Spy
    private CakeManagerRepository cakeManagerRepository;
    @Spy
    private CakeMapper cakeMapper;

    @Mock
    private CakeEntity entity;
    @Mock
    private Cake cake;

    @Test
    void testGetCakes(){
        doReturn(List.of(entity)).when(cakeManagerRepository).findAll();
        doReturn(List.of(cake)).when(cakeMapper).getCakes(List.of(entity));
        List<Cake> cakes =cakeManagerService.getCakes();
        assertNotNull(cakes);
        assertEquals(1,cakes.size());
    }

    @Test
    void testAddCake(){
        doReturn(entity).when(cakeMapper).getCakeEntity(cake);
        cakeManagerService.addCake(cake);
        verify(cakeManagerRepository,times(1)).save(entity);
    }

    @Test
    void testAddCakeShouldThrowDataIntegrityViolationException(){
        doReturn(entity).when(cakeMapper).getCakeEntity(cake);
        when(cakeManagerRepository.save(entity)).thenThrow(DataIntegrityViolationException.class);
        DuplicateCakeTitleException ex = Assertions.assertThrows(DuplicateCakeTitleException.class,()->{
            cakeManagerService.addCake(cake);
        });
        assertEquals("Cake title : null already available in DB",ex.getMessage());
    }
}
