package com.waracle.cakemgr.repository;

import com.waracle.cakemgr.entity.CakeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class CakeManagerRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CakeManagerRepository cakeManagerRepository;
    private CakeEntity entity;

    @BeforeEach()
    public void setUp() {
        entity = new CakeEntity();
        entity.setTitle("title");
        entity.setDesc("desc");
        entity.setImage("image");
        testEntityManager.persistAndFlush(entity);
    }

    @Test
    public void whenFindAllThenReturnCakeEntities(){
        List<CakeEntity> cakeEntities = cakeManagerRepository.findAll();
        assertNotNull(cakeEntities);
        assertEquals(1,cakeEntities.size());
        assertEquals("title",cakeEntities.get(0).getTitle());
        assertNotNull(cakeEntities.get(0).getCakeId());
    }

    @Test
    public void whenSaveThenReturnCakeEntity(){
        CakeEntity cakeEntity = cakeManagerRepository.save(entity);
        assertNotNull(cakeEntity);
        assertEquals("title",cakeEntity.getTitle());
        assertNotNull(cakeEntity.getCakeId());
    }
}
