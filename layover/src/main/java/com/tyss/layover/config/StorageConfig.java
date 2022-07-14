package com.tyss.layover.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class StorageConfig {

	@Value("${amazonProperties.accessKey:AKIA2XCFFJGUA2DNSZ7V}")
	private String accessKey;

	@Value("${amazonProperties.secretKey:BLW81YEawPsqnIUyKgUUjl9x31vBfQtMFLh/dyGI}")
	private String accessSecret;

	@Value("${amazonProperties.region:ap-south-1}")
	private String region;

	@Bean
	public AmazonS3 s3client() {
		AWSCredentials credentials = new BasicAWSCredentials(accessKey,accessSecret);
		return AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(region).build();
	}
}
