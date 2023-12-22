package com.am.reaprich.reaprichbackend.business.service.inventory;

import com.am.reaprich.reaprichbackend.business.pojo.inventory.*;
import com.am.reaprich.reaprichbackend.data.entities.inventory.Item;
import com.am.reaprich.reaprichbackend.data.entities.inventory.Warehouse;
import com.am.reaprich.reaprichbackend.data.entities.inventory.WarehouseInventory;
import com.am.reaprich.reaprichbackend.data.entities.inventory.inventoryprovider.ItemState;
import com.am.reaprich.reaprichbackend.data.repositories.inventory.WarehouseInventoryRepository;
import com.am.reaprich.reaprichbackend.data.repositories.inventory.WarehouseRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class InventoryService {
    private static final Logger logger = LogManager.getLogger(InventoryService.class);
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    WarehouseInventoryRepository warehouseInventoryRepository;
    @Autowired
    ItemService itemService;

    public Warehouse getWarehouse(String id) throws Exception{
        Optional<Warehouse> optionalWarehouse = this.warehouseRepository.findById(id);

        if (optionalWarehouse.isEmpty()) {
            throw new IllegalArgumentException("Warehouse with the specified ID doesn't exist");
        }
        return optionalWarehouse.get();
    }

    public Warehouse getWarehouseByOutlet(String outletID) throws Exception {
        Iterable<Warehouse> warehouses = this.warehouseRepository.findByOutlet(outletID);
        if (!warehouses.iterator().hasNext()) {
            throw new IllegalArgumentException("No warehouse found for specified outlet id");
        }
        return warehouses.iterator().next();
    }

    public void addWarehouse(Warehouse warehouse) {
        if (!this.warehouseRepository.findById(warehouse.getId()).isEmpty()) {
            throw new IllegalArgumentException("Warehouse with the same ID is already present");
        }
        addUpdateWarehouse(warehouse);
    }
    public void addUpdateWarehouse(Warehouse warehouse) {
        this.warehouseRepository.save(warehouse);
    }

    public WarehouseInventoryResponse getWarehouseInvetoryItem(String id) {
        Optional<WarehouseInventory> optionalWarehouseInventory = this.warehouseInventoryRepository.findById(id);

        if (optionalWarehouseInventory.isEmpty()) {
            throw new IllegalArgumentException("Warehouse inventory item with the specified ID doesn't exist");
        }
        return WarehouseInventoryResponse.builder().warehouseInventoryItem(optionalWarehouseInventory.get()).build();
    }

    public WarehouseInventoryCollectionResponse getInventoryItems(String warehouseID) {
        Iterable<WarehouseInventory> warehouseInventoryItems = this.warehouseInventoryRepository.findByWarehouse(warehouseID);

        return WarehouseInventoryCollectionResponse
                .builder()
                .inventoryItems(
                        StreamSupport
                                .stream(warehouseInventoryItems.spliterator(), false)
                                .collect(Collectors.toList())).build();
    }

    public AddWarehouseInvetoryItemsResponse addItemsInWarehouse(AddWarehouseInventoryItemsRequestCollection warehouseInventoryItems) {
        final String PQMN = "addItemsInWarehouse";
        logger.info(PQMN + " - Start");
        List<String> insertedItems = new ArrayList<String>();
        List<String > notInsertedItems = new ArrayList<String >();
        List<String> errors = new ArrayList<String>();

        for(AddWarehouseInventoryItemsRequest addWarehouseInventoryItemsRequest: warehouseInventoryItems.getAddWarehouseInventoryItemsRequests()){
            Item item;
            Warehouse warehouse;
            try
            {
                item = this.itemService.getItem(addWarehouseInventoryItemsRequest.getItem().getId());
                warehouse = getWarehouseByOutlet(addWarehouseInventoryItemsRequest.getOutlet().getId());
            }
            catch (Exception ex) {
                logger.error(ex.toString());
                logger.info(ex.getStackTrace().toString());
                notInsertedItems.add(addWarehouseInventoryItemsRequest.getItem().getId());
                errors.add(ex.getMessage());
                continue;
            }

            Date mfgDate = addWarehouseInventoryItemsRequest.getMfgDate();
            Date expDate = addWarehouseInventoryItemsRequest.getExpDate();
            String batchNumber = addWarehouseInventoryItemsRequest.getBatchNumber();

            for (int i = 0; i< addWarehouseInventoryItemsRequest.getQuantity(); i++) {
                String id = UUID.randomUUID().toString();
                this.warehouseInventoryRepository.save(
                        WarehouseInventory
                                .builder()
                                .id(id)
                                .item(item)
                                .warehouse(warehouse)
                                .mfgDate(mfgDate)
                                .expDate(expDate)
                                .batchNumber(batchNumber)
                                .build());
            }
            insertedItems.add(addWarehouseInventoryItemsRequest.getItem().getId());
        }
        return AddWarehouseInvetoryItemsResponse
                .builder()
                .insertedItems(insertedItems)
                .notInsertedItems(notInsertedItems)
                .errors(errors)
                .build();
    }

    public void updateWarehouseItem(WarehouseInventory warehouseInventory) throws Exception {
        if (this.warehouseInventoryRepository.findById(warehouseInventory.getId()).isEmpty()) {
            throw new IllegalArgumentException("Inventory Item with the specified ID does not exist");
        }
        warehouseInventory.setItem(this.itemService.getItem(warehouseInventory.getItem().getId()));
        this.warehouseInventoryRepository.save(warehouseInventory);
    }

    public void removeItemsFromWarehouse(RemoveWarehouseInventoryItemRequest removeWarehouseInventoryItemRequest) {
        for (String id: removeWarehouseInventoryItemRequest.getWarehouseInvetoryItemList()) {
            try {
                WarehouseInventory warehouseInventoryItem = getWarehouseInvetoryItem(id).getWarehouseInventoryItem();
                warehouseInventoryItem.setState(ItemState.REMOVED);
                this.warehouseInventoryRepository.save(warehouseInventoryItem);
            }
            catch (Exception ex) {

            }
        }
    }
}
