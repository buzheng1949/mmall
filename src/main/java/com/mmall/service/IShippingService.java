package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Shipping;

/**
 * Created by buzheng on 17/7/22.
 */
public interface IShippingService {
    /**
     * 新增收获地址
     *
     * @param userId
     * @param shpping
     * @return
     */
    ServerResponse add(Integer userId, Shipping shpping);

    /**
     * 删除用户的某个收货地址
     *
     * @param userId
     * @param shippingId
     * @return
     */
    ServerResponse<String> del(Integer userId, Integer shippingId);

    /**
     * 更新用户收货地址
     *
     * @param userId
     * @param shipping
     * @return
     */
    ServerResponse update(Integer userId, Shipping shipping);

    /**
     * 查询用户的单个地址
     * @param userId
     * @param shippingId
     * @return
     */
    ServerResponse<Shipping> select(Integer userId, Integer shippingId);

    /**
     * 查询用户的所有地址
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize);
}
