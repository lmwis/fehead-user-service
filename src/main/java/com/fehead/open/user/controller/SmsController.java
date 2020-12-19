package com.fehead.open.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fehead.lang.controller.BaseController;
import com.fehead.lang.error.BusinessException;
import com.fehead.lang.error.EmBusinessError;
import com.fehead.lang.response.CommonReturnType;
import com.fehead.lang.response.FeheadResponse;
import com.fehead.lang.util.CheckEmailAndTelphoneUtil;
import com.fehead.open.user.service.remote.FeheadCommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 调用远程fehead-common-service
 * @Author: lmwis
 * @Date 2020-12-19 20:46
 * @Version 1.0
 */
@RestController
@RequestMapping("/sms")
@RequiredArgsConstructor
public class SmsController extends BaseController {
    final FeheadCommonService feheadCommonService;

    /**
     * 提供手机号和当前行为，根据行为发送相应类型短信
     * @param tel tel
     * @param action action
     * @return  return
     * @throws BusinessException 业务异常
     */
    @PostMapping(value = "/send")
    public FeheadResponse sendSms(@RequestParam("tel")String tel, @RequestParam("action")String action) throws BusinessException {
        // 检查手机号是否合法
        if (!CheckEmailAndTelphoneUtil.checkTelphone(tel)) {
            logger.info("手机号不合法");
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "手机号不合法");
        }
        return feheadCommonService.sendSms(tel,action);
    }

    /**
     * 对手机号和验证码进行校验
     * @param tel tel
     * @param code code
     * @return return
     */
    @PutMapping(value = "/validate")
    public FeheadResponse validateSms(@RequestParam("tel")String tel, @RequestParam("code")String code){
        return feheadCommonService.validateSms(tel,code);
    }
}
