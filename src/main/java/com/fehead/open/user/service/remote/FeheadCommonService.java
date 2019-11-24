package com.fehead.open.user.service.remote;

import com.fehead.lang.response.CommonReturnType;
import com.fehead.lang.response.FeheadResponse;
import com.fehead.open.user.service.remote.fallback.FeheadCommonServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description:
 * @Author lmwis
 * @Date 2019-11-16 20:11
 * @Version 1.0
 */
@FeignClient(value = "fehead-common-server", fallback = FeheadCommonServiceFallback.class)
public interface FeheadCommonService {

    @PostMapping(value = "/sms/send")
    public FeheadResponse sendSms(@RequestParam("tel") String tel, @RequestParam("action") String action);

    @PutMapping(value = "/sms/validate")
    public CommonReturnType validateSms(@RequestParam("tel") String tel, @RequestParam("code") String code);

    @PutMapping(value = "/email/validate")
    public FeheadResponse validateEmailCode(@RequestParam("address") String address, @RequestParam("code") String code);

    @PostMapping(value = "/email/send")
    public FeheadResponse sendAuthenticationEmail(@RequestParam("address") String address, @RequestParam("action") String action);
}
