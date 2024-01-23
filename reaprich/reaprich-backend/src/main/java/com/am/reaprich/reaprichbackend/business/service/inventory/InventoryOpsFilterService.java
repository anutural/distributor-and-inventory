package com.am.reaprich.reaprichbackend.business.service.inventory;

import com.am.reaprich.reaprichbackend.business.pojo.inventoryops.AllItemTransferRequest;
import com.am.reaprich.reaprichbackend.business.pojo.inventoryops.AllPurchaseRequestsReq;
import com.am.reaprich.reaprichbackend.business.pojo.inventoryops.ItemTransferCollectionResponse;
import com.am.reaprich.reaprichbackend.business.pojo.inventoryops.PurchaseRequestCollectionResponse;
import com.am.reaprich.reaprichbackend.data.entities.inventory.Item;
import com.am.reaprich.reaprichbackend.data.entities.inventoryops.ItemTransfer;
import com.am.reaprich.reaprichbackend.data.entities.inventoryops.PurchaseRequest;
import com.am.reaprich.reaprichbackend.data.repositories.inventoryops.ItemTransferRepository;
import com.am.reaprich.reaprichbackend.data.repositories.inventoryops.PurchaseRequestRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.cfg.annotations.reflection.internal.XMLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class InventoryOpsFilterService {
    @Autowired
    private PurchaseRequestRepository purchaseRequestRepository;
    @Autowired
    private ItemTransferRepository itemTransferRepository;

    public List<PurchaseRequest> getAllPurchaseRequestsByFilter (AllPurchaseRequestsReq allPurchaseRequestsReq) throws  Exception{
        return InventoryOpsFilter.doPurchaseRequestFilter(purchaseRequestRepository, allPurchaseRequestsReq);
    }
    public List<ItemTransfer>  getAllItemTransfersByFilter (AllItemTransferRequest allItemTransferRequest) throws  Exception{
        return InventoryOpsFilter.doItemTransferFilter(itemTransferRepository, allItemTransferRequest);
    }
}

class InventoryOpsFilter {
    public static List<PurchaseRequest> doPurchaseRequestFilter(PurchaseRequestRepository purchaseRequestRepository, AllPurchaseRequestsReq allPurchaseRequestsReq){
        List<PurchaseRequest> allPurchaseRequests = StreamSupport
                .stream(purchaseRequestRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        switch (allPurchaseRequestsReq.getPurchaseRequestFilterBy()) {
            case NONE:
                return allPurchaseRequests;
            case DATE_RANGE:
            case STATUS:
                throw new IllegalArgumentException("Specified purchase request filter type is not yet implemented");
            default:
                throw new IllegalArgumentException("Specified purchase request filter type is not valid");
        }
    }

    public static List<ItemTransfer> doItemTransferFilter(ItemTransferRepository itemTransferRepository, AllItemTransferRequest allItemTransferRequest){
        List<ItemTransfer> allItemTransfers = StreamSupport
                .stream(itemTransferRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        switch (allItemTransferRequest.getItemTransferFilterBy()) {
            case NONE:
                return allItemTransfers;
            case DATE_RANGE:
            case STATUS:
                throw new IllegalArgumentException("Specified item transfer filter type is not yet implemented");
            default:
                throw new IllegalArgumentException("Specified item transfer filter type is not valid");
        }
    }
}


