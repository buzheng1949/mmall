package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.mmall.service.IFileService;
import com.mmall.util.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by buzheng on 17/7/16.
 */
@Service(value = "iFileService")
public class FileServiceImpl implements IFileService {

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    /**
     * 上传文件服务
     *
     * @param file
     * @param path
     * @return
     */
    public String upload(MultipartFile file, String path) {
        String fileName = file.getOriginalFilename();
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        String fileUploadName = UUID.randomUUID() + "." + fileExtensionName;
        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path, fileUploadName);
        try {
            //文件暂时存储在tomact
            file.transferTo(targetFile);
            //文件从tomact上传到服务器
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            //tomact文件删除
            targetFile.delete();
        } catch (IOException e) {
            logger.error("上传发生异常", e);
            return null;
        }
        return targetFile.getName();
    }
}
