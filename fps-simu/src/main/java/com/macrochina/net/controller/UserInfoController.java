package com.macrochina.net.controller;

import com.macrochina.net.entity.AdminUserInfo;
import com.macrochina.net.entity.Res;
import com.macrochina.net.service.AdminUserInfoService;
import com.macrochina.net.service.SysParamsService;
import com.macrochina.net.utils.SysParamsContst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Api(tags = "用户管理")
@RestController
@RequestMapping("/aui")
public class UserInfoController {

    private Log logger = LogFactory.getLog(UserInfoController.class);

    @Resource
    private AdminUserInfoService adminUserInfoService;

    @ApiOperation(value = "用户测试",notes = "用户测试notes")
    @RequestMapping("/add")
    public Res addAdminUserInfoService(HttpServletRequest request) {
        String userName = request.getParameter("userName");
        String userLoginName = request.getParameter("userLoginName");
        String userNickName = request.getParameter("userNickName");
        logger.info("userName:"+userName+",userLoginName:"+userLoginName+",userNickName:"+userNickName);
        if(StringUtils.isEmpty(userLoginName)||StringUtils.isEmpty(userName)||StringUtils.isEmpty(userNickName)){
            return Res.Failure(Res.Failure,"添加错误！");
        }
        AdminUserInfo adminUserInfo=new AdminUserInfo();
        adminUserInfo.setUserLoginName(userLoginName);
        adminUserInfo.setUserName(userName);
        adminUserInfo.setUserNickName(userNickName);
        adminUserInfo.setUserPassword(SysParamsService.getSysParams(SysParamsContst.TECH_PASSWORD));
        AdminUserInfo res = null;
        res = this.adminUserInfoService.addAdminUserInfo(adminUserInfo);
        if (res!=null) {
            return Res.Success(res);
        }else{
            return Res.Failure(Res.Failure,"添加错误！");
        }
    }

    @RequestMapping("/findAll")
    public Res findAll(@RequestParam Integer page, @RequestParam Integer limit,HttpServletRequest request) {
        AdminUserInfo adminUserInfo = new AdminUserInfo();
        String userName = request.getParameter("userName");
        String userLoginName = request.getParameter("userLoginName");
        if(!StringUtils.isEmpty(userLoginName)){
            adminUserInfo.setUserLoginName(userLoginName);
        }
        if(!StringUtils.isEmpty(userName)){
            adminUserInfo.setUserName(userName);
        }
        logger.info("page:"+page+",limit:"+limit);
        Page<AdminUserInfo> data = this.adminUserInfoService.findAllAdminUserInfoByCdn(page,limit,adminUserInfo);
        if (data!=null) {
            logger.info("findAll data:"+data+",data.getContent()"+data.getContent().size());
            for (AdminUserInfo a:data.getContent()) {
                logger.info(":"+a.getUserName());
            }
            return Res.Success(data);
        }else{
            return Res.Failure(Res.Failure,"错误！");
        }
    }

    @DeleteMapping("/del/{rowId}")
    public Res delete(@PathVariable Long rowId) {
        logger.info("delete.rowId:"+rowId);
        AdminUserInfo adminUserInfo=this.adminUserInfoService.findAdminUserInfoById(rowId);
        if(adminUserInfo!=null){
            boolean res=this.adminUserInfoService.deleteAdminUserInfoById(rowId);
            if (res) {
                return Res.Success();
            } else {
                return Res.Failure(Res.Failure, "删除失败！");
            }
        }else{
            return Res.Failure(Res.Failure, "删除失败！");
        }
    }

    @RequestMapping("/update")
    public Res updateAdminUserInfoService(HttpServletRequest request) {
        String idstr = request.getParameter("id");
        String userName = request.getParameter("userName");
        String userLoginName = request.getParameter("userLoginName");
        String userNickName = request.getParameter("userNickName");
        logger.info("userName:"+userName+",userLoginName:"+userLoginName+",userNickName:"+userNickName);
        if(StringUtils.isEmpty(idstr)||StringUtils.isEmpty(userLoginName)||StringUtils.isEmpty(userName)||StringUtils.isEmpty(userNickName)){
            return Res.Failure(Res.Failure,"修改信息错误！");
        }
        Long id=Long.parseLong(idstr);
        AdminUserInfo orgAdminUserInfo=this.adminUserInfoService.findAdminUserInfoById(id);
        AdminUserInfo newAdminUserInfo=new AdminUserInfo();
        newAdminUserInfo.setUserNickName(userNickName);
        newAdminUserInfo.setUserName(userName);
        newAdminUserInfo.setUserLoginName(userLoginName);
        boolean res=this.adminUserInfoService.updateAllAdminUserInfoNameOrNick(orgAdminUserInfo,newAdminUserInfo);
        if (res) {
            return Res.Success(orgAdminUserInfo);
        }else{
            return Res.Failure(Res.Failure,"修改信息错误！");
        }
    }

    @PostMapping("/get")
    public Res getAdminUserInfo(@RequestParam Long rowId) {
        logger.info("getAdminUserInfo.rowId"+rowId);
        AdminUserInfo adminUserInfo=this.adminUserInfoService.findAdminUserInfoById(rowId);
        if (adminUserInfo!=null) {
            logger.info("getAdminUserInfo.adminUserInfo"+adminUserInfo.toString());
            return Res.Success(adminUserInfo);
        }else{
            return Res.Failure(Res.Failure,"修改信息错误！");
        }
    }



}
