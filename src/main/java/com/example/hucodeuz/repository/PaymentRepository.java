package com.example.hucodeuz.repository;

import com.example.hucodeuz.entity.Group;
import com.example.hucodeuz.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author "Husniddin Ulachov"
 * @created 2:50 PM on 6/26/2022
 * @project Edu-Center
 */
public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
