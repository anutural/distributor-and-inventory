package com.am.reaprich.reaprichbackend.business.pojo.uermanagement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AllActorsRequest {
    private ActorFilterBy actorFilterBy;
    private String filter;
    private String subFilter;
}
