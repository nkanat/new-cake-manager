package com.waracle.cakemgr.mapper;

import com.waracle.cakemgr.entity.CakeEntity;
import com.waracle.cakemgr.model.Cake;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CakeMapperTest {

    @InjectMocks
    private CakeMapper mapper;

    @Test
    void testGetCakeEntity(){

        Cake cake= new Cake("Vanilla Cake","Premium Vanilla Cake 1Kg","http://cake-factory/vanilla-cake.jpeg");
        CakeEntity entity=mapper.getCakeEntity(cake);
        assertNotNull(entity);
        assertEquals("Vanilla Cake",entity.getTitle());
    }

    @Test
    void testGetCakes(){

        List<CakeEntity> cakeEntities = List.of(new CakeEntity(0,"Vanilla Cake","desc","image-url"));
        List<Cake> cakes =mapper.getCakes(cakeEntities);
        assertNotNull(cakes);
        assertNotNull(cakes.get(0));
        assertEquals("Vanilla Cake",cakes.get(0).getTitle());
    }
}
