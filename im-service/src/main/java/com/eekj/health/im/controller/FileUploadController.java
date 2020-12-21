package com.eekj.health.im.controller;


import com.eekj.health.im.common.COSUtils;
import com.eekj.health.im.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description : 腾讯云对象存储控制器（文件的上传和删除）
 * @Author : linzi
 * @Date: 2020-06-27 10:37
 */
@Api(value = "FileUploadController",tags = "腾讯云对象存储控制器（文件的上传和删除）")
@RestController
@RequestMapping(value = "/fileUpload")
public class FileUploadController {

    //上传到腾讯云对象存储的路径(也就是存储同下的文件夹)
    private String chatPath = "images/chat/";

    /**
     * 上传文件到 腾讯云对象存储
     * @param multipartFile 上传的文件
     * @param type （1:聊天）
     * @return
     */
    @ApiOperation(value = "文件上传",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "multipartFile",value = "文件",required = true,paramType = "form",dataType = "__File"),
            @ApiImplicitParam(name = "type",value = "类型(1：聊天路径)",required = true,paramType = "query",dataType = "int")

    })
    @PostMapping(value = "/upload",headers = "content-type=multipart/form-data")
    public Result upload(@RequestParam("multipartFile") MultipartFile multipartFile,
                         @RequestParam("type") Integer type){
        try {
            //获取文件名称
            String fileName = multipartFile.getOriginalFilename();
            //判断有无后缀
            if (fileName.lastIndexOf(".") < 0){
                return Result.error("文件格式不正确");
            }
            //获取文件后缀
            String prefix = fileName.substring(fileName.lastIndexOf("."));
            //使用uuid作为文件名，防止生成的临时文件重复
            final File file = File.createTempFile("imagesFile-" + System.currentTimeMillis(), prefix);
            //将Multifile转换成File
            multipartFile.transferTo(file);
            String uploadFileName = "";
            Map<String,Object> map = new HashMap<String,Object>();
            if (type == 1) {
                uploadFileName = COSUtils.uploadFile(file, chatPath);
                map.put("fileName", uploadFileName);
                //程序结束时删除临时文件
                deleteFile(file);
                return Result.ok(map);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 删除文件
     * @param fileName 文件访问路径名称
     * @param type
     * @return
     */
    @ApiOperation(value = "文件删除",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileName",value = "文件名称（也就是存入数据库的访问路径）",required = true,dataType = "String"),
            @ApiImplicitParam(name = "type",value = "类型(1-聊天路径)",required = true,dataType="int")
    })
    @GetMapping(value = "/deleteFile")
    public Result deleteFile(@RequestParam("fileName") String fileName,
                             @RequestParam("type") Integer type){
        try {
            //获取文件名称
            String fileNewName = fileName.substring(fileName.lastIndexOf("/")+1);
            String key = "";
            if (type == 1) {
                key = chatPath + fileNewName;
                COSUtils.deleteFile(key);
                return Result.ok("删除成功");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 删除临时文件
     * @param files 临时文件，可变参数
     */
    private void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }

}
