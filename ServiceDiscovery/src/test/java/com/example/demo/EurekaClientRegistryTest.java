package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EurekaClientRegistryTest {

    @LocalServerPort
    private int port;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void eurekaServerClientRegistry() {

        //유레카 서버 상태 확인 엔드포인트
        String eurekaStatusUrl = "http://localhost" + port + "/eureka";

        //유레카 서버가 클라이언트 등록 상태를 반환하는지 확인
        String response = restTemplate.getForObject(eurekaStatusUrl, String.class);


    }
}
