package com.mmall.controller.portal;

import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by buzheng on 17/7/18.
 */
@RequestMapping(value = "/cart/")
@Controller
public class CartController {

    @Autowired
    private ICartService iCartService;

    /**
     * 购物车新增商品的接口
     *
     * @param session
     * @param productId
     * @param count
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "add.do")
    public ServerResponse add(HttpSession session, Integer productId, Integer count) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登陆");
        }
        return iCartService.add(user.getId(), productId, count);
    }
}
