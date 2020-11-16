package com.holidu.interview.assignment.resource;

import com.holidu.interview.assignment.service.TreeService;
import com.holidu.interview.assignment.service.impl.TreeServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TreeResource.class)
public class TreeResourceTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TreeService treeService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void searchTrees() throws Exception {
        Map<String, Integer> map = new HashMap<>();
        when(treeService.searchTress(any(), any(), any()))
                .thenReturn(map);
        mvc.perform(MockMvcRequestBuilders
                .get("/rest/services/v1/trees//search/100/100/10")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void get4xxErrorStatus() throws Exception {
        Map<String, Integer> map = new HashMap<>();
        when(treeService.searchTress(any(), any(), any()))
                .thenReturn(map);
        mvc.perform(MockMvcRequestBuilders
                .get("/rest/services/v1/trees/search/100/10")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

}

