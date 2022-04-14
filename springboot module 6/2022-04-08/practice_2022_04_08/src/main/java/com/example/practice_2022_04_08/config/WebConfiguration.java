package com.example.practice_2022_04_08.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

@Configuration // đánh dấu đây là lớp cấu hình
public class WebConfiguration implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean<AdminFilter> myCartFilterFilterRegistrationBean() {
        FilterRegistrationBean<AdminFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AdminFilter());
        registrationBean.addUrlPatterns("/admin/*");
        return registrationBean;
    }

    @Bean
    FilterRegistrationBean<RegistrationFilter> adminFilterFilterRegistrationBean() {
        FilterRegistrationBean<RegistrationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RegistrationFilter());
        registrationBean.addUrlPatterns("/subjects/*");
        return registrationBean;
    }

    public static void main(String[] args) {
//        String[] csvHeader = {"SUBJECT ID", "SUBJECT NAME", "POINT TYPE", "POINT", "POINT DATE"};
//        String[] nameMapping = {"subjectId", "subjectName", "pointType", "point", "pointDate"};
//
//        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
    }

}
