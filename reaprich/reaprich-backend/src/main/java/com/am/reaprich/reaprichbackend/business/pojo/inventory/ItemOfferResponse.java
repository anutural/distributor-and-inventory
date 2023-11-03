package com.am.reaprich.reaprichbackend.business.pojo.inventory;

import com.am.reaprich.reaprichbackend.data.entities.inventory.ItemOffer;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ItemOfferResponse {
    private ItemOffer itemOffer;
    private String error;
}
