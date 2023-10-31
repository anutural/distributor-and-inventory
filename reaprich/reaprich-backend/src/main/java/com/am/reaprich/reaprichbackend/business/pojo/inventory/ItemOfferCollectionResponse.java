package com.am.reaprich.reaprichbackend.business.pojo.inventory;

import com.am.reaprich.reaprichbackend.data.entities.inventory.ItemOffer;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ItemOfferCollectionResponse {
    private List<ItemOffer> itemOffers;
    private String error;
}
