package com.love.lylph.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author PanHuai
 * @data 2018/8/7 14:32
 */
public class UploadUtils {

    private static final Logger logger = LoggerFactory.getLogger(UploadUtils.class);

    private static final UploadUtils upLoad = new UploadUtils();

    @Value("${upload.file.path}")
    private String uploadFilePath;

    public static UploadUtils get() {
        return upLoad;
    }

    /**
     * 文件上传
     */
    public void upload(String fileName, MultipartFile file) throws IOException {
        String path = String.format("%s/%s", uploadFilePath, DateUtils.getCurrentTime("yyyyMMddHHmmss") + fileName);
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        file.transferTo(dir);
    }

    /**
     * 文件下载
     */
    public void download(String fileName, HttpServletResponse response) throws IOException {

        if (StringUtils.isNotBlank(fileName)) {
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            InputStream is = null;
            BufferedInputStream bs = null;
            OutputStream os = null;
            byte[] buffer = new byte[1024];
            File file = new File(String.format("%s/%s", uploadFilePath, fileName));
            try {
                is = new FileInputStream(file);
                bs = new BufferedInputStream(is);
                os = response.getOutputStream();
                int i = bs.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, buffer.length);
                    os.flush();
                    i = bs.read(buffer);
                }
            } finally {
                if (os != null) {
                    os.close();
                }
                if (bs != null) {
                    bs.close();
                }
                if (is != null) {
                    is.close();
                }
            }
        }
    }


}
