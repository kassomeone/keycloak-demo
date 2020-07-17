package com.oath2.demo.demo.dao;

import com.oath2.demo.demo.model.Car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "*")
public interface CarRepository extends JpaRepository<Car, Long> {
}