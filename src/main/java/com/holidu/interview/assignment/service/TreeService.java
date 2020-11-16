package com.holidu.interview.assignment.service;

import java.math.BigDecimal;
import java.util.Map;

public interface TreeService {
    
    Map<String, Integer> searchTress(Double xPoint, Double yPoint, Double radius) throws Exception;
}
