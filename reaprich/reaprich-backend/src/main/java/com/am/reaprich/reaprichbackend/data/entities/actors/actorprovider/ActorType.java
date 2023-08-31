package com.am.reaprich.reaprichbackend.data.entities.actors.actorprovider;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACTOR_TYPE")
@NoArgsConstructor
public class ActorType {
    @Id
    @Getter
    @Setter
    @Column(name = "ACTOR_TYPE_ID")
    private String id;

    @Getter
    @Setter
    @Column(name = "ACTOR_TYPE_DESC")
    private String desc;
}
