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

    public List<InventoryItem> getInventoryItems() {
        inventoryItemServiceLogger.info("InventoryItemService --- getInventoryItems --- " + new Date().toString());
        return (List<InventoryItem>) inventoryItemRepository.findAll();
    }

    public InventoryItem findById(int id) {
        inventoryItemServiceLogger.info("InventoryItemService --- findById --- " + new Date().toString());
        return inventoryItemRepository.findInventoryItemByInventoryItemId(id);
    }

    public InventoryItem save(InventoryItem item) {
        inventoryItemServiceLogger.info("InventoryItemService --- save --- " + new Date().toString());
        return inventoryItemRepository.save(item);
    }

    public void delete(InventoryItem item) {
        inventoryItemServiceLogger.info("InventoryItemService --- delete --- " + new Date().toString());
        inventoryItemRepository.delete(item);
    }
}
