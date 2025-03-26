package com.amstech.dairy.management.system.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileService {

    private static final String BASE_REPO_DIRECTORY_PATH = "./storage";
    private static final String FILE_PATH_FORMAT = "{0}/{1}/{2}.{3}";

    @Getter
    @Value("${app.max-file-size}")
    private long fileMaxSize;

    @PostConstruct
    public void init() {
        log.info("Initializing FileService...");
        File file = new File(BASE_REPO_DIRECTORY_PATH);
        if (!file.exists()) {
            if (file.mkdirs()) {
                log.info("Base repository directory created successfully.");
            } else {
                log.error("Failed to create base repository directory.");
            }
        }
    }

    public String saveFile(byte[] data, String directoryName, String fileExt) throws IOException {
        String filePath = MessageFormat.format(FILE_PATH_FORMAT, BASE_REPO_DIRECTORY_PATH, directoryName, UUID.randomUUID(), fileExt);

        // Ensure directory exists
        File dir = new File(BASE_REPO_DIRECTORY_PATH + "/" + directoryName);
        if (!dir.exists() && !dir.mkdirs()) {
            throw new IOException("Could not create directory: " + dir.getAbsolutePath());
        }

        // Save file
        Files.write(Paths.get(filePath), data);
        log.info("File saved at path: {}", filePath);

        return filePath;
    }
}

//package com.amstech.dairy.management.system.service;
//
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.text.MessageFormat;
//import java.util.UUID;
//
//import org.apache.tomcat.util.http.fileupload.FileUtils;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.util.FileCopyUtils;
//import org.springframework.util.FileSystemUtils;
//
//import com.fasterxml.jackson.annotation.JsonAnyGetter;
//
//import ch.qos.logback.core.util.FileUtil;
//import jakarta.annotation.PostConstruct;
//import lombok.Getter;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Service
//public class FileService {
//
//	  private final String BASE_REPO_DIRECTORY_PATH = "./storage" ;
//	  
//	  private final String FILE_PATH_FORMAT = "{0}/{1}/{2}.{3}" ;
//	  
//	  @Getter
//	  @Value("${app.max-file-size}")
//	  private long fileMaxSize ;
//	  
//	  @PostConstruct
//	  public void init() {
//		log.info("Init file Service");  
//		
//		File file = new File(BASE_REPO_DIRECTORY_PATH);
//		if(!file.exists()) {
//			try {
//				log.info("base repo does note exist , criting base repo directory ");
//				if(file.mkdir())
//					log.info("base repo created successfuly");
//				else
//					log.info("Unable to created a base repo");
//				
//			}catch(Exception e) {
//				e.printStackTrace();
//				log.info("faild to created base repo due to [{}]" ,e.getMessage(),e);
//			}
//		}else {
//			log.info("base repo already exist");
//		}
//		log.info(" Max file size {}", getFileMaxSize());
//		
//	  }
//	  public String saveFile(byte [] data ,String directoryName , String fileExt)throws IOException{
//		   String filePath = MessageFormat.format(FILE_PATH_FORMAT,  BASE_REPO_DIRECTORY_PATH , directoryName , UUID.randomUUID().toString(),fileExt);
//		   Files.write(Paths.get(filePath), data);
//		   log.info(" SAVE FILE ON THE PATH" ,filePath);
//		   return filePath;
//	  }
//}
