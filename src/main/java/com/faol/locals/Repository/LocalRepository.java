package com.faol.locals.Repository;

import com.faol.locals.Entities.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocalRepository extends JpaRepository<Local, Long> {

    Optional<Local>findByName(String name);

    Optional<Local>findByNameIgnoreCase(String name);

    @Modifying
    @Query(
            value = "DELETE FROM locals WHERE name= :name",
            nativeQuery = true
    )
    void deleteByName(@Param("name") String name);

    List<Local> findByNameContaining(String name);

    List<Local> findByCodeContaining(String code);

    List<Local> findByFloor(String floor);



}
