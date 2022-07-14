package com.tyss.layover.util;

import static com.tyss.layover.constant.LayoverConstant.ERROR_WHILE_CONVERTING_MULTIPART_FILE_TO_FILE;
import static com.tyss.layover.constant.LayoverConstant.FAILED_TO_UPLOAD;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.tyss.layover.exception.FailedToConvertMultipartToFile;
import com.tyss.layover.exception.FailedToUploadException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SSSUploadFile {
	
	/**
	 * This is the end point url for amazon s3 bucket
	 */
	@Value("${amazonProperties.endpointUrl}")
	private String endpointUrl;
	/**
	 * This is the bucketName for amazon s3 bucket
	 */
	@Value("${amazonProperties.bucketName}")
	public String bucketName;

	/**
	 * This enables automatic dependency injection of AmazonS3 interface. This
	 * object is used by methods in the SimpleProductServiceImplementation to call
	 * the respective methods.
	 */
	@Autowired
	private AmazonS3 s3client;

	public String uploadFile(MultipartFile multipartFile) {
		String fileUrl = "";
		try {
			File file = convertMultiPartFiletoFile(multipartFile);
			String fileName = generateFileName(multipartFile);
			fileUrl = uploadFileTos3bucketConfig(file,fileName);
		} catch (Exception e) {
			throw new FailedToUploadException(FAILED_TO_UPLOAD);
		}
		return fileUrl;

	}
	
	private String generateFileName(MultipartFile multiPart) {

		String fileName = multiPart.getOriginalFilename();
		if (fileName != null) {
			return UUID.randomUUID() + "-" + fileName.replace(" ", "_");
		} else {
			throw new FailedToUploadException("File name is empty.");
		}

	}

	private File convertMultiPartFiletoFile(MultipartFile file) {
		File convertedFile = new File(file.getOriginalFilename());
		try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
			fos.write(file.getBytes());
		} catch (IOException e) {
			log.error(ERROR_WHILE_CONVERTING_MULTIPART_FILE_TO_FILE, e);
			throw new FailedToConvertMultipartToFile(ERROR_WHILE_CONVERTING_MULTIPART_FILE_TO_FILE);
		}
		return convertedFile;
	}



	public String uploadFileTos3bucketConfig(File file,String fileName) {
		String filePath = "img/" + fileName;
		s3client.putObject(
				new PutObjectRequest(bucketName, filePath, file).withCannedAcl(CannedAccessControlList.PublicRead));
		return s3client.getUrl(bucketName, filePath).toString();

	}

	public void deleteS3Folder(String folderPath) {
		for (S3ObjectSummary file : s3client.listObjects(bucketName, folderPath).getObjectSummaries()) {
			s3client.deleteObject(bucketName, file.getKey());
		}
	}
}
