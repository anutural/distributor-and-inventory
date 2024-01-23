package com.am.reaprich.reaprichbackend.business.service.inventory;

import com.am.reaprich.reaprichbackend.business.pojo.inventoryops.AllItemTransferRequest;
import com.am.reaprich.reaprichbackend.business.pojo.inventoryops.AllPurchaseRequestsReq;
import com.am.reaprich.reaprichbackend.business.pojo.inventoryops.PRRequest;
import com.am.reaprich.reaprichbackend.business.service.usermanagement.ActorService;
import com.am.reaprich.reaprichbackend.data.entities.actors.Outlet;
import com.am.reaprich.reaprichbackend.data.entities.inventory.WarehouseInventory;
import com.am.reaprich.reaprichbackend.data.entities.inventoryops.CartEntry;
import com.am.reaprich.reaprichbackend.data.entities.inventoryops.ItemTransfer;
import com.am.reaprich.reaprichbackend.data.entities.inventoryops.PurchaseRequest;
import com.am.reaprich.reaprichbackend.data.entities.inventoryops.transactionsprovider.CartEntryStatus;
import com.am.reaprich.reaprichbackend.data.entities.inventoryops.transactionsprovider.PRQntPriceHolder;
import com.am.reaprich.reaprichbackend.data.entities.inventoryops.transactionsprovider.PurchaseRequestStatus;
import com.am.reaprich.reaprichbackend.data.entities.inventoryops.transactionsprovider.PurchaseRequestType;
import com.am.reaprich.reaprichbackend.data.repositories.inventoryops.CartEntriesRepository;
import com.am.reaprich.reaprichbackend.data.repositories.inventoryops.ItemTransferRepository;
import com.am.reaprich.reaprichbackend.data.repositories.inventoryops.PurchaseRequestRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.InsufficientResourcesException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class InventoryOpsService {
    @Autowired
    private PurchaseRequestRepository purchaseRequestRepository;
    @Autowired
    private ItemTransferRepository itemTransferRepository;
    @Autowired
    private CartEntriesRepository cartEntriesRepository;
    @Autowired
    private ActorService actorService;
    @Autowired
    private InventoryOpsFilterService inventoryOpsFilterService;
    @Autowired
    private InventoryService inventoryService;

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

    public List<CartEntry> getCartEntriesForOutlet(String outletID) {
        Iterable<CartEntry> cartEntries = this.cartEntriesRepository.findByOutletID(outletID);
        if (cartEntries.iterator().hasNext()) {
            return StreamSupport
                    .stream(cartEntries.spliterator(), false)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
    private CartEntry getCartEntryForOutletAndItem(String outletID, String itemID) {
        List<CartEntry> cartEntryForOutlet = getCartEntriesForOutlet(outletID);
        if (cartEntryForOutlet.size() > 0) {
            for (CartEntry cartEntery: cartEntryForOutlet) {
                System.out.println(cartEntery.getItem().getId() + " -- " + itemID);
            }
            cartEntryForOutlet = cartEntryForOutlet.stream().filter(c -> c.getItem().getId().equals(itemID)).collect(Collectors.toList());
            if (cartEntryForOutlet.size() > 0) {
                System.out.println("Cart Entry Found");
                return cartEntryForOutlet.get(0);
            }
        }
        return null;
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

        validatePurchaseRequestForItemTransfer(itemTransfer.getPurchaseRequest());
        PurchaseRequest purchaseRequest = updatePurchaseRequestStatus(itemTransfer.getPurchaseRequest().getId(), PurchaseRequestStatus.PROCESSING);

        itemTransfer.setPurchaseRequest(purchaseRequest);
        addUpdateItemTransfer(itemTransfer);
    }

    private void validatePurchaseRequestForItemTransfer(PurchaseRequest purchaseRequest) throws  Exception {
        Map<String, List<WarehouseInventory>> inventoryItems;
        if (purchaseRequest.getPurchaseRequestType() == PurchaseRequestType.OUTLET_TO_OUTLET) {
            inventoryItems = inventoryService.getInventoryItems(
                    inventoryService.getWarehouseByOutlet(
                                    purchaseRequest.getRequestFrom().getId())
                            .getId());
        } else if (purchaseRequest.getPurchaseRequestType() == PurchaseRequestType.COMPANY_TO_OUTLET) {
            inventoryItems = inventoryService.getCompanyInvetoryItems();
        } else {
            throw new IllegalArgumentException("Invalid or not supported Purchase request type provided");
        }
        List<PRQntPriceHolder> prQntPriceHolderList = purchaseRequest.getPRQntPriceHolders();

        List<String> insufficientItems = new ArrayList<>();
        for (PRQntPriceHolder prQntPriceHolder: prQntPriceHolderList) {
            if (inventoryItems.containsKey(prQntPriceHolder.getItem().getId())){
                if (inventoryItems.get(prQntPriceHolder.getItem().getId()).size() >= prQntPriceHolder.getQuantity()) {
                    insufficientItems.add(prQntPriceHolder.getItem().getName() + " - " + prQntPriceHolder.getItem().getId());
                }
            } else {
                insufficientItems.add(prQntPriceHolder.getItem().getName() + " - " + prQntPriceHolder.getItem().getId());
            }
        }
        if (insufficientItems.size() > 0) {
            String errorMessage = "Inventory doesn't have or have insufficient stock for following items";
            errorMessage += " - " + String.join(",", insufficientItems);
            throw new InsufficientResourcesException(errorMessage);
        }
    }

    public void updateItemTransfer(ItemTransfer itemTransfer) throws Exception {
        ItemTransfer itemTransferFormDB = getItemTransfer(itemTransfer.getId());
        itemTransferFormDB.setPurchaseRequest(getPurchaseRequest(itemTransfer.getPurchaseRequest().getId()));
        addUpdateItemTransfer(itemTransferFormDB);
    }

    public void addUpdateItemTransfer(ItemTransfer itemTransfer) throws  Exception {
        this.itemTransferRepository.save(itemTransfer);
    }

    public void cancelPurchaseRequest(String purchaseRequestID) throws Exception {
        updatePurchaseRequestStatus(purchaseRequestID, PurchaseRequestStatus.CANCELED);
    }

    public ItemTransfer updatePurchaseRequestStatusInItemTransfer(String itemTransferID, PurchaseRequestStatus status) throws Exception{
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

    public PurchaseRequest generatePurchaseRequest(PRRequest prRequest) throws Exception {
        PurchaseRequest purchaseRequest = new PurchaseRequest();
        purchaseRequest.setId(java.util.UUID.randomUUID().toString());
        purchaseRequest.setPurchaseRequestType(prRequest.getPurchaseRequestType());
        purchaseRequest.setRequestFrom(this.actorService.getOutletById(prRequest.getRequesterOutlet()));

        if (purchaseRequest.getPurchaseRequestType()  == PurchaseRequestType.OUTLET_TO_OUTLET) {
            purchaseRequest.setRequestTo(this.actorService.getOutletById(prRequest.getRequestedToOutlet()));
        }
        purchaseRequest.setDate(new Date());
        purchaseRequest.setPRQntPriceHolders(getPRQntPriceHolderList(prRequest.getCartEntryIDs(), purchaseRequest.getRequestFrom()));

        return purchaseRequest;
    }

    private List<PRQntPriceHolder> getPRQntPriceHolderList(List<String> cartEntryIDs, Outlet requestFrom) throws Exception {
        List<PRQntPriceHolder> prQntPriceHolderList = new ArrayList<>();
        for (String cartEntryID: cartEntryIDs ) {
            CartEntry cartEntry = getCartEntryFromID(cartEntryID);

            prQntPriceHolderList.add(generatePRQntPriceHolder(cartEntry,requestFrom));

            cartEntry.setStatus(CartEntryStatus.CHECKED_OUT);
            updateItemIntoCart(cartEntry);
        }
        return prQntPriceHolderList;
    }

    private PRQntPriceHolder generatePRQntPriceHolder(CartEntry cartEntry, Outlet outlet) {
        return PRQntPriceHolder.builder()
                .id(UUID.randomUUID().toString())
                .quantity(cartEntry.getItemQuantity())
                .price(cartEntry.getItem().getPriceForOutletType(outlet.getOutletType()))
                .item(cartEntry.getItem())
                .build();
    }

    public String addItemIntoCart(CartEntry cartEntry) throws Exception {
        CartEntry cartEntryOptional = getCartEntryForOutletAndItem(cartEntry.getOutlet().getId(), cartEntry.getItem().getId());
        if (cartEntryOptional == null) {
            String id = java.util.UUID.randomUUID().toString();
            cartEntry.setId(id);
            cartEntry.setStatus(CartEntryStatus.ADDED);
            cartEntry.setCreatedDate(new Date());
            cartEntry.setUpdatedDate(new Date());
            this.cartEntriesRepository.save(cartEntry);
            return id;
        }
        cartEntryOptional.setItemQuantity(cartEntryOptional.getItemQuantity() + cartEntry.getItemQuantity());
        updateItemIntoCart(cartEntryOptional);
        return cartEntryOptional.getId();
    }

    public String updateItemIntoCart(CartEntry cartEntry) throws Exception {
        CartEntry cartEntryOptional = getCartEntryForOutletAndItem(cartEntry.getOutlet().getId(), cartEntry.getItem().getId());
        if (cartEntryOptional == null) {
            throw new IllegalArgumentException("Cart entry with provided ID doesn't exist");
        }
        cartEntryOptional.setItem(cartEntry.getItem());
        cartEntryOptional.setItemQuantity(cartEntry.getItemQuantity());
        cartEntryOptional.setUpdatedDate(new Date());

        this.cartEntriesRepository.save(cartEntryOptional);
        return cartEntryOptional.getId();
    }

    public void removeEntryFromCart(String cartEntryID) throws Exception {
        CartEntry cartEntry = getCartEntryFromID(cartEntryID);
        cartEntry.setStatus(CartEntryStatus.REMOVED);
        this.cartEntriesRepository.save(cartEntry);
    }

    public CartEntry getCartEntryFromID(String cartEntryID) {
        Optional <CartEntry> cartEntryOptional = this.cartEntriesRepository.findById(cartEntryID);
        if (cartEntryOptional.isEmpty()) {
            throw new IllegalArgumentException("Cart entry with provided ID doesn't exist");
        }
        return cartEntryOptional.get();

    }
}


