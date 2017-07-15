package com.mmall.controller.backend;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.ICategoryService;
import com.mmall.service.IUserService;
import net.sf.jsqlparser.schema.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by buzheng on 17/7/15.
 */
@RequestMapping("/manage/category")
@Controller
public class CategoryManageController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICategoryService iCategoryService;

    @RequestMapping(value = "add_category.do")
    @ResponseBody
    public ServerResponse addCategory(HttpSession session, String categoryName, @RequestParam(value = "parentId", defaultValue = "0") int parentId) {
        //校验是否是管理员
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登陆");
        }
        if (!iUserService.checkAdminRole(user).isSuccess()) {
            return ServerResponse.createByErrorMessage("不是管理员，请使用管理员登陆");
        }

        return iCategoryService.addCategory(categoryName, parentId);
    }

    /**
     * 更新品类名字
     *
     * @param session
     * @param categoryName
     * @param categoryId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "set_category_name.do")
    public ServerResponse setCategoryName(HttpSession session, String categoryName, Integer categoryId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登陆");
        }
        if (!iUserService.checkAdminRole(user).isSuccess()) {
            return ServerResponse.createByErrorMessage("不是管理员，请使用管理员登陆");
        }
        //更新
        return iCategoryService.updateCategoryName(categoryName, categoryId);
    }

    /**
     * 获取品类下所有子品类的信息
     *
     * @param session
     * @param categoryId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "get_category.do")
    public ServerResponse getChildrenParallelCategory(HttpSession session, @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登陆");
        }
        if (!iUserService.checkAdminRole(user).isSuccess()) {
            return ServerResponse.createByErrorMessage("不是管理员，请使用管理员登陆");
        }
        //查询
        return iCategoryService.getChildrenParallelCategory(categoryId);
    }

    @RequestMapping("get_deep_category.do")
    @ResponseBody
    public ServerResponse getCategoryAndDeepChildrenCategory(HttpSession session,@RequestParam(value = "categoryId" ,defaultValue = "0") Integer categoryId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //查询当前节点的id和递归子节点的id
            return iCategoryService.selectCategoryAndChildrenById(categoryId);

        }else{
            return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
        }
    }

}
