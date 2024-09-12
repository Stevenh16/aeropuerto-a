package com.example.aeropuertoa.repository;

import com.example.aeropuertoa.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
