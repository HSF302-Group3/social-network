package com.hsf302.socialnetwork.service;

import com.hsf302.socialnetwork.enity.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IsReportService {
    void createReport(String content, long postid, long userid);
    Page<Report> getAllReportsByStatus(boolean status, Pageable pageable);
    void banded(int postid);

}
