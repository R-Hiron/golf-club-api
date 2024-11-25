package com.keyin.golfclubapi.controller;

import com.keyin.golfclubapi.entity.Member;
import com.keyin.golfclubapi.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @PostMapping
    public Member addMember(@RequestBody Member member) {
        return memberRepository.save(member);
    }

    @GetMapping
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new RuntimeException("Member not found with ID: " + id));
    }
    @GetMapping("/search")
    public List<Member> searchMembers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer type,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate tournamentStartDate) {

        if (name != null) return memberRepository.findByName(name);
        if (type != null) return memberRepository.findByMembershipType(type);
        if (phoneNumber != null) return memberRepository.findByPhoneNumber(phoneNumber);
        if (tournamentStartDate != null) return memberRepository.findByTournamentStartDate(tournamentStartDate);

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No valid search criteria provided");
    }


}
