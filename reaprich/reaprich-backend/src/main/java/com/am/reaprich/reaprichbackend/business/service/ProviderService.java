package com.am.reaprich.reaprichbackend.business.service;

import com.am.reaprich.reaprichbackend.data.entities.actors.actorprovider.ActorType;
import com.am.reaprich.reaprichbackend.data.repositories.actors.actorprovider.ActorTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProviderService {

    @Autowired
    private ActorTypeRepository actorTypeRepository;

    public Iterable<ActorType> GetActorTypes() { return this.actorTypeRepository.findAll();    }
    public ActorType GetActorTypeByID(String id) throws Exception {
        Optional<ActorType> optionalActorType = this.actorTypeRepository.findById(id);
        if (optionalActorType.isEmpty()) {
            throw new java.lang.NullPointerException("Actor type  not found");
        }
        return optionalActorType.get();
    }
}
