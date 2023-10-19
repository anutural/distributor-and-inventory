package com.am.reaprich.reaprichbackend.util;


import com.am.reaprich.reaprichbackend.data.entities.actors.actorprovider.ActorType;
import com.am.reaprich.reaprichbackend.data.entities.actors.outletprovider.OutletType;
import com.am.reaprich.reaprichbackend.data.repositories.actors.actorprovider.ActorTypeRepository;
import com.am.reaprich.reaprichbackend.data.repositories.actors.outletprovider.OutletTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private ActorTypeRepository actorTypeRepository;
    @Autowired
    private OutletTypeRepository outletTypeRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        this.actorTypeRepository.save(getActorType("Outlet"));
        this.actorTypeRepository.save(getActorType("TD"));
        this.actorTypeRepository.save(getActorType("Customer"));

        if (!this.outletTypeRepository.existsById("pl")) {
            this.outletTypeRepository.save(getOutletType("pl", "Platinum", (float)20.0));
        }
        if (!this.outletTypeRepository.existsById("gl")) {
            this.outletTypeRepository.save(getOutletType("gl", "Gold", (float)15.0));
        }
        if (!this.outletTypeRepository.existsById("sl+")) {
            this.outletTypeRepository.save(getOutletType("sl+", "Silver Plus", (float)10.0));
        }
        if (!this.outletTypeRepository.existsById("sl")) {
            this.outletTypeRepository.save(getOutletType("sl", "Silver", (float)8.0));
        }
    }

    private ActorType getActorType(String Id) {
        ActorType actorType = new ActorType();
        actorType.setId(Id);
        actorType.setDesc(Id);
        return  actorType;
    }

    private OutletType getOutletType(String id, String name, float discount) {
        OutletType outletType = new OutletType();
        outletType.setId(id);
        outletType.setName(name);
        outletType.setDiscount(discount);
        return outletType;
    }
}
