package com.holidu.interview.assignment.service.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TreeServiceImplTest {

    @Autowired
    TreeServiceImpl treeService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void searchTress() throws Exception {
        Map<String, Integer> trees = treeService.searchTress(100000.00, 200000.00, 1000000.00);
        assertNotEquals(0, trees.size());
    }

    @Test
    public void searchTressOtherParams() throws Exception {
        Map<String, Integer> trees = treeService.searchTress(0.0, 0.0, 300000.00);
        assertNotEquals(0, trees.size());
    }


    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionWhenNullsAreGiven() throws Exception {
        treeService.searchTress(null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionWhenNullRadiusGiven() throws Exception {
        treeService.searchTress(0.0, 0.1, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionWhenNullXAndYPointGiven() throws Exception {
        treeService.searchTress(null, null, 100.00);
    }

    @Test
    public void searchTressEmpty() throws Exception {
        Map<String, Integer> trees = treeService.searchTress(0.00, 0.0, 0.0);
        assertEquals(0, trees.size());
    }


}
