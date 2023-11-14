package com.am.reaprich.reaprichbackend.business.pojo.uermanagement;

import com.am.reaprich.reaprichbackend.data.entities.actors.Outlet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class OutletCollectionResponse {
    private List<Outlet> outlets;
    private String error;
}
