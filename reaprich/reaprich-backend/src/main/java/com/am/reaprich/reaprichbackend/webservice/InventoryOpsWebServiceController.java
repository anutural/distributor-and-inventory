package com.am.reaprich.reaprichbackend.webservice;

import com.am.reaprich.reaprichbackend.business.pojo.IdResponse;
import com.am.reaprich.reaprichbackend.business.pojo.inventoryops.*;
import com.am.reaprich.reaprichbackend.business.service.inventory.InventoryOpsService;
import com.am.reaprich.reaprichbackend.data.entities.inventoryops.CartEntry;
import com.am.reaprich.reaprichbackend.data.entities.inventoryops.ItemTransfer;
import com.am.reaprich.reaprichbackend.data.entities.inventoryops.PurchaseRequest;
import com.am.reaprich.reaprichbackend.data.entities.inventoryops.transactionsprovider.PurchaseRequestStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.InsufficientResourcesException;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.POST})
@RequestMapping("/v1/inventoryops")
public class InventoryOpsWebServiceController {
    private static final Logger logger = LogManager.getLogger(InventoryOpsWebServiceController.class);
    @Autowired
    private InventoryOpsService inventoryOpsService;

    @GetMapping("/purchaserequest")
    public ResponseEntity<PurchaseRequestResponse> getPurchaseRequest(@RequestParam  String purchaseRequestID) {
        final String PQMN  = "getPurchaseRequest";
        logger.info(PQMN + " - Start");
        try {
            PurchaseRequest purchaseRequest = inventoryOpsService.getPurchaseRequest(purchaseRequestID);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(PurchaseRequestResponse
                            .builder()
                            .purchaseRequest(purchaseRequest)
                            .build());
        } catch (Exception ex) {
            logger.error(ex.toString());
            Arrays.stream(ex.getStackTrace()).iterator().forEachRemaining(x -> logger.error(x.toString()));
            return getPurchaseRequestResponseEntityForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }

    @PostMapping("/allpurchaserequests")
    public ResponseEntity<PurchaseRequestCollectionResponse> getAllPurchaseRequests(@RequestBody AllPurchaseRequestsReq allPurchaseRequestsReq) {
        final String PQMN  = "getAllPurchaseRequests";
        logger.info(PQMN + " - Start");
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(PurchaseRequestCollectionResponse
                            .builder()
                            .purchaseRequestList(inventoryOpsService.getAllPurchaseRequests(allPurchaseRequestsReq))
                            .build());
        } catch (Exception ex) {
            logger.error(ex.toString());
            Arrays.stream(ex.getStackTrace()).iterator().forEachRemaining(x -> logger.error(x.toString()));
            return getPurchaseRequestCollectionResponseEntityForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }

    @GetMapping("/itemstransfer")
    public ResponseEntity<ItemTransferResponse> getItemTransfer (@RequestParam  String itemTransferID) {
        final String PQMN  = "getItemTransfer";
        logger.info(PQMN + " - Start");
        try {
            ItemTransfer itemTransfer = inventoryOpsService.getItemTransfer(itemTransferID);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ItemTransferResponse
                            .builder()
                            .itemTransfer(itemTransfer)
                            .build());
        } catch (Exception ex) {
            logger.error(ex.toString());
            Arrays.stream(ex.getStackTrace()).iterator().forEachRemaining(x -> logger.error(x.toString()));
            return getItemTransferResponseEntityForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }

    @PostMapping("/allitemstransfer")
    public ResponseEntity<ItemTransferCollectionResponse> getAllItemTransfer (@RequestBody AllItemTransferRequest allItemTransferRequest) {
        final String PQMN  = "getAllItemTransfer";
        logger.info(PQMN + " - Start");
        try {
            List<ItemTransfer> itemTransferList = inventoryOpsService.getAllItemTransfer(allItemTransferRequest);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ItemTransferCollectionResponse
                            .builder()
                            .itemTransferList(itemTransferList)
                            .build());
        } catch (Exception ex) {
            logger.error(ex.toString());
            Arrays.stream(ex.getStackTrace()).iterator().forEachRemaining(x -> logger.error(x.toString()));
            return getItemTransferCollectionResponseEntityForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }

    @GetMapping("/cart")
    public ResponseEntity<CartEntriesResponse> getCart(@RequestParam String outletID) {
        final String PQMN =  "getCart";
        logger.info(PQMN + "Start");
        try {
            List<CartEntry> cartEntries = this.inventoryOpsService.getCartEntriesForOutlet(outletID);
            return ResponseEntity.status(HttpStatus.OK).body(CartEntriesResponse.builder().cartEntries(cartEntries).build());
        } catch (Exception ex) {
            logger.error(ex.toString());
            Arrays.stream(ex.getStackTrace()).iterator().forEachRemaining(x -> logger.error(x.toString()));
            return  getCartEntriesResponseForInternalServerError(ex);
        } finally {
            logger.info(PQMN + "End");
        }
    }

    @PostMapping("/itemincart")
    public ResponseEntity<IdResponse> addItemIntoCart(@RequestBody CartEntry cartEntrie) {
        final String PQMN =  "addItemIntoCart";
        logger.info(PQMN + "Start");
        try {
            String cartEntryID = this.inventoryOpsService.addItemIntoCart(cartEntrie);
            return ResponseEntity.status(HttpStatus.OK).body(IdResponse.builder().id(cartEntryID).build());
        } catch (Exception ex) {
            logger.error(ex.toString());
            Arrays.stream(ex.getStackTrace()).iterator().forEachRemaining(x -> logger.error(x.toString()));
            return  getIDResponseForInternalServerError(ex);
        } finally {
            logger.info(PQMN + "End");
        }
    }
    @PutMapping("/itemfromcart")
    public ResponseEntity<IdResponse> removeEntryFromCart(@RequestParam String cartEntryID) {
        final String PQMN =  "removeEntryFromCart";
        logger.info(PQMN + "Start");
        try {
            this.inventoryOpsService.removeEntryFromCart(cartEntryID);
            return ResponseEntity.status(HttpStatus.OK).body(IdResponse.builder().id("Success").build());
        } catch (Exception ex) {
            logger.error(ex.toString());
            Arrays.stream(ex.getStackTrace()).iterator().forEachRemaining(x -> logger.error(x.toString()));
            return  getIDResponseForInternalServerError(ex);
        } finally {
            logger.info(PQMN + "End");
        }
    }
    @PutMapping("/itemincart")
    public ResponseEntity<IdResponse> updateItemInCart(@RequestBody CartEntry cartEntrie) {
        final String PQMN =  "updateItemInCart";
        logger.info(PQMN + "Start");
        try {
            String id = this.inventoryOpsService.updateItemIntoCart(cartEntrie);
            return ResponseEntity.status(HttpStatus.OK).body(IdResponse.builder().id(id).build());
        } catch (Exception ex) {
            logger.error(ex.toString());
            Arrays.stream(ex.getStackTrace()).iterator().forEachRemaining(x -> logger.error(x.toString()));
            return  getIDResponseForInternalServerError(ex);
        } finally {
            logger.info(PQMN + "End");
        }
    }


    @PostMapping("/purchaserequest")
    public ResponseEntity<IdResponse> addPurchaseRequest(@RequestBody PRRequest prRequest) {
        final String PQMN  = "addPurchaseRequest";
        logger.info(PQMN + " - Start");
        try {
            PurchaseRequest purchaseRequest = this.inventoryOpsService.generatePurchaseRequest(prRequest);
            this.inventoryOpsService.addPurchaseRequest(purchaseRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    IdResponse
                            .builder()
                            .id(purchaseRequest.getId())
                            .build());
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            Arrays.stream(ex.getStackTrace()).iterator().forEachRemaining(x -> logger.error(x.toString()));
            return  getIDResponseForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }

    @PostMapping("/itemstransfer")
    public ResponseEntity<IdResponse> addItemTransfer(@RequestBody ItemTransfer itemTransfer) {
        final String PQMN  = "addItemTransfer";
        logger.info(PQMN + " - Start");
        String id = java.util.UUID.randomUUID().toString();
        itemTransfer.setId(id);

        try {
            this.inventoryOpsService.addItemTransfer(itemTransfer);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    IdResponse
                            .builder()
                            .id(id)
                            .build());
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            Arrays.stream(ex.getStackTrace()).iterator().forEachRemaining(x -> logger.error(x.toString()));
            return  getIDResponseForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }

    @PostMapping("/itemstransfertounknown")
    public ResponseEntity<IdResponse> addItemTransferToUnknown(@RequestBody ItemTransfer itemTransfer) {
        final String PQMN  = "addItemTransferToUnknown";
        logger.info(PQMN + " - Start");
        String id = java.util.UUID.randomUUID().toString();
        itemTransfer.setId(id);
        itemTransfer.setUnknown(true);
        try {
            this.inventoryOpsService.addItemTransfer(itemTransfer);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    IdResponse
                            .builder()
                            .id(id)
                            .build());
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            Arrays.stream(ex.getStackTrace()).iterator().forEachRemaining(x -> logger.error(x.toString()));
            return  getIDResponseForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }

    @PutMapping("/purchaserequest/cancel")
    public ResponseEntity<IdResponse> cancelPurchaseRequest(@RequestBody String purchaseRequestID) {
        final String PQMN  = "cancelPurchaseRequest";
        logger.info(PQMN + " - Start");
        try {
            this.inventoryOpsService.cancelPurchaseRequest(purchaseRequestID);
            return ResponseEntity.status(HttpStatus.OK).body(
                    IdResponse
                            .builder()
                            .id("Success")
                            .build());
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            Arrays.stream(ex.getStackTrace()).iterator().forEachRemaining(x -> logger.error(x.toString()));
            return getIDResponseForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }

    @PutMapping("/itemstransfer/updatestatus")
    public ResponseEntity<IdResponse> updateItemTransferStatus(@RequestBody ItemTransferUpdateStatusRequest itemTransferUpdateStatusRequest) {
        final String PQMN  = "rejectItemTransfer";
        logger.info(PQMN + " - Start");
        try {
            this.inventoryOpsService.updatePurchaseRequestStatusInItemTransfer(
                    itemTransferUpdateStatusRequest.getItemTransferID()
                    , itemTransferUpdateStatusRequest.getPurchaseRequestStatus());

            return ResponseEntity.status(HttpStatus.OK).body(
                    IdResponse
                            .builder()
                            .id("Success")
                            .build());
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            Arrays.stream(ex.getStackTrace()).iterator().forEachRemaining(x -> logger.error(x.toString()));
            return getIDResponseForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }

    private String getErrorMessage(Exception ex) {
        String error = "Something went wrong";
        if (ex.getClass() == IllegalArgumentException.class || ex.getClass() == InsufficientResourcesException.class) {
            error = ex.getMessage();
        }
        return error;
    }
    private HttpStatus getHTTPStatus(Exception ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (ex.getClass() == IllegalArgumentException.class) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex.getClass() == InsufficientResourcesException.class) {
            status = HttpStatus.NOT_ACCEPTABLE;
        }
        return status;
    }

    private ResponseEntity<IdResponse> getIDResponseForInternalServerError(Exception ex) {
        return ResponseEntity.status(getHTTPStatus(ex)).body(
                IdResponse
                        .builder()
                        .error(getErrorMessage(ex))
                        .build());
    }

    private ResponseEntity<PurchaseRequestResponse> getPurchaseRequestResponseEntityForInternalServerError(Exception ex) {
        return ResponseEntity.status(getHTTPStatus(ex)).body(
                PurchaseRequestResponse
                        .builder()
                        .error(getErrorMessage(ex))
                        .build()
        );
    }

    private ResponseEntity<ItemTransferCollectionResponse> getItemTransferCollectionResponseEntityForInternalServerError(Exception ex) {
        return ResponseEntity.status(getHTTPStatus(ex)).body(
                ItemTransferCollectionResponse
                        .builder()
                        .error(getErrorMessage(ex))
                        .build()
        );
    }

    private ResponseEntity<ItemTransferResponse> getItemTransferResponseEntityForInternalServerError(Exception ex) {
        return ResponseEntity.status(getHTTPStatus(ex)).body(
                ItemTransferResponse
                        .builder()
                        .error(getErrorMessage(ex))
                        .build()
        );
    }

    private ResponseEntity<PurchaseRequestCollectionResponse> getPurchaseRequestCollectionResponseEntityForInternalServerError(Exception ex) {
        return ResponseEntity.status(getHTTPStatus(ex)).body(
                PurchaseRequestCollectionResponse
                        .builder()
                        .error(getErrorMessage(ex))
                        .build()
        );
    }

    private ResponseEntity<CartEntriesResponse> getCartEntriesResponseForInternalServerError(Exception ex) {
        return ResponseEntity.status(getHTTPStatus(ex)).body(
                CartEntriesResponse
                        .builder()
                        .error(getErrorMessage(ex))
                        .build()
        );
    }
}
