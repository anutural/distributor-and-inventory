package com.am.reaprich.reaprichbackend.data.repositories.inventory;

import com.am.reaprich.reaprichbackend.data.entities.inventory.Coupon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends CrudRepository <Coupon, String> {
}
