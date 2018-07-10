package com.systemManage.web.controller.system;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.systemManage.common.utils.CollectionUtils;
import com.systemManage.common.utils.JsonUtils;
import com.systemManage.common.utils.SecurityUtil;
import com.systemManage.common.utils.StringUtil;
import com.systemManage.common.utils.WebUtils;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.dto.CommonAccountDto;
import com.systemManage.service.CommonAccountService;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SystemController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CommonAccountService accountService;

    /**
     * 方法名: toLogin
     * 描述: 跳转至登录页面
     * 参数: @param request
     * 参数: @param response
     * 参数: @return
     * 创建人: Zhang JinQiu
     * 创建时间: 2017年7月20日 下午10:16:07
     * 版本号: v1.0
     * 返回类型: String
     */
    @RequestMapping("/toLogin")
    public String toLogin(HttpServletRequest request, HttpServletResponse response) {
        // TODO - 重定向到登录窗口页面, 调用其他网站链接,session失效或退出时走这个方法
        return "system/login";
    }

    /**
     * 登录操作
     *
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/login")
    public /*JSONObject*/void login(HttpServletRequest request, HttpServletResponse response) {
        logger.info("--------------- 调用登录接口, 进行账号登录操作  ----------------");
        JSONObject result = new JSONObject();
        String loginName = request.getParameter("loginName");
        String password = request.getParameter("password");
        if (StringUtil.isNotBlank(loginName) && StringUtil.isNotBlank(password)) {
            logger.info("--------------- 根据用户输入用户名密码进行数据确认  ----------------");
            // 根据用户名密码进行数据确认
            Criteria criteria = new Criteria();
            criteria.put("accountName", loginName);
            criteria.put("accountPassWord", password);
            List<CommonAccountDto> accountInfoList = accountService.selectByAccount(criteria);
            if (CollectionUtils.isNotEmpty(accountInfoList)) {
                logger.info("--------------- 登录成功, 向Session中存储用户信息  ----------------");
                // 向Session中存储用户信息
                HttpSession session = request.getSession();
                session.setAttribute("accountInfo", accountInfoList.get(0));

                // 加密后的accountId
                String token = SecurityUtil.encryptAES(accountInfoList.get(0).getAccountId() + "|" + StringUtil.getUUID());

                // 向前台响应数据
                result.put("status", "1");
                result.put("token", token);
            } else {
                logger.info("--------------- 登录失败, 用户输入的用户名或密码错误  ----------------");
                result.put("status", "0");
                result.put("message", "用户名或密码错误");
            }
        } else {
            logger.info("--------------- 登录失败, 用户输入的用户名或密码为空  ----------------");
            result.put("status", "0");
            result.put("message", "用户名或密码不能为空");
        }
//        设置允许跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        JsonUtils.outJsonString(result.toString(), response);
//        return result;

    }

    /**
     * 进入首页
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/main")
    public String toIndex(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "system/main";
    }
}
