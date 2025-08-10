package org.oaoa.demo.api;

import org.oaoa.demo.common.verifycode.VerifyCodeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.OutputStream;

@RestController
@RequestMapping("/res/verifycode")
public class VerifyCodeController {

    @GetMapping("")
    public void outVerifyCodeImage(String u_verify_key, OutputStream out){
        VerifyCodeUtils.outVerifyCodeImage(u_verify_key,out);
    }
}

