package com.am.reaprich.reaprichbackend.data.repositories.inventory;

import com.am.reaprich.reaprichbackend.data.entities.inventory.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, String> {
}
