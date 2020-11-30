package com.realdd.medcost.controller;

import com.realdd.medcost.common.api.CommonResult;
import com.realdd.medcost.dto.OssCallbackResult;
import com.realdd.medcost.dto.OssPolicyResult;
import com.realdd.medcost.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Oss对象存储管理Controller
 * Created by duanduan on 2020/11/19 15:02
 */
@Controller
@Api(tags = "OssController", description = "Oss管理")
@RequestMapping("/aliyun/oss")
public class OssController {
    @Autowired
    private OssService ossService;

    @ApiOperation(value = "oss上传签名生成")
    @RequestMapping(value = "/policy", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<OssPolicyResult> policy() {
        OssPolicyResult result = ossService.policy();
        return CommonResult.success(result);
    }

    @ApiOperation(value = "oss上传成功回调")
    @RequestMapping(value = "/callback", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<OssCallbackResult> callback(HttpServletRequest request) {
        System.out.println("回调");
        OssCallbackResult ossCallbackResult = ossService.callback(request);
        return CommonResult.success(ossCallbackResult);
    }

    @ApiOperation(value = "oss上传签名生成")
    @RequestMapping(value = "/policy_excel", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<OssPolicyResult> policyExcel() {
        OssPolicyResult result = ossService.policyExcel();
        return CommonResult.success(result);
    }

    @ApiOperation(value = "oss上传成功回调")
    @RequestMapping(value = "/callback_excel", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult callbackExcel(HttpServletRequest request) {
//        if(request==null || request.getParameter("filename")==null) return CommonResult.failed("导入失败");
//
//        Boolean success= ossService.callbackExcel(request.getParameter("filename"));
//        if(success){
//            return CommonResult.success(null);
//        }else{
//            return CommonResult.failed("导出失败");
//        }
        OssCallbackResult ossCallbackResult = ossService.callback(request);
        return CommonResult.success(ossCallbackResult);
    }

}
