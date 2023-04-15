package com.distributed_system.term_project.member.repository;


import com.distributed_system.term_project.member.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member,Long> {

    Optional<Member> findByName(String name);
}
