package com.waracle.cakemgr.util;

import com.waracle.cakemgr.model.Cake;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CakeManagerUtilTest {

    @InjectMocks
    CakeManagerUtil cakeManagerUtil;

    @Mock
    Cake cake;

    @Test
    void testGetCakesAsHtmlString(){
        when(cake.getTitle()).thenReturn("title");
        when(cake.getDesc()).thenReturn("desc");
        when(cake.getImage()).thenReturn("image-url");
        String htmlString = cakeManagerUtil.getCakesAsHtmlString(List.of(cake));
        Assertions.assertNotNull(htmlString);
    }
}
