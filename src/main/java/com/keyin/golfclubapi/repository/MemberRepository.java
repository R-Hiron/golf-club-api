package com.keyin.golfclubapi.repository;

import com.keyin.golfclubapi.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDate;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("SELECT m FROM Member m WHERE m.name LIKE %:name%")
    List<Member> findByName(@Param("name") String name);

    @Query("SELECT m FROM Member m WHERE m.durationInMonths = :type")
    List<Member> findByMembershipType(@Param("type") Integer type);

    @Query("SELECT m FROM Member m WHERE m.phoneNumber LIKE %:phoneNumber%")
    List<Member> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Query("SELECT m FROM Member m JOIN m.tournaments t WHERE t.startDate = :startDate")
    List<Member> findByTournamentStartDate(@Param("startDate") LocalDate startDate);
}