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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("name")
public class InventoryController {
    private final InventoryItemService inventoryItemService;
    private final ProductService productService;

    public InventoryController(InventoryItemService inventoryItemService, ProductService productService) {
        this.inventoryItemService = inventoryItemService;
        this.productService = productService;
    }

    @RequestMapping(value="/add", method = RequestMethod.GET)
    public ModelAndView showAddPage(ModelMap model){
        ModelAndView addMav = new ModelAndView();
        addMav.setViewName("add");
        return addMav;
    }

    @RequestMapping(value="/delete", method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam("itemId") int itemId) {
        InventoryItem item = inventoryItemService.findById(itemId);
        inventoryItemService.delete(item);

        ModelAndView appMav = new ModelAndView();
        appMav.addObject("items", inventoryItemService.getInventoryItems());
        appMav.setViewName("app");
        return appMav;
    }

    @RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
    public ModelAndView showEditPage(ModelMap model, @PathVariable int id){
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

        ModelAndView appMav = new ModelAndView();
        appMav.addObject("items", inventoryItemService.getInventoryItems());
        appMav.setViewName("app");
        return appMav;
    }
}
