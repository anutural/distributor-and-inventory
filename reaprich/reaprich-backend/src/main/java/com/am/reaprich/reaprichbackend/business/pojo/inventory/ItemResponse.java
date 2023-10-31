package com.am.reaprich.reaprichbackend.business.pojo.inventory;

import com.am.reaprich.reaprichbackend.data.entities.inventory.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ItemResponse {
    private Item item;
    private String error;
}
