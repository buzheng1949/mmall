package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.vo.CartVo;

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

    /**
     * 更新购物车
     * @param userId
     * @param productId
     * @param count
     * @return
     */
    ServerResponse<CartVo> update(Integer userId, Integer productId, Integer count);

    /**
     * 删除购物车商品 批量支持多个商品删除
     * @param userId
     * @param productIds
     * @return
     */
    ServerResponse<CartVo> deleteProduct(Integer userId, String productIds);
}
