package com.hsf302.socialnetwork.service.impl;

import com.hsf302.socialnetwork.enity.Post;
import com.hsf302.socialnetwork.enity.Report;
import com.hsf302.socialnetwork.enity.Users;
import com.hsf302.socialnetwork.repo.PostRepo;
import com.hsf302.socialnetwork.repo.ReportRepo;
import com.hsf302.socialnetwork.repo.UserRepo;
import com.hsf302.socialnetwork.service.IsReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService implements IsReportService {
    @Autowired
    ReportRepo repo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    PostRepo postRepo;
    @Override
    public void createReport(String content, long postid, long userid) {

        Post  post = postRepo.findById(postid).get();
        Users  user = userRepo.findById(userid).get();
        Report report = new Report(content);
        report.setPost(post);
        post.getReports().add(report);

        report.setUser(user);
        user.addReport(report);


        repo.save(report);
        userRepo.save(user);
        postRepo.save(post);
    }

    @Override
    public Page<Report> getAllReportsByStatus(boolean status, Pageable pageable) {
        return repo.getAllByBandedOrderByCreatedAtDesc(status,pageable);
    }

    @Override
    public void banded(int postid) {
        Report report = repo.findById(postid).get();
        report.setBanded(true);
        repo.save(report);

    }
    public List<Long> canreport(List<Post> posts,Users user)
    {
        List<Long> list = new ArrayList<Long>();
        for (Post post : posts) {
            if(!repo.existsByPostAndUser(post,user))
            {
                list.add(post.getId());
            }
        }
        return list;
    }
}
