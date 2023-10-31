package com.am.reaprich.reaprichbackend.data.repositories.inventory.inventoryprovider;

import com.am.reaprich.reaprichbackend.data.entities.inventory.inventoryprovider.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, String> {
}
