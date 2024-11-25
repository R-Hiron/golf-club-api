package com.keyin.golfclubapi.controller;

import com.keyin.golfclubapi.entity.Member;
import com.keyin.golfclubapi.entity.Tournament;
import com.keyin.golfclubapi.repository.MemberRepository;
import com.keyin.golfclubapi.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private MemberRepository memberRepository;

    @PostMapping
    public Tournament addTournament(@RequestBody Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    @GetMapping
    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Tournament getTournamentById(@PathVariable Long id) {
        return tournamentRepository.findById(id).orElseThrow(() -> new RuntimeException("Tournament not found with ID: " + id));
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchTournaments(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Long tournamentId
    ) {
        if (startDate != null) return ResponseEntity.ok(tournamentRepository.findByStartDate(startDate));
        if (location != null) return ResponseEntity.ok(tournamentRepository.findByLocation(location));
        if (tournamentId != null) return ResponseEntity.ok(tournamentRepository.findMembersByTournament(tournamentId));
        return ResponseEntity.badRequest().body("Please provide a valid query parameter.");
    }

    @GetMapping("/{id}/members")
    public List<Member> getTournamentMembers(@PathVariable Long id) {
        Tournament tournament = tournamentRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Tournament not found"));
        return new ArrayList<>(tournament.getMembers());
    }

    @PutMapping("/{tournamentId}/add-member/{memberId}")
    public Tournament addMemberToTournament(@PathVariable Long tournamentId, @PathVariable Long memberId) {
        Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow(() -> new RuntimeException("Tournament not found with ID: " + tournamentId));
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("Member not found with ID: " + memberId));

        tournament.getMembers().add(member);
        return tournamentRepository.save(tournament);
    }
}
