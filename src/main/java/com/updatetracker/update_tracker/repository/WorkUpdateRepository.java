package com.updatetracker.update_tracker.repository;

import com.updatetracker.update_tracker.model.WorkUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface WorkUpdateRepository extends JpaRepository<WorkUpdate, Long> {

 @Query("""
        select u
        from WorkUpdate u
        join fetch u.user usr
        join fetch u.project p
        where (:username is null
               or lower(usr.username) = lower(:username))
          and (:projectName is null
               or lower(p.name) = lower(:projectName))
          and (:fromDate is null
               or u.updateDate >= :fromDate)
          and (:toDate is null
               or u.updateDate <= :toDate)
        order by u.updateDate desc, u.createdAt desc
        """)
 List<WorkUpdate> search(
         @Param("username") String username,
         @Param("projectName") String projectName,
         @Param("fromDate") LocalDate fromDate,
         @Param("toDate") LocalDate toDate
 );

 @Query("""
        select u
        from WorkUpdate u
        join fetch u.user usr
        join fetch u.project p
        where usr.id = :userId
        order by u.updateDate desc, u.createdAt desc
        """)
 List<WorkUpdate> findByUserIdOrderByUpdateDateDesc(
         @Param("userId") Long userId
 );
}