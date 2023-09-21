package com.example.testProject.util;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperUtil {
    @Bean
    public ModelMapper getMapper() {
        return new ModelMapper();
    }


}
