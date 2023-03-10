package com.microservices.core.recommendation.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.api.core.recommendation.Recommendation;
import com.microservices.api.core.recommendation.RecommendationService;
import com.microservices.api.exceptions.InvalidInputException;
import com.microservices.util.http.ServiceUtil;

@RestController
public class RecommendationServiceImpl implements RecommendationService {
    
    private static final Logger LOG = LoggerFactory.getLogger(RecommendationServiceImpl.class);
    private final ServiceUtil serviceUtil;

    public RecommendationServiceImpl(ServiceUtil serviceUtil) {
        this.serviceUtil = serviceUtil;
    }

    @Override
    public List<Recommendation> getRecommendations(int productId) {
        if (productId < 1) {
            throw new InvalidInputException("Invalid productId: " + productId);
        }
        
        if (productId == 113) {
            LOG.debug("No recommendations found for productId: {}", productId);
            return new ArrayList<>();
        }

        List<Recommendation> recommendations = new ArrayList<>();
        recommendations.add(new Recommendation(productId, 1, "Author 1", 1, "Content 1", serviceUtil.getServiceAddress()));
        recommendations.add(new Recommendation(productId, 2, "Author 2", 2, "Content 2", serviceUtil.getServiceAddress()));
        recommendations.add(new Recommendation(productId, 3, "Author 3", 3, "Content 3", serviceUtil.getServiceAddress()));
        
        LOG.debug("/recommendation response size: {}", recommendations.size());

        return recommendations;
    }
}
