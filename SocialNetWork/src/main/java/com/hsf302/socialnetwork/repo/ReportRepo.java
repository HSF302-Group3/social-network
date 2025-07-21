package com.hsf302.socialnetwork.repo;

import com.hsf302.socialnetwork.enity.Post;
import com.hsf302.socialnetwork.enity.Report;
import com.hsf302.socialnetwork.enity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepo extends JpaRepository<Report, Integer> {

    boolean existsByPostAndUser(Post post, Users user);

    List<Report> getAllByBandedOrderByCreatedAtDesc(boolean banded);

    Page<Report> getAllByBandedOrderByCreatedAtDesc(boolean banded, Pageable pageable);
}
