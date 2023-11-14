package com.am.reaprich.reaprichbackend.webservice;

import com.am.reaprich.reaprichbackend.business.pojo.IdResponse;
import com.am.reaprich.reaprichbackend.business.pojo.inventory.*;
import com.am.reaprich.reaprichbackend.business.service.inventory.InventoryService;
import com.am.reaprich.reaprichbackend.business.service.inventory.ItemService;
import com.am.reaprich.reaprichbackend.data.entities.inventory.Item;
import com.am.reaprich.reaprichbackend.data.entities.inventory.ItemOffer;
import com.am.reaprich.reaprichbackend.data.entities.inventory.WarehouseInventory;
import com.am.reaprich.reaprichbackend.data.entities.inventory.inventoryprovider.Category;
import com.am.reaprich.reaprichbackend.data.entities.inventory.inventoryprovider.PackingType;
import com.am.reaprich.reaprichbackend.data.entities.inventory.inventoryprovider.Subcategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.POST})
@RequestMapping("/v1/inventory")
public class InventoryWebServiceController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private InventoryService inventoryService;

    @RequestMapping(path = "/item", method = RequestMethod.GET)
    public ResponseEntity<ItemResponse> getItem(@RequestParam String id) {
        try {
            return ResponseEntity.ok(this.itemService.getItem(id));
        }
        catch (Exception ex) {
            return getItemResponseEntityForException(ex);
        }
    }

    @RequestMapping(path = "/allitems", method = RequestMethod.POST)
    public ResponseEntity<ItemCollectionResponse> allItem(@RequestBody AllItemRequest allItemRequest) {
        try {
            return ResponseEntity.ok(this.itemService.getAllItems(allItemRequest));
        }
        catch (Exception ex) {
            return getItemCollectionResponseEntityForException(ex);
        }
    }

    @RequestMapping(path = "/warehouse/items", method = RequestMethod.GET)
    public ResponseEntity<WarehouseInventoryCollectionResponse> getWarehouseItems(@RequestParam String warehouseID) {
        try {
            return ResponseEntity.ok(this.inventoryService.getInventoryItems(warehouseID));
        }
        catch (Exception ex) {
            return getWarehouseInventoryCollectionResponseEntityForException(ex);
        }
    }

    @RequestMapping(path = "/itemoffer/all", method = RequestMethod.GET)
    public ResponseEntity<ItemOfferCollectionResponse> getAllActiveItemOffers() {
        ItemOfferCollectionResponse itemOfferCollection = this.itemService.getAllItemOffers();
        return ResponseEntity.status(HttpStatus.OK).body(itemOfferCollection);
    }

    @RequestMapping(path = "/itemoffer", method = RequestMethod.GET)
    public ResponseEntity<ItemOfferResponse> getItemOffer(@RequestParam String itemOfferID) {
        try {
            ItemOfferResponse itemOfferResponse = this.itemService.getItemOfferByID(itemOfferID);
            return ResponseEntity.status(HttpStatus.OK).body(itemOfferResponse);
        }
        catch (Exception ex) {
            return getItemOfferResponseEntityForInternalServerError(ex);
        }
    }

    @RequestMapping(path = "/offersonitem", method = RequestMethod.GET)
    public ResponseEntity<ItemOfferCollectionResponse> getOffersOnItem(@RequestParam String itemID) {
        ItemOfferCollectionResponse itemOfferCollection = this.itemService.getItemOffersOnItem(itemID);
        return ResponseEntity.status(HttpStatus.OK).body(itemOfferCollection);
    }

    @RequestMapping(path = "/item", method = RequestMethod.POST)
    public ResponseEntity<IdResponse> addItem(@RequestBody Item item) {
        String id = java.util.UUID.randomUUID().toString();
        item.setId(id);
        try {
            this.itemService.addItem(item);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    IdResponse
                            .builder()
                            .id(id)
                            .build());
        }
        catch (Exception ex) {
            return getErrorMsgResponseEntityForException(ex);
        }
    }

    @RequestMapping(path = "/item", method = RequestMethod.PUT)
    public ResponseEntity<IdResponse> updateItem(@RequestBody Item item) {
        try {
            this.itemService.updateItemDetail(item);
            return ResponseEntity.ok(IdResponse
                    .builder()
                    .id(item.getId())
                    .build());
        }
        catch (Exception ex) {
            return getErrorMsgResponseEntityForException(ex);
        }
    }

    @RequestMapping(path = "/item/packingtype", method = RequestMethod.POST)
    public ResponseEntity<IdResponse> addPackingType(@RequestBody PackingType packingType) {
        try {
            this.itemService.addPackingType(packingType);
            return ResponseEntity.status(HttpStatus.CREATED).body(IdResponse
                    .builder()
                    .id(packingType.getId())
                    .build());
        }
        catch (Exception ex) {
            return getErrorMsgResponseEntityForException(ex);
        }
    }

    @RequestMapping(path = "/item/category", method = RequestMethod.POST)
    public ResponseEntity<IdResponse> addCategory(@RequestBody Category category) {
        try {
            this.itemService.addCategory(category);
            return ResponseEntity.status(HttpStatus.CREATED).body(IdResponse
                    .builder()
                    .id(category.getId())
                    .build());
        }
        catch (Exception ex) {
            return getErrorMsgResponseEntityForException(ex);
        }
    }

    @RequestMapping(path = "/item/subcategory", method = RequestMethod.POST)
    public ResponseEntity<IdResponse> addSubCategory(@RequestBody Subcategory subcategory) {
        try {
            this.itemService.addSubcategory(subcategory);
            return ResponseEntity.status(HttpStatus.CREATED).body(IdResponse
                    .builder()
                    .id(subcategory.getId())
                    .build());
        }
        catch (Exception ex) {
            return getErrorMsgResponseEntityForException(ex);
        }
    }

    @RequestMapping(path = "/itemoffer", method = RequestMethod.POST)
    public ResponseEntity<IdResponse> addItemOffer(@RequestBody ItemOffer itemOffer) {
        try {
            String id = UUID.randomUUID().toString();
            itemOffer.setId(id);
            this.itemService.addItemOffer(itemOffer);
            return ResponseEntity.status(HttpStatus.CREATED).body(IdResponse
                    .builder()
                    .id(id)
                    .build());
        }
        catch (Exception ex) {
            return getErrorMsgResponseEntityForException(ex);
        }
    }
    @RequestMapping(path = "/itemoffer", method = RequestMethod.PUT)
    public ResponseEntity<IdResponse> updateItemOffer(@RequestBody ItemOffer itemOffer) {
        try {
            this.itemService.updateItemOffer(itemOffer);
            return ResponseEntity.status(HttpStatus.OK).body(IdResponse
                    .builder()
                    .id(itemOffer.getId())
                    .build());
        }
        catch (Exception ex) {
            return getErrorMsgResponseEntityForException(ex);
        }
    }

    @RequestMapping(path = "/warehouse/items", method = RequestMethod.POST)
    public ResponseEntity<AddWarehouseInvetoryItemsResponse> addItemsInWarehouse(@RequestBody AddWarehouseInventoryItemsRequestCollection warehouseInventoryItems) {
        AddWarehouseInvetoryItemsResponse warehouseInvetoryItemsResponse =
                this.inventoryService.addItemsInWarehouse(warehouseInventoryItems);

        if (warehouseInvetoryItemsResponse.getErrors().stream().count() > 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(warehouseInvetoryItemsResponse);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(warehouseInvetoryItemsResponse);
    }

    @RequestMapping(path = "warehouse/item", method = RequestMethod.PUT)
    public ResponseEntity<IdResponse> updateWarehouseItem(@RequestBody WarehouseInventory warehouseInventory) {
        try {
            this.inventoryService.updateWarehouseItem(warehouseInventory);
            return ResponseEntity.status(HttpStatus.OK).body(IdResponse
                    .builder()
                    .id(warehouseInventory.getId())
                    .build());
        }
        catch (Exception ex) {
            return getErrorMsgResponseEntityForException(ex);
        }
    }

    @RequestMapping(path = "warehouse/removeitems", method = RequestMethod.PUT)
    public ResponseEntity<IdResponse> removeItemsFromWarehouse(@RequestBody RemoveWarehouseInventoryItemRequest removeWarehouseInventoryItemRequest) {
        try {
            this.inventoryService.removeItemsFromWarehouse(removeWarehouseInventoryItemRequest);
            return ResponseEntity.status(HttpStatus.OK).body(IdResponse
                    .builder()
                    .id("Success")
                    .build());
        }
        catch (Exception ex) {
            return getErrorMsgResponseEntityForException(ex);
        }
    }



    private String getErrorMessage(Exception ex) {
        String error = "Something went wrong";
        if (ex.getClass() == IllegalArgumentException.class) {
            error = ex.getMessage();
        }
        return error;
    }
    private HttpStatus getHTTPStatus(Exception ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (ex.getClass() == IllegalArgumentException.class) {
            status = HttpStatus.BAD_REQUEST;
        }
        return status;
    }

    private ResponseEntity<IdResponse > getErrorMsgResponseEntityForException(Exception ex) {
        return ResponseEntity.status(getHTTPStatus(ex)).body(IdResponse
                .builder()
                .error(ex.getMessage())
                .build());
    }

    private ResponseEntity<ItemResponse> getItemResponseEntityForException(Exception ex) {
        return ResponseEntity.status(getHTTPStatus(ex)).body(
                ItemResponse.builder().
                        error(getErrorMessage(ex))
                        .build());
    }

    private ResponseEntity<ItemCollectionResponse> getItemCollectionResponseEntityForException(Exception ex) {
        return ResponseEntity.status(getHTTPStatus(ex)).body(
                ItemCollectionResponse.builder().
                        error(getErrorMessage(ex))
                        .build());
    }

    private ResponseEntity<WarehouseInventoryCollectionResponse> getWarehouseInventoryCollectionResponseEntityForException(Exception ex) {
        return ResponseEntity.status(getHTTPStatus(ex)).body(
                WarehouseInventoryCollectionResponse.builder().
                        error(getErrorMessage(ex))
                        .build());
    }

    private ResponseEntity<ItemOfferResponse> getItemOfferResponseEntityForInternalServerError(Exception ex) {
        return ResponseEntity.status(getHTTPStatus(ex))
                .body(
                        ItemOfferResponse
                                .builder()
                                .error(getErrorMessage(ex))
                                .build());
    }
}
