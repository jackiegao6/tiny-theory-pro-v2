package com.gzc.test;

import com.gzc.domain.login.service.ILoginService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    @Resource
    private ILoginService loginService;

    @Test
    public void test() {
        log.info("测试完成");
    }

    @Test
    public void test_sendCode_success(){
        String phoneNumber = "13406877265";
        loginService.sendCode(phoneNumber);

    }

}
