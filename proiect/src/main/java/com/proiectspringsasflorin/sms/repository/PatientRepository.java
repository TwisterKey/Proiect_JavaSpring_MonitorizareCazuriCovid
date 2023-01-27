package com.proiectspringsasflorin.sms.repository;

import com.proiectspringsasflorin.sms.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//Acest cod reprezinta o interfata care extinde interfata JpaRepository si se ocupa de persistența datelor pentru clasa
// Patient. JpaRepository este o interfata predefinita din Spring Data JPA care ofera metode de baza pentru operatiuni
// CRUD (create, read, update, delete) pentru o entitate.
public interface PatientRepository extends JpaRepository<Patient, Long>{

    @Query(value="SELECT * FROM patients p where p.name like %:keyword%", nativeQuery = true)
    List<Patient> findByKeyword(@Param("keyword") String keyword);

} //face legatura intre tabele si baza de date
