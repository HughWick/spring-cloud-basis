package com.hugh.integration.config;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfiguration {
//    @Resource
//    private MinioProp minioProp;
//    @Value("${minio.endpoint}")
//    private String endpoint;
//    @Value("${minio.accesskey}")
//    private String accesskey;
//    @Value("${minio.secretKey}")
//    private String secretKey;
    @Bean
    public MinioClient minioClient() throws InvalidPortException, InvalidEndpointException {
//        System.out.println("--->>" + JsonObjectUtils.toJson(minioProp));
//        return new MinioClient(minioProp.getEndpoint(), minioProp.getAccesskey(), minioProp.getSecretKey());
        return new MinioClient("http://146.56.113.135:9000", "minioadmin", "minioadmin");
//        return MinioClient.builder()
//                .endpoint(minioProp.getEndpoint())
//                .credentials(minioProp.getAccesskey(), minioProp.getSecretKey())
//                .build();
    }
}
