package com.example.demo;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class EurekaServerStatusTest {


    @Mock
    private EurekaClient eurekaClient;

    @InjectMocks
    private EurekaServerStatusTest eurekaServerStatusTest;


    // 유레카 서버 서비스 상태 체크(엔드포인트가 정상적으로 UP 상태를 반환하는지)
    @Test
    void eurekaServerStatus_Down() throws Exception {
        MockitoAnnotations.openMocks(this);

        Applications mockApplications = new Applications();
        when(eurekaClient.getApplications()).thenReturn(mockApplications);

        String status = eurekaClient.getApplications().getRegisteredApplications().isEmpty() ? "Down" : "Up";

        assertEquals("Down", status); // 현재 모킹된 동작이 비어있음으로 인해 DOWN이 되어야함
    }


    // 추후에 유레카 클라이언트 상태에서 애플리케이션이 등록된 후에 다시 테스트 해봐야될거같음
    @Test
    void eurekaServerStatus_Up() throws Exception {
        //mock 초기화
        MockitoAnnotations.openMocks(this);

        InstanceInfo mockInstanceInfo = InstanceInfo.Builder.newBuilder()
                .setAppName("TEST_APP")
                .setVIPAddress("testapp.com")
                .setStatus(InstanceInfo.InstanceStatus.UP)
                .build();

        //Application 객체 생성해서 instanceInfo에 추가
        Application mockApplication = new Application("TEST_APP");
        mockApplication.addInstance(mockInstanceInfo);

        // mock Application에 애플리케이션 추가하기
        Applications mockApplications = new Applications();
        mockApplications.addApplication(mockApplication);

        // eurekaClient 모킹하기
        when(eurekaClient.getApplications()).thenReturn(mockApplications);

        // 상태 확인하기
        String status = eurekaClient.getApplications().getRegisteredApplications().isEmpty() ? "DOWN" : "UP";
        assertEquals("UP", status);

    }
}
