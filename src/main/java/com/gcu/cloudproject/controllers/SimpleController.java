package com.gcu.cloudproject.controllers;

import com.gcu.cloudproject.services.SimpleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SimpleController {
    private final SimpleService simpleService;

    public SimpleController(SimpleService simpleService) {
        this.simpleService = simpleService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showSimplePage() {
        ModelAndView simpleMav = new ModelAndView();
        simpleMav.addObject("class", simpleService.getClassName());
        simpleMav.setViewName("simple");
        return simpleMav;
    }
}
