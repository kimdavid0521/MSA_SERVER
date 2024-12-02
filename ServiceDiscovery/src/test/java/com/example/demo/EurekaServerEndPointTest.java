package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

// get , status 메서드 임포트
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EurekaServerEndPointTest {

    @Autowired
    private MockMvc mockMvc;

    // MockMvc를 이용하여 유레카 서버 엔드포인트(대시보드) 테스트
    @Test
    void eurekaServerEndPoint() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }
}
