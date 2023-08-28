package com.forum.clothing.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyuncs.exceptions.ClientException;
import com.forum.clothing.config.SystemConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class OSSUtil {
	private static OSS $client;

	@SuppressWarnings("static-access")
	@PostConstruct
	public void init() throws ClientException {
		// 创建OSSClient实例。
		EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
		this.$client= new OSSClientBuilder().build(SystemConfig.endpoint, credentialsProvider);
	}

	public static void uploadImgByBytes(byte[] bytes, String targarPath) {
		$client.putObject(SystemConfig.bucketName, targarPath, new ByteArrayInputStream(bytes));
	}

	public static void uploadByStream(InputStream inputStream, String targetPath) throws Exception {
		$client.putObject(SystemConfig.bucketName, targetPath, inputStream);
		inputStream.close();
	}

	public static void downloadFile(String filePath, String targetPath) throws Exception {
		$client.getObject(new GetObjectRequest(SystemConfig.bucketName, filePath), new File(targetPath));
	}

	public static void uploadNetFile(String url, String targetPath) throws Exception {
		InputStream inputStream = new URL(url).openStream();
		$client.putObject(SystemConfig.bucketName, targetPath, inputStream);
		inputStream.close();
	}

	public static void uploadLocalFile(String filePath, String targetPath) throws Exception {
		InputStream inputStream = Files.newInputStream(Paths.get(filePath));
		$client.putObject(SystemConfig.bucketName, targetPath, inputStream);
		inputStream.close();
	}
}

