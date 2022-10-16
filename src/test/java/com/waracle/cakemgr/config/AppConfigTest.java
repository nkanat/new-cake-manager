package com.waracle.cakemgr.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { AppConfig.class })
public class AppConfigTest {

    @Autowired
    ApplicationContext context;

    @Test
    void checkRestTemplateBeanIsCreated() {
        assertThatBeanExists("restTemplate", RestTemplate.class);
    }

    private void assertThatBeanExists(String beanName, Class<?> beanClass) {
        Assertions.assertTrue(context.containsBean(beanName));
        Assertions.assertNotNull(context.getBean(beanClass));
    }
}
