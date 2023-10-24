package com.bda.skila.repositories;


import com.bda.skila.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findFirstByAddress_CityId(long id);
}
