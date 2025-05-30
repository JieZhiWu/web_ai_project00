package com.example.controller;

import com.example.pojo.Result;
import com.example.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j //日志注解
@RestController //返回json数据
public class UploadController {

    @Autowired
    private AliyunOSSOperator  aliyunOSSOperator;
/*
    @PostMapping("/upload") //上传文件接口
    public Result upload(String name,Integer age, MultipartFile file) throws IOException {
        log.info("上传文件: name={}, age={}, file={}", name, age, file.getOriginalFilename());
        //文件上传
        String originalFilename = file.getOriginalFilename(); //原始文件名

        String extension = originalFilename.substring(originalFilename.lastIndexOf(".")); //文件后缀名
        String newFileName = UUID.randomUUID().toString() + extension;

        String path =  "E:\\learn_temp\\" + newFileName;

        file.transferTo(new File(path));
        return Result.success();
    }
*/
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("上传文件: file={}", file.getOriginalFilename());
        //将文件上传到oss存储管理
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        log.info("上传文件成功: url={}", url);
        return Result.success(url);

    }
}
