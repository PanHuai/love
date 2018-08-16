package com.love.lylph.controller;

import com.love.lylph.common.UploadUtils;
import com.love.lylph.response.ResponseInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @author PanHuai
 * @data 2018/8/7 10:00
 */
@RestController
public class FileController {

    /**
     * 上传 下载 路径
     */
    @Value("${upload.file.path}")
    private String uploadFilePath;

    @Value("${upload.file.path}")
    private String downloadFilePath;

    /**
     * 单文件上传
     * 表单name = 'file'
     * @return
     */
    @PreAuthorize("hasAnyRole('user','admin','pass')")
    @RequestMapping("/file/upload")
    public ResponseInfo upload(HttpServletRequest request, @RequestParam("file")MultipartFile file) {

        ResponseInfo response = new ResponseInfo();
        if ( !file.isEmpty()) {

            String filePath = file.getOriginalFilename();

            try {
                UploadUtils.get().upload(uploadFilePath,filePath, file);
            } catch (IOException e) {
                response.setCode(500);
                response.setMsg("文件上传出错："+e);
                return response;
            }
        }
        response.setCode(200);
        response.setMsg("文件上传成功");
        return response;
    }

    /**
     * 多文件上传
     */
    @RequestMapping("/file/uploads")
    public ResponseInfo uploads(HttpServletRequest request) {

        ResponseInfo response = new ResponseInfo();
        try {
            List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
            BufferedOutputStream bs = null;
            OutputStream os = null;
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    String filePath = file.getOriginalFilename();
                    UploadUtils.get().upload(uploadFilePath, filePath, file);
                }
            }
            response.setCode(200);
            response.setMsg("文件上传成功");
        } catch (Exception e) {
            response.setCode(500);
            response.setMsg("文件上传发生错误:"+e);
        }finally {
            return response;
        }
    }

    /**
     * 文件下载
     */
    @RequestMapping("/file/download")
    public ResponseInfo downLoad(@RequestBody() Map<String,Object> params, HttpServletResponse response) {

        ResponseInfo responseInfo = new ResponseInfo();
        String fileName = (String) params.get("fileName");
        try {
            UploadUtils.get().download(downloadFilePath,fileName,response);
        } catch (IOException e) {
            responseInfo.setCode(500);
            responseInfo.setMsg("文件下载发生错误:"+e);
        }
        responseInfo.setCode(200);
        responseInfo.setMsg("文件下载成功");
        return responseInfo;
    }


}
