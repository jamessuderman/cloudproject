/*
 *   GEODynamics
 *   Author - James Suderman
 *   Date - 02/08/2021
 */

package com.gcu.cloudproject.services;

import com.gcu.cloudproject.models.Product;
import com.gcu.cloudproject.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProductService {
    Logger productServiceLogger = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(Product product) {
        productServiceLogger.info("ProductService --- save --- " + new Date().toString());
        return productRepository.save(product);
    }
}
