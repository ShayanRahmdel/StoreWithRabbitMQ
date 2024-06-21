package com.example.eurekaserver;


import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepository extends JpaRepository<ServerInfo,Integer> {
}
