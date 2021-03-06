/*
 *   GEODynamics
 *   Author - James Suderman
 *   Date - 02/08/2021
 */

package com.gcu.cloudproject.services;

import com.gcu.cloudproject.models.InventoryItem;
import com.gcu.cloudproject.repositories.InventoryItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InventoryItemService {
    Logger inventoryItemServiceLogger = LoggerFactory.getLogger(InventoryItemService.class);

    private final InventoryItemRepository inventoryItemRepository;

    public InventoryItemService(InventoryItemRepository inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
    }

    /**
     * @return the list of all inventory items
     */
    public List<InventoryItem> getInventoryItems() {
        inventoryItemServiceLogger.info("InventoryItemService --- getInventoryItems --- " + new Date().toString());
        return (List<InventoryItem>) inventoryItemRepository.findAll();
    }

    /**
     * @param id is the id of the inventory item to find
     * @return the inventory item
     */
    public InventoryItem findById(int id) {
        inventoryItemServiceLogger.info("InventoryItemService --- findById --- " + new Date().toString());
        return inventoryItemRepository.findInventoryItemByInventoryItemId(id);
    }

    /**
     * @param item is the inventory item to save or update
     * @return the inventory item as proof of save
     */
    public InventoryItem save(InventoryItem item) {
        inventoryItemServiceLogger.info("InventoryItemService --- save --- " + new Date().toString());
        return inventoryItemRepository.save(item);
    }

    /**
     * @param item is the inventory item to delete
     */
    public void delete(InventoryItem item) {
        inventoryItemServiceLogger.info("InventoryItemService --- delete --- " + new Date().toString());
        inventoryItemRepository.delete(item);
    }
}
