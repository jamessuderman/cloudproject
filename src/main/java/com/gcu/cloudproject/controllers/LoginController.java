/*
 *   GEODynamics
 *   Author - James Suderman
 *   Date - 02/08/2021
 */

package com.gcu.cloudproject.controllers;

import com.gcu.cloudproject.models.User;
import com.gcu.cloudproject.services.InventoryItemService;
import com.gcu.cloudproject.services.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("name")
public class LoginController {
    private final LoginService loginService;
    private final InventoryItemService inventoryItemService;

    public LoginController(LoginService loginService, InventoryItemService inventoryItemService) {
        this.loginService = loginService;
        this.inventoryItemService = inventoryItemService;
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView showLoginPage(ModelMap model){
        ModelAndView loginMav = new ModelAndView();
        loginMav.setViewName("login");
        return loginMav;
    }

    @RequestMapping(value="/app", method = RequestMethod.POST)
    public ModelAndView navigateToApp(ModelMap model, @ModelAttribute User user){
        ModelAndView appMav = new ModelAndView();

        if(loginService.validate(user.getUsername(), user.getPassword())) {
            appMav.addObject("name", user.getUsername());
            appMav.addObject("user", user);
            appMav.addObject("items", inventoryItemService.getInventoryItems());
            appMav.setViewName("app");
        } else {
            appMav.addObject("invalidCreds", true);
            appMav.setViewName("login");
        }

        return appMav;
    }
}