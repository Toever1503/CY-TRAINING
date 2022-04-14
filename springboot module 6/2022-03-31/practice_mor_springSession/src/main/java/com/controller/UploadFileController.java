package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
public class UploadFileController {

    @PostMapping("upload")
    @ResponseBody
    public Object upload(MultipartFile file, HttpServletRequest req, HttpServletResponse res) throws IOException {
//        System.out.println(file.getOriginalFilename());
        String filePath = req.getServletContext().getRealPath("/") + file.getOriginalFilename();

        file.transferTo(new File(filePath));
//        res.setContentType(file.getContentType());
        System.out.println(filePath);
        return "ok";
    }
}
