package com.am.reaprich.reaprichbackend.business.pojo.inventory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class AllItemRequest {
    private ItemFilterBy itemFilterBy;
    private String filter;
}
