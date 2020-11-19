package com.realdd.medcost.service;

import com.realdd.medcost.dto.OssCallbackResult;
import com.realdd.medcost.dto.OssPolicyResult;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by duanduan on 2020/11/19 14:24
 */
public interface OssService {
    /**
     * oss上传策略生成
     */
    OssPolicyResult policy();
    /**
     * oss上传成功回调
     */
    OssCallbackResult callback(HttpServletRequest request);
}
