package com.mmall.common;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Created by buzheng on 17/6/17.
 */
public class Const {


    public static final String CURRENT_USER = "currentUser";

    public static final String EMAIL = "email";

    public static final String USERNAME = "username";

    public interface Cart{
        int CHECKED= 1;
        int UN_CHECKED = 0;
        String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL";
        String LIMIT_NUM_SUCCESS = "LIMIT_NUM_SUCCESS";
    }
    /**
     * 商品排序
     */
    public interface ProductListOrderBy{
        Set<String> PRICE_ASC_DESC= Sets.newHashSet("price_desc","price_asc");
    }
    public interface Role {
        int ROLE_CUSTOMER = 0;//普通用户
        int ROLE_ADMIN = 1;//管理员
    }

    /**
     * 产品的状态
     */
    public enum ProductStatusEnum {
        ON_SALE(1, "在线");
        private int code;
        private String value;


        ProductStatusEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }




}
