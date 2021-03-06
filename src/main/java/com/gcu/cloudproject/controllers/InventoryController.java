/*
 *   GEODynamics
 *   Author - James Suderman
 *   Date - 02/08/2021
 */

package com.gcu.cloudproject.controllers;

import com.gcu.cloudproject.models.InventoryItem;
import com.gcu.cloudproject.models.Product;
import com.gcu.cloudproject.services.InventoryItemService;
import com.gcu.cloudproject.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
@SessionAttributes("name")
public class InventoryController {
    Logger inventoryLogger = LoggerFactory.getLogger(InventoryController.class);

    private final InventoryItemService inventoryItemService;
    private final ProductService productService;

    public InventoryController(InventoryItemService inventoryItemService, ProductService productService) {
        this.inventoryItemService = inventoryItemService;
        this.productService = productService;
    }

    @RequestMapping(value="/add", method = RequestMethod.GET)
    public ModelAndView showAddPage(ModelMap model){
        inventoryLogger.info("InventoryController --- showAddPage --- " + new Date().toString());
        ModelAndView addMav = new ModelAndView();
        addMav.setViewName("add");
        return addMav;
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") int itemId) {
        inventoryLogger.info("InventoryController --- delete --- " + new Date().toString());
        InventoryItem item = inventoryItemService.findById(itemId);
        inventoryItemService.delete(item);

        ModelAndView appMav = new ModelAndView();
        appMav.addObject("items", inventoryItemService.getInventoryItems());
        appMav.setViewName("app");
        return appMav;
    }

    @RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
    public ModelAndView showEditPage(ModelMap model, @PathVariable int id){
        inventoryLogger.info("InventoryController --- showEditPage --- " + new Date().toString());
        ModelAndView editMav = new ModelAndView();
        InventoryItem item = inventoryItemService.findById(id);
        editMav.addObject("item", item);
        editMav.setViewName("edit");
        return editMav;
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    public ModelAndView save(@RequestParam(name = "itemId") int itemId,
                             @RequestParam(name = "name") String name,
                             @RequestParam(name = "description") String description,
                             @RequestParam(name = "amount") String amount) {

        int itemAmount;
        try {
            itemAmount = Integer.parseInt(amount);

        } catch (NumberFormatException e) {
            itemAmount = 0;
        }

        InventoryItem item = inventoryItemService.findById(itemId);
        item.setAmount(itemAmount);

        inventoryItemService.save(item);

        inventoryLogger.info("InventoryController --- save --- " + new Date().toString());

        ModelAndView appMav = new ModelAndView();
        appMav.addObject("items", inventoryItemService.getInventoryItems());
        appMav.setViewName("app");
        return appMav;
    }

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public ModelAndView create(@RequestParam(name = "name") String name,
                               @RequestParam(name = "description") String description,
                               @RequestParam(name = "amount") String amount) {

        int itemAmount;
        try {
            itemAmount = Integer.parseInt(amount);

        } catch (NumberFormatException e) {
            itemAmount = 0;
        }

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        InventoryItem item = new InventoryItem();
        item.setAmount(itemAmount);
        item.setProduct(product);

        productService.save(product);
        inventoryItemService.save(item);

        inventoryLogger.info("InventoryController --- create --- " + new Date().toString());

        ModelAndView appMav = new ModelAndView();
        appMav.addObject("items", inventoryItemService.getInventoryItems());
        appMav.setViewName("app");
        return appMav;
    }

    @RequestMapping(value="/app", method = RequestMethod.GET)
    public ModelAndView showApp(ModelMap model){
        inventoryLogger.info("InventoryController --- showApp --- " + new Date().toString());
        ModelAndView appMav = new ModelAndView();
        appMav.addObject("items", inventoryItemService.getInventoryItems());
        appMav.setViewName("app");
        return appMav;
    }
}
