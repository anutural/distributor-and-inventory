package com.am.reaprich.reaprichbackend.webservice;

import com.am.reaprich.reaprichbackend.business.pojo.IdResponse;
import com.am.reaprich.reaprichbackend.business.pojo.inventoryops.*;
import com.am.reaprich.reaprichbackend.business.service.inventory.InventoryOpsService;
import com.am.reaprich.reaprichbackend.data.entities.inventoryops.ItemTransfer;
import com.am.reaprich.reaprichbackend.data.entities.inventoryops.PurchaseRequest;
import com.am.reaprich.reaprichbackend.data.entities.inventoryops.transactionsprovider.PurchaseRequestStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.POST})
@RequestMapping("/v1/inventoryops")
public class InventoryOpsWebServiceController {
    private static final Logger logger = LogManager.getLogger(InventoryOpsWebServiceController.class);
    @Autowired
    private InventoryOpsService inventoryOpsService;

    @GetMapping("/purchaserequest")
    public ResponseEntity<PurchaseRequestResponse> getPurchaseRequest(String purchaseRequestID) {
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
            logger.info(ex.getStackTrace());
            return getPurchaseRequestResponseEntityForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }

    @PostMapping("/allpurchaserequests")
    public ResponseEntity<PurchaseRequestCollectionResponse> getAllPurchaseRequests(AllPurchaseRequestsReq allPurchaseRequestsReq) {
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
            logger.info(ex.getStackTrace());
            return getPurchaseRequestCollectionResponseEntityForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }

    @GetMapping("/itemstransfer")
    public ResponseEntity<ItemTransferResponse> getItemTransfer (String itemTransferID) {
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
            logger.info(ex.getStackTrace());
            return getItemTransferResponseEntityForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }

    @PostMapping("/allitemstransfer")
    public ResponseEntity<ItemTransferCollectionResponse> getAllItemTransfer (AllItemTransferRequest allItemTransferRequest) {
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
            logger.info(ex.getStackTrace());
            return getItemTransferCollectionResponseEntityForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }


    @PostMapping("/purchaserequest")
    public ResponseEntity<IdResponse> addPurchaseRequest(PurchaseRequest purchaseRequest) {
        final String PQMN  = "addPurchaseRequest";
        logger.info(PQMN + " - Start");
        String id = java.util.UUID.randomUUID().toString();
        purchaseRequest.setId(id);
        try {
            this.inventoryOpsService.addPurchaseRequest(purchaseRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    IdResponse
                            .builder()
                            .id(id)
                            .build());
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            logger.info(ex.getStackTrace());
            return  getIDResponseForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }

    @PostMapping("/itemstransfer")
    public ResponseEntity<IdResponse> addItemTransfer(ItemTransfer itemTransfer) {
        final String PQMN  = "addItemTransfer";
        logger.info(PQMN + " - Start");
        String id = java.util.UUID.randomUUID().toString();
        itemTransfer.setId(id);
        itemTransfer.setUnknown(false);
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
            logger.info(ex.getStackTrace());
            return  getIDResponseForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }

    @PostMapping("/itemstransfertounknown")
    public ResponseEntity<IdResponse> addItemTransferToUnknown(ItemTransfer itemTransfer) {
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
            logger.info(ex.getStackTrace());
            return  getIDResponseForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }

    @PutMapping("/purchaserequest/cancel")
    public ResponseEntity<IdResponse> cancelPurchaseRequest(String purchaseRequestID) {
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
            logger.info(ex.getStackTrace());
            return getIDResponseForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }

    @PutMapping("/itemstransfer/reject")
    public ResponseEntity<IdResponse> rejectItemTransfer(String itemTransferID) {
        final String PQMN  = "rejectItemTransfer";
        logger.info(PQMN + " - Start");
        try {
            this.inventoryOpsService.rejectItemTransfer(itemTransferID);
            return ResponseEntity.status(HttpStatus.OK).body(
                    IdResponse
                            .builder()
                            .id("Success")
                            .build());
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            logger.info(ex.getStackTrace());
            return getIDResponseForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }

    @PutMapping("/itemstransfer/accept")
    public ResponseEntity<IdResponse> acceptItemTransfer(String itemTransferID) {
        final String PQMN  = "acceptItemTransfer";
        logger.info(PQMN + " - Start");
        try {
            this.inventoryOpsService.acceptItemTransfer(itemTransferID);
            return ResponseEntity.status(HttpStatus.OK).body(
                    IdResponse
                            .builder()
                            .id("Success")
                            .build());
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            logger.info(ex.getStackTrace());
            return getIDResponseForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
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
}
