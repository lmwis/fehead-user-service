package com.fehead.open.user.response;

import com.fehead.lang.response.ErrorMsgType;
import lombok.Data;

/**
 * @Description:
 * @Author lmwis
 * @Date 2019-11-16 21:11
 * @Version 1.0
 */
@Data
public class RPCommonErrorType {

    private ErrorMsgType data;

    private String status;

}
