package com.gzc.trigger.http;

import com.gzc.api.ILoginController;
import com.gzc.api.dto.req.LoginFormReqDTO;
import com.gzc.api.response.Response;
import com.gzc.domain.login.model.entity.LoginInfoEntity;
import com.gzc.domain.login.service.ILoginService;
import com.gzc.types.enums.ResponseCode;
import com.gzc.types.exception.AppException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@Slf4j
@CrossOrigin("*")
@RequestMapping("/api/v1/hmdp/login")
@RequiredArgsConstructor
public class LoginController implements ILoginController {

    private final ILoginService loginService;

    @RequestMapping(value = "/send-code", method = RequestMethod.GET)
    public Response<String> sendCode(@RequestParam String phone, HttpSession session){

        String code = null;
        try {
            code = loginService.sendCode(phone);

        } catch (AppException e) {
            return Response.<String>builder()
                    .code(e.getCode())
                    .info(e.getInfo())
                    .build();
        }
        session.setAttribute("code", code);
        return Response.<String>builder()
                .code(ResponseCode.SUCCESS.getCode())
                .info(ResponseCode.SUCCESS.getInfo())
                .data(code)
                .build();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response<Boolean> login(@RequestBody LoginFormReqDTO loginFormReqDTO, HttpSession session){
        LoginInfoEntity loginInfoEntity = LoginInfoEntity.builder()
                .phone(loginFormReqDTO.getPhone())
                .code(loginFormReqDTO.getCode())
                .password(loginFormReqDTO.getPassword())
                .build();
        try {
            loginService.login(loginInfoEntity);
        } catch (AppException e) {
            return Response.<Boolean>builder()
                    .code(e.getCode())
                    .info(e.getInfo())
                    .data(Boolean.FALSE)
                    .build();
        }
        return Response.<Boolean>builder()
                .code(ResponseCode.SUCCESS.getCode())
                .info(ResponseCode.SUCCESS.getInfo())
                .data(Boolean.TRUE)
                .build();
    }

}
