package com.hsf302.socialnetwork.repo;

import com.hsf302.socialnetwork.enity.Post;
import com.hsf302.socialnetwork.enity.Report;
import com.hsf302.socialnetwork.enity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportRepo extends JpaRepository<Report, Integer> {

    boolean existsByPostAndUser(Post post, Users user);

    List<Report> getAllByBandedOrderByCreatedAtDesc(boolean banded);
   @Query("""
          select r from Report r where  r.banded = :banded and r.post.id not in (select  r.post.id from Report r where r.banded = true)
          order by r.createdAt desc
""")
    Page<Report> getAllByBandedOrderByCreatedAtDesc(boolean banded, Pageable pageable);
}
