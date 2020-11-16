package com.holidu.interview.assignment.service.impl;

import com.holidu.interview.assignment.model.TreeData;
import com.holidu.interview.assignment.service.TreeService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * To fetch the tree details from the external API and return back aggregate tree details for the given details.
 * @Author Subba
 *
 */
@Service
@Slf4j
public class TreeServiceImpl implements TreeService {

    Logger logger = LoggerFactory.getLogger(TreeServiceImpl.class);

    RestTemplate restTemplate;

    public TreeServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    /**
     * To search the trees based centre point and radius.
     * @param xPoint
     * @param yPoint
     * @param radius
     * @return Tree name with count.
     * @throws Exception
     */
    @Override
    public Map<String, Integer> searchTress(Double xPoint, Double yPoint, Double radius) throws Exception {
        logger.info("Search tress for the xPoint:" + xPoint + "| yPoint:" + yPoint + "| Radius:" + radius);
        if (xPoint == null){
            throw new IllegalArgumentException("xPoint is null");
        }
        if (yPoint == null){
            throw new IllegalArgumentException("yPoint is null");
        }
        if (radius == null){
            throw new IllegalArgumentException("radius is null");
        }
        Map<String, Integer> trees = new HashMap<>();
        List<TreeData> treeDataList = null;
        try {
            ResponseEntity<List<TreeData>> responseEntity = restTemplate.exchange("http://data.cityofnewyork.us/resource/nwxe-4ae8.json", HttpMethod.GET, null, new ParameterizedTypeReference<List<TreeData>>() {
            });
            treeDataList = responseEntity.getBody();
        } catch (RestClientException e) {
            throw e;
        }
        if (treeDataList != null) {
            treeDataList.forEach(treeData -> {
                if (checkTreeEligible(xPoint, yPoint, radius, treeData)) {
                    String treeName = treeData.getSpc_common();
                    if (treeData.getSpc_common() == null) {
                        treeName = "UNKNOWN";  // Making name as UNKNOWN for trees which doesn't have names.
                    }
                    Integer count = trees.get(treeName);
                    if (count != null) {
                        count++;
                        trees.put(treeName, count);
                    } else {
                        trees.put(treeName, 1);
                    }
                }
            });
        }

        logger.info("Aggregation Trees details:" + trees.toString());
        return trees;
    }

    /**
     * To check given tree is within range of radius with xPoint and yPoint.
     * @param xPoint
     * @param yPoint
     * @param radius
     * @param treeData
     * @return Is tree eligible to return back to json response or not true/false
     */
    private boolean checkTreeEligible(Double xPoint, Double yPoint, Double radius, TreeData treeData) {
        if (logger.isTraceEnabled()) {
            logger.trace("Checking Tree is within given radius for the treeData:" + treeData);
        }
        boolean flag = false;
        if (treeData.getX_sp() != null && treeData.getY_sp() != null) {
            //r^2 = (a-x)^2 + (b-y)^2
            Double calculateRadius = Math.sqrt(Math.pow((treeData.getX_sp() - xPoint), 2) + Math.pow((treeData.getY_sp() - yPoint), 2));
            //Convert feet to meters.
            calculateRadius = (calculateRadius / 3.2808);
            if (logger.isDebugEnabled()) {
                logger.debug("Calculated radius for the tree :" + treeData.getSpc_common() + "| is :" + calculateRadius);
            }
            if (calculateRadius <= radius) {
                flag = true;
            }
        } else {
            logger.info("X and Y point are not available hence ignoring it for the Tree :" + treeData.getSpc_common());
        }
        return flag;
    }
}
