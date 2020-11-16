package com.holidu.interview.assignment.resource;

import com.holidu.interview.assignment.service.TreeService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/rest/services/v1/trees")
@Slf4j
public class TreeResource {

    Logger logger = LoggerFactory.getLogger(TreeResource.class);

    TreeService treeService;

    public TreeResource(TreeService treeService) {
        this.treeService = treeService;
    }

    @GetMapping(value = "/search/{xPoint}/{yPoint}/{radius}")
    public Map<String, Integer> searchTrees(@PathVariable Double xPoint, @PathVariable Double yPoint, @PathVariable Double radius) throws Exception {
        if (logger.isTraceEnabled()){
            logger.trace("Called Search Trees Resource URL");
        }
        return treeService.searchTress(xPoint,yPoint, radius);
    }


}
