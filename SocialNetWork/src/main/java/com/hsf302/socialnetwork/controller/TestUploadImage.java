package com.hsf302.socialnetwork.controller;

import com.hsf302.socialnetwork.util.CloudiaryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController

public class TestUploadImage {
    @Autowired
    CloudiaryUtil cloudinaryUtil;
    @PostMapping("/up")
    public ResponseEntity<String> uploadImage(MultipartFile file) throws IOException {
       try
       {

           return ResponseEntity.ok(cloudinaryUtil.uploadImage(file));
       }catch (Exception e)
       {e.printStackTrace();}
       return ResponseEntity.ok("Image uploaded failed");
    }
}
