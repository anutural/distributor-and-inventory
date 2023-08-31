package com.am.reaprich.reaprichbackend.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.am.reaprich.reaprichbackend.data.repositories.*;
import com.am.reaprich.reaprichbackend.data.entities.Guest;

import java.util.Date;

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {
    private  final GuestRepository guestRepository;

    @Autowired
    public AppStartupEvent(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
//        Iterable<Room> rooms = roomRepository.findAll();
//        for (Room room : rooms) {
//        System.out.println("***********************************************************");
//            System.out.println(room);
//        }
        System.out.println("***********************************************************");
        Iterable<Guest> guests = guestRepository.findAll();
        for (Guest guest : guests) {
            System.out.println(guest);
        }
//        System.out.println("***********************************************************");
//        Iterable<Reservation> reservations = reservationRepository.findAll();
//        for (Reservation reservation : reservations) {
//            System.out.println(reservation);
//        }
//        System.out.println("***********************************************************");
//        System.out.println("###############################################################");
    }
}
