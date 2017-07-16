package com.mmall.controller.backend;

import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import com.mmall.pojo.User;
import com.mmall.service.IProductService;
import com.mmall.service.IUserService;
import com.mmall.vo.ProductDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpSession;

/**
 * Created by buzheng on 17/7/16.
 */
@RequestMapping(value = "/manage/product ")
@Controller
public class ProductManageController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IProductService iProductService;

    /**
     * 保存商品
     *
     * @param session
     * @param product
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "save.do")
    public ServerResponse saveProduct(HttpSession session, Product product) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登陆");
        }
        if (!iUserService.checkAdminRole(user).isSuccess()) {
            return ServerResponse.createByErrorMessage("请使用管理员登陆");
        }
        //调用商品保存更新服务
        return iProductService.saveOrUpdateProduct(product);
    }

    /**
     * 更新商品状态
     *
     * @param session
     * @param productId
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "set_sale_status.do")
    public ServerResponse setSaleStatus(HttpSession session, Integer productId, Integer status) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登陆");
        }
        if (!iUserService.checkAdminRole(user).isSuccess()) {
            return ServerResponse.createByErrorMessage("请使用管理员登陆");
        }
        if (productId == null || status == null) {
            return ServerResponse.createByErrorMessage("商品参数丢失");
        }
        return iProductService.setSaleStatus(productId, status);
    }

    /**
     * 查询商品详情
     *
     * @param session
     * @param productId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "detail.do")
    public ServerResponse<ProductDetailVO> getDeatail(HttpSession session, Integer productId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登陆");
        }
        if (!iUserService.checkAdminRole(user).isSuccess()) {
            return ServerResponse.createByErrorMessage("请使用管理员登陆");
        }
        return iProductService.manageProductDetail(productId);
    }


    /**
     * 获取所有商品列表
     *
     * @param session
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "list.do")
    public ServerResponse getProductList(HttpSession session, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登陆");
        }
        if (!iUserService.checkAdminRole(user).isSuccess()) {
            return ServerResponse.createByErrorMessage("请使用管理员登陆");
        }
        return iProductService.getList(pageNum, pageSize);
    }

    /**
     * 提供根据商品ID或者商品名称搜索信息的接口
     *
     * @param session
     * @param productId
     * @param productName
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "search.do")
    public ServerResponse searchProduct(HttpSession session,
                                        Integer productId,
                                        String productName,
                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                        @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登陆");
        }
        if (!iUserService.checkAdminRole(user).isSuccess()) {
            return ServerResponse.createByErrorMessage("请使用管理员登陆");
        }
        return iProductService.searchProduct(pageNum, pageSize, productId, productName);
    }


}
