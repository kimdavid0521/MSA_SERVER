package com.example.demo;

import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Applications;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when; // Mockito의 when 메서드 임포트

@SpringBootTest
@AutoConfigureMockMvc
public class EurekaServerEndPointTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EurekaClient eurekaClient; //유레카 클라이언트를 모킹

    // MockMvc를 이용하여 유레카 서버 엔드포인트(대시보드) 테스트
    @Test
    void eurekaServerEndPoint() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    // 유레카 서버 상태 확인(health 체크)
//    @Test
//    void testHealthCheck() throws Exception {
//        mockMvc.perform(get("http://localhost:8761/acuator/health"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.status").value("UP"));
//    }

}
