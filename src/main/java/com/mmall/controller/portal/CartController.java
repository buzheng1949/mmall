package com.mmall.controller.portal;

import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.ICartService;
import com.mmall.vo.CartVo;
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
    public ServerResponse<CartVo> add(HttpSession session, Integer productId, Integer count) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登陆");
        }
        return iCartService.add(user.getId(), productId, count);
    }

    /**
     * 更新购物车
     *
     * @param session
     * @param productId
     * @param count
     * @return
     */
    @RequestMapping(value = "update.do")
    @ResponseBody
    public ServerResponse<CartVo> update(HttpSession session, Integer productId, Integer count) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登陆");
        }
        return iCartService.update(user.getId(), productId, count);
    }

    /**
     * 删除购物车的商品 跟前端的约定是根据逗号进行分割
     *
     * @param session
     * @param productId
     * @return
     */
    @RequestMapping(value = "delete_product.do")
    @ResponseBody
    public ServerResponse<CartVo> deleteProduct(HttpSession session, String productId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登陆");
        }
        return iCartService.deleteProduct(user.getId(), productId);

    }

    /**
     * 获取购物车列表
     *
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("list.do")
    public ServerResponse list(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (null == user) {
            return ServerResponse.createByErrorMessage("用户未登陆");
        }

        return iCartService.list(user.getId());
    }
}
