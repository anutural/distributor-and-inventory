package com.am.reaprich.reaprichbackend.util;


import com.am.reaprich.reaprichbackend.data.entities.actors.actorprovider.ActorType;
import com.am.reaprich.reaprichbackend.data.repositories.actors.actorprovider.ActorTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private ActorTypeRepository actorTypeRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        this.actorTypeRepository.save(getActorType("Outlet"));
        this.actorTypeRepository.save(getActorType("TD"));
        this.actorTypeRepository.save(getActorType("Customer"));
    }

    private ActorType getActorType(String Id) {
        ActorType actorType = new ActorType();
        actorType.setId(Id);
        actorType.setDesc(Id);
        return  actorType;
    }
}
