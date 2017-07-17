package com.mmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by buzheng on 17/7/16.
 */
public interface IFileService {
    String upload(MultipartFile file, String path);
}
