package com.am.reaprich.reaprichbackend.data.entities.addressprovider;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "ADD_STATE")
@NoArgsConstructor
public class ADDState{
    @Id
    @Column(name = "ADD_STATE_ID")
    @Getter
    @Setter
    private String id;

    @Column(name = "NAME")
    private String name;
}
