package com.mmall.controller.portal;

import com.github.pagehelper.PageInfo;
import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Shipping;
import com.mmall.pojo.User;
import com.mmall.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by buzheng on 17/7/22.
 */
@Controller
@RequestMapping(value = "/shipping/")
public class ShippingController {

    @Autowired
    private IShippingService iShippingService;

    /**
     * 对象数据绑定，spring mvc自动完成
     * 新增收货地址接口
     *
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "add.do")
    public ServerResponse add(HttpSession session, Shipping shipping) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登陆");
        }

        return iShippingService.add(user.getId(), shipping);

    }

    /**
     * 删除用户的某个收获地址
     *
     * @param session
     * @param shippingId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "del.do")
    public ServerResponse del(HttpSession session, Integer shippingId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登陆");
        }

        return iShippingService.del(user.getId(), shippingId);

    }

    /**
     * 更新用户收获地址
     *
     * @param session
     * @param shipping
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "update.do")
    public ServerResponse update(HttpSession session, Shipping shipping) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登陆");
        }
        return iShippingService.update(user.getId(), shipping);

    }

    /**
     * 更新用户收获地址
     *
     * @param session
     * @param shippingId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "select.do")
    public ServerResponse select(HttpSession session, Integer shippingId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登陆");
        }
        return iShippingService.select(user.getId(), shippingId);

    }

    /**
     * 列举所有用户地址
     *
     * @param session
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "list.do")
    public ServerResponse<PageInfo> list(HttpSession session,
                                         @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登陆");
        }
        return iShippingService.list(user.getId(), pageNum, pageSize);

    }

}
