package com.example.hucodeuz.repository;

import com.example.hucodeuz.entity.Group;
import com.example.hucodeuz.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author "Husniddin Ulachov"
 * @created 2:50 PM on 6/26/2022
 * @project Edu-Center
 */
public interface PaymentRepository extends JpaRepository<Payment, UUID> {
      // hammasi paga va size
      // filial buyicha
      // student buyicha
      // data buyicha 1 - 10 gacha
      // (vaqt va page buyicha)
      Page<Payment> findAllByCreatedAtBetween(Date start, Date end, Pageable pageable);

      Page<Payment> findAllByCreatedAtBetweenAndStudent_FullNameContainingIgnoreCase(Date start,Date end,String student,Pageable pageable);
      Page<Payment> findAllByCreatedAtBetweenAndFilial_NameContainingIgnoreCase(Date start,Date end,String filial,Pageable pageable);

      Page<Payment> findAllByCreatedAtBetweenAndStudent_FullNameContainingIgnoreCaseAndFilial_NameContainingIgnoreCase(Date start,Date end,String full_name,String name, Pageable pageable);



}
