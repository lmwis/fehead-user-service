package com.fehead.open.user.service.remote.fallback;

import com.fehead.lang.error.EmBusinessError;
import com.fehead.lang.response.CommonReturnType;
import com.fehead.lang.response.ErrorMsgType;
import com.fehead.lang.response.FeheadResponse;
import com.fehead.lang.response.RPCommonErrorType;
import com.fehead.open.user.service.remote.FeheadCommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Description: 熔断方案
 * @Author lmwis
 * @Date 2019-11-19 13:36
 * @Version 1.0
 */
@Component
public class FeheadCommonServiceFallback implements FeheadCommonService {
    private static final Logger logger = LoggerFactory.getLogger(FeheadCommonServiceFallback.class);
    @Override
    public FeheadResponse sendSms(String tel, String action) {
        logger.error("短信服务调用异常："+tel);
        return new RPCommonErrorType(
                new ErrorMsgType(EmBusinessError.UNKNOWN_ERROR.getErrorCode(),EmBusinessError.UNKNOWN_ERROR.getErrorMsg())
                ,"fail");
    }

    @Override
    public CommonReturnType validateSms(String tel, String code) {
        logger.error("短信校验服务调用异常："+tel);
        return CommonReturnType.create("?");
    }

    @Override
    public FeheadResponse validateEmailCode(String address, String code) {
        return null;
    }

    @Override
    public FeheadResponse sendAuthenticationEmail(String address, String action) {
        return null;
    }
}
