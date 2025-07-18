package com.hsf302.socialnetwork.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudiaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        Map<String, Object> config = new HashMap<String, Object>();
        config.put("cloud_name", "dnmyerhfc");
        config.put("api_key", "223895654873727");
        config.put("api_secret", "b2I9EF3g6xLkSq5Lm3LNm-UYG6Y");
        return new Cloudinary(config);
    }
}
