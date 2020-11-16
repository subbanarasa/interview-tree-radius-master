package com.holidu.interview.assignment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class ApplicationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void shouldLoadContext() {
        assertThat(applicationContext).isNotNull();
    }

}
