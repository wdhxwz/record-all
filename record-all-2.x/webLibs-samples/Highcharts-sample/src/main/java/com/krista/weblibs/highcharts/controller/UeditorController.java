package com.krista.weblibs.highcharts.controller;

import com.krista.weblibs.highcharts.bean.JsonResult;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * UeditorController
 *
 * @author krista
 * @version V1.0
 * @since 2019/3/4 21:46
 */
@Controller
public class UeditorController {
    private static Logger logger = LoggerFactory.getLogger(UeditorController.class);

    @RequestMapping("/config")
    public String config(String action, HttpServletRequest request, HttpServletResponse response) {
        return "redirect:/js/libs/ueditor/jsp/config.json";
    }



    @PostMapping("/upload")
    @ResponseBody
    public JsonResult<String> upload(MultipartFile file, HttpServletRequest request) {
        String url = "";
        try {
            String imgLocalPath = "";
            String baseDir = request.getServletContext().getRealPath("/");
            imgLocalPath = baseDir + File.separator + "upload";
            File dir = new File(imgLocalPath);
            if (!dir.exists()) {
                dir.mkdir();
            }

            String originalFilename = file.getOriginalFilename();
            String fileType = originalFilename.substring(originalFilename.lastIndexOf('.'), originalFilename.length());
            String random = RandomStringUtils.randomAlphabetic(16);
            // 文件名用客户端上传的
            String fileName = random + fileType;

            FileCopyUtils.copy(file.getBytes(), new File(imgLocalPath + File.separator, fileName));
        } catch (IOException e) {
            logger.error("文件上传失败{}", e);
            return new JsonResult<String>(500, "上传失败");
        }
        return JsonResult.success(url);
    }
}
