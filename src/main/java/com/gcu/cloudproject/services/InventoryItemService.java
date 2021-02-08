/*
 *   GEODynamics
 *   Author - James Suderman
 *   Date - 02/08/2021
 */

package com.gcu.cloudproject.services;

import com.gcu.cloudproject.models.InventoryItem;
import com.gcu.cloudproject.repositories.InventoryItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryItemService {
    private final InventoryItemRepository inventoryItemRepository;

    public InventoryItemService(InventoryItemRepository inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
    }

    public List<InventoryItem> getInventoryItems() {
        return (List<InventoryItem>) inventoryItemRepository.findAll();
    }

    public InventoryItem findById(int id) {
        return inventoryItemRepository.findInventoryItemByInventoryItemId(id);
    }

    public InventoryItem save(InventoryItem item) {
        return inventoryItemRepository.save(item);
    }

    public void delete(InventoryItem item) {
        inventoryItemRepository.delete(item);
    }
}
