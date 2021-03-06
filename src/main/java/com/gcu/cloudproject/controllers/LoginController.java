/*
 *   GEODynamics
 *   Author - James Suderman
 *   Date - 02/08/2021
 */

package com.gcu.cloudproject.controllers;

import com.gcu.cloudproject.models.User;
import com.gcu.cloudproject.services.InventoryItemService;
import com.gcu.cloudproject.services.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
@SessionAttributes("name")
public class LoginController {
    Logger loginLogger = LoggerFactory.getLogger(LoginController.class);

    private final LoginService loginService;
    private final InventoryItemService inventoryItemService;

    public LoginController(LoginService loginService, InventoryItemService inventoryItemService) {
        this.loginService = loginService;
        this.inventoryItemService = inventoryItemService;
    }

    /**
     * @return the model and view for the login page
     */
    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView showLoginPage(){
        loginLogger.info("LoginController --- showLoginPage --- " + new Date().toString());
        ModelAndView loginMav = new ModelAndView();
        loginMav.setViewName("login");
        return loginMav;
    }

    /**
     * Will validate the user based upon username and password
     * @param user is the user from login form
     * @return to the main app page
     */
    @RequestMapping(value="/app", method = RequestMethod.POST)
    public ModelAndView navigateToApp(@ModelAttribute User user){
        ModelAndView appMav = new ModelAndView();

        if(loginService.validate(user.getUsername(), user.getPassword())) {
            loginLogger.info("LoginController --- navigateToApp --- " + new Date().toString());
            appMav.addObject("name", user.getUsername());
            appMav.addObject("user", user);
            appMav.addObject("items", inventoryItemService.getInventoryItems());
            appMav.setViewName("app");
        } else {
            loginLogger.error("LoginController --- navigateToApp --- " + new Date().toString());
            appMav.addObject("invalidCreds", true);
            appMav.setViewName("login");
        }

        return appMav;
    }
}