package com.hsf302.socialnetwork.controller;

import com.hsf302.socialnetwork.enity.Report;
import com.hsf302.socialnetwork.enity.Users;
import com.hsf302.socialnetwork.enums.Role;
import com.hsf302.socialnetwork.service.impl.ReportService;
import jakarta.servlet.http.HttpSession;
import org.hibernate.annotations.ConcreteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reports")
public class ReportController {
    @Autowired
    ReportService reportService;

    @GetMapping
    public String report(Model model, HttpSession session,@RequestParam(required = false ,defaultValue = "0") int page) {
        Users users = (Users) session.getAttribute("user");
        // de test thoi tuong lai se bo no di
        if (users == null) {
//            users = repo.findByUsername("hsf302");
//            System.out.println(users.getUsername());
            session.setAttribute("user", users);
            return "redirect:/login";
        }

        if(!users.getRole().equals(Role.ADMIN))
        {
            return "redirect:/login";
        }
        PageRequest pageRequest = PageRequest.of(page, 1);//test


        model.addAttribute("user", users);
        model.addAttribute("paging", reportService.getAllReportsByStatus(false, pageRequest));
        model.addAttribute("currentPage", page);
        model.addAttribute("reports", reportService.getAllReportsByStatus(false, pageRequest).getContent());
        return "Adminreport";
    }

    @PostMapping("/create/{id}")
    public String createReport(@RequestParam String content, HttpSession session,@PathVariable Long id) {
        Users users = (Users) session.getAttribute("user");

        // de test thoi tuong lai se bo no di
        if (users == null) {
//            users = repo.findByUsername("hsf302");
//            System.out.println(users.getUsername());
            session.setAttribute("user", users);
            return "redirect:/login";
        }

        reportService.createReport(content,id,users.getUserId());
        return "redirect:/posts/friendPost";

    }
    @PostMapping("/band/{id}")
    public String createReport( HttpSession session,@PathVariable int id) {
        Users users = (Users) session.getAttribute("user");

        // de test thoi tuong lai se bo no di
        if (users == null) {
//            users = repo.findByUsername("hsf302");
//            System.out.println(users.getUsername());
            session.setAttribute("user", users);
            return "redirect:/login";
        }

        reportService.banded(id);
        return "redirect:/reports";

    }
}
