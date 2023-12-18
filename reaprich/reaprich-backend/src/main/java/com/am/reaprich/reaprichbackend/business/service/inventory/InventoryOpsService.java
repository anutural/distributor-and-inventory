package com.am.reaprich.reaprichbackend.business.service.inventory;

import com.am.reaprich.reaprichbackend.business.pojo.inventoryops.AllItemTransferRequest;
import com.am.reaprich.reaprichbackend.business.pojo.inventoryops.AllPurchaseRequestsReq;
import com.am.reaprich.reaprichbackend.business.pojo.inventoryops.PurchaseRequestCollectionResponse;
import com.am.reaprich.reaprichbackend.data.entities.actors.Outlet;
import com.am.reaprich.reaprichbackend.data.entities.inventoryops.ItemTransfer;
import com.am.reaprich.reaprichbackend.data.entities.inventoryops.PurchaseRequest;
import com.am.reaprich.reaprichbackend.data.entities.inventoryops.transactionsprovider.PurchaseRequestStatus;
import com.am.reaprich.reaprichbackend.data.repositories.inventoryops.ItemTransferRepository;
import com.am.reaprich.reaprichbackend.data.repositories.inventoryops.PurchaseRequestRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class InventoryOpsService {
    private PurchaseRequestRepository purchaseRequestRepository;
    private ItemTransferRepository itemTransferRepository;

    private InventoryOpsFilterService inventoryOpsFilterService;

    public PurchaseRequest getPurchaseRequest(String purchaseRequestID) throws Exception{
        Optional<PurchaseRequest> optionalPurchaseRequest = this.purchaseRequestRepository.findById(purchaseRequestID);
        if (optionalPurchaseRequest.isEmpty()) {
            throw new IllegalArgumentException("Purchase Request with specified id doesn't exist");
        }
        return optionalPurchaseRequest.get();
    }

    public ItemTransfer getItemTransfer(String itemTransferID) {
        Optional<ItemTransfer> optionalItemTransfer = this.itemTransferRepository.findById(itemTransferID);
        if (optionalItemTransfer.isEmpty()) {
            throw new IllegalArgumentException("Item transfer with specified id doesn't exist");
        }
        return optionalItemTransfer.get();
    }


    public List<PurchaseRequest> getAllPurchaseRequests(AllPurchaseRequestsReq allPurchaseRequestsReq) throws Exception {
        return inventoryOpsFilterService.getAllPurchaseRequestsByFilter(allPurchaseRequestsReq);
    }

    public List<ItemTransfer> getAllItemTransfer(AllItemTransferRequest allItemTransferRequest) throws Exception {
        return inventoryOpsFilterService.getAllItemTransfersByFilter(allItemTransferRequest);
    }


    public void addPurchaseRequest(PurchaseRequest purchaseRequest) throws Exception {
        if (!this.purchaseRequestRepository.findById(purchaseRequest.getId()).isEmpty()) {
            throw new IllegalArgumentException("Purchase request with the same ID is already present");
        }
        purchaseRequest.setStatus(PurchaseRequestStatus.PLACED);
        addUpdatePurchaseRequest(purchaseRequest);
    }

    public void addUpdatePurchaseRequest(PurchaseRequest purchaseRequest) {
        this.purchaseRequestRepository.save(purchaseRequest);
    }

    public void addItemTransfer(ItemTransfer itemTransfer) throws Exception {
        if (!this.itemTransferRepository.findById(itemTransfer.getId()).isEmpty()) {
            throw new IllegalArgumentException("Item transfer with the same ID is already present");
        }
        PurchaseRequest purchaseRequest = updatePurchaseRequestStatus(itemTransfer.getPurchaseRequest().getId(), PurchaseRequestStatus.ACCEPTED);
        itemTransfer.setPurchaseRequest(purchaseRequest);
        addUpdateItemTransfer(itemTransfer);
    }

    public void addUpdateItemTransfer(ItemTransfer itemTransfer) throws  Exception {
        this.itemTransferRepository.save(itemTransfer);
    }

    public void cancelPurchaseRequest(String purchaseRequestID) throws Exception {
        updatePurchaseRequestStatus(purchaseRequestID, PurchaseRequestStatus.CANCELED);
    }

    public void rejectItemTransfer(String itemTransferID) throws Exception {
        updatePurchaseRequestStatusInItemTransfer(itemTransferID, PurchaseRequestStatus.REJECTED);
    }

    public void acceptItemTransfer(String itemTransferID) throws Exception {
        updatePurchaseRequestStatusInItemTransfer(itemTransferID, PurchaseRequestStatus.DELIVERED);
    }

    private ItemTransfer updatePurchaseRequestStatusInItemTransfer(String itemTransferID, PurchaseRequestStatus status) throws Exception{
        ItemTransfer itemTransfer = getItemTransfer(itemTransferID);
        PurchaseRequest purchaseRequest = updatePurchaseRequestStatus(itemTransfer.getPurchaseRequest().getId(), status);
        itemTransfer.setPurchaseRequest(purchaseRequest);
        addUpdateItemTransfer(itemTransfer);
        return itemTransfer;
    }

    private PurchaseRequest updatePurchaseRequestStatus (String purchaseRequestID, PurchaseRequestStatus status) throws  Exception {
        PurchaseRequest purchaseRequest = getPurchaseRequest(purchaseRequestID);
        purchaseRequest.setStatus(status);
        addUpdatePurchaseRequest(purchaseRequest);
        return purchaseRequest;
    }
}


