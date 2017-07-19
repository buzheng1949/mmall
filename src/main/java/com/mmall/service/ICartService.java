package com.mmall.service;

import com.mmall.common.ServerResponse;

/**
 * Created by buzheng on 17/7/19.
 */
public interface ICartService {
    /**
     * 购物车增加商品服务
     * @param userId
     * @param productId
     * @param count
     * @return
     */
    ServerResponse add(Integer userId, Integer productId, Integer count);
}
