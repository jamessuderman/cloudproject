package com.gcu.cloudproject.services;

import com.gcu.cloudproject.models.SimpleModel;
import org.springframework.stereotype.Service;

@Service
public class SimpleService {
    public SimpleModel getClassName() {
        return new SimpleModel("CST-323");
    }
}
