package com.realdd.medcost.common.value;

/**
 * Created by duanduan on 2020/11/27 16:27
 */
public class AccountStatus {
    //新建未保存草稿
    public static final Integer STATUS_NEW=0;

    //草稿（未提交）
    public static final Integer STATUS_DRAFT=1;

    //审核中（已提交未审核）
    public static final Integer STATUS_REVIEW=2;

    //审核通过
    public static final Integer STATUS_PASSED=3;

    //审核不通过
    public static final Integer STATUS_UNPASSED=4;

    //已收单
    public static final Integer STATUS_DELIVER=5;

    //已拒绝
    public static final Integer STATUS_REJECT=6;

}
