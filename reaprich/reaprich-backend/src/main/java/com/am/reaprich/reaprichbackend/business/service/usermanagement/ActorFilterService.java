package com.am.reaprich.reaprichbackend.business.service.usermanagement;

import com.am.reaprich.reaprichbackend.business.pojo.uermanagement.*;
import com.am.reaprich.reaprichbackend.data.entities.actors.Customer;
import com.am.reaprich.reaprichbackend.data.entities.actors.Outlet;
import com.am.reaprich.reaprichbackend.data.entities.actors.TD;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ActorFilterService {
    private final ActorService actorService;
    public OutletCollectionResponse getOutletsByFilter(AllActorsRequest getAllOutletsRequest) throws Exception{
        List<Outlet> outlets;
        if (getAllOutletsRequest.getActorFilterBy() == ActorFilterBy.NONE){
            outlets = StreamSupport.stream(this.actorService.GetOutlets().spliterator(), false).collect(Collectors.toList());
        }
        else {
            outlets = new ActorFilter().doOutletFilter(actorService, getAllOutletsRequest);
        }
        return OutletCollectionResponse
                .builder()
                .outlets(outlets)
                .build();
    }

    public TDCollectionResponse getTDsByFilter(AllActorsRequest getAllOutletsRequest) {
        List<TD> tds;
        if (getAllOutletsRequest.getActorFilterBy() == ActorFilterBy.NONE){
            tds = StreamSupport.stream(this.actorService.GetTDs().spliterator(), false).collect(Collectors.toList());
        }
        else {
            tds = new ActorFilter().doTDFilter(actorService, getAllOutletsRequest);
        }
        return TDCollectionResponse
                .builder()
                .tds(tds)
                .build();
    }

    public CustomerCollectionResponse getCustomersByFilter(AllActorsRequest getAllOutletsRequest) {
        List<Customer> customers;
        if (getAllOutletsRequest.getActorFilterBy() == ActorFilterBy.NONE){
            customers = StreamSupport.stream(this.actorService.getCustomers().spliterator(), false).collect(Collectors.toList());
        }
        else {
            customers = new ActorFilter().doCustomerFilter(actorService, getAllOutletsRequest);
        }
        return CustomerCollectionResponse
                .builder()
                .customers(customers)
                .build();
    }
}

class ActorFilter {
    public List<Outlet> doOutletFilter(ActorService actorService, AllActorsRequest allActorsRequest) {
        switch (allActorsRequest.getActorFilterBy())
        {
            case SPECIFIC_TYPE:
            case BUSINESS_DONE_IN_DURATION:
            case INCOMPLETE_KYC:
            case NOT_APPROVED:
            case INSUFFICIENT_STOCK:
            case IN_COUNTRY:
            case IN_STATE:
            case IN_ZONE:
            case IN_DIST:
            case NAME_CHARS:
                throw new IllegalArgumentException("Filter type not yet implemented");
            default:
                throw new IllegalArgumentException("Improper filter type specified");
        }
    }

    public List<TD> doTDFilter(ActorService actorService, AllActorsRequest allActorsRequest) {
        switch (allActorsRequest.getActorFilterBy())
        {
            case BUSINESS_DONE_IN_DURATION:
            case INCOMPLETE_KYC:
            case NOT_APPROVED:
            case IN_COUNTRY:
            case IN_STATE:
            case IN_ZONE:
            case IN_DIST:
            case NAME_CHARS:
                throw new IllegalArgumentException("Filter type not yet implemented");
            default:
                throw new IllegalArgumentException("Improper filter type specified");
        }
    }

    public List<Customer> doCustomerFilter(ActorService actorService, AllActorsRequest allActorsRequest) {
        switch (allActorsRequest.getActorFilterBy())
        {
            case SPECIFIC_TYPE:
            case BUSINESS_DONE_IN_DURATION:
            case IN_COUNTRY:
            case IN_STATE:
            case IN_ZONE:
            case IN_DIST:
            case NAME_CHARS:
                throw new IllegalArgumentException("Filter type not yet implemented");
            default:
                throw new IllegalArgumentException("Improper filter type specified");
        }
    }
}
