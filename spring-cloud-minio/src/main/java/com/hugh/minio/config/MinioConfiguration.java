package com.hugh.minio.config;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * minio 注入配置
 */
@Configuration
public class MinioConfiguration {

    @Value("${minio.endpoint}")
    private String endpoint;
    @Value("${minio.accesskey}")
    private String accesskey;
    @Value("${minio.secretKey}")
    private String secretKey;

    @Bean
    public MinioClient minioClient() throws InvalidPortException, InvalidEndpointException {
//        return new MinioClient(minioProp.getEndpoint(), minioProp.getAccesskey(), minioProp.getSecretKey());
        return new MinioClient(endpoint, accesskey, secretKey);
//        return MinioClient.builder()
//                .endpoint(minioProp.getEndpoint())
//                .credentials(minioProp.getAccesskey(), minioProp.getSecretKey())
//                .build();
    }
}
