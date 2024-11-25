package com.keyin.golfclubapi.repository;

import com.keyin.golfclubapi.entity.Member;
import com.keyin.golfclubapi.entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;


import java.util.List;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    @Query("SELECT t FROM Tournament t WHERE t.startDate = :startDate")
    List<Tournament> findByStartDate(@Param("startDate") LocalDate startDate);

    @Query("SELECT t FROM Tournament t WHERE t.location LIKE %:location%")
    List<Tournament> findByLocation(@Param("location") String location);

    @Query("SELECT m FROM Tournament t JOIN t.members m WHERE t.id = :tournamentId")
    List<Member> findMembersByTournament(@Param("tournamentId") Long tournamentId);
}

