package com.macrochina.net.service;

import com.macrochina.net.dao.UserInfoRepository;
import com.macrochina.net.entity.AdminUserInfo;
import com.macrochina.net.utils.SHAEncrypt;
import com.macrochina.net.utils.SysParamsContst;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;

@Service
public class AdminUserInfoService {

    private Log logger = LogFactory.getLog(AdminUserInfoService.class);

    @Autowired
    private UserInfoRepository userInfoRepository;

    /**
     * 用戶查重
     */
    public AdminUserInfo findAdminUserInfoByUserLoginName(String userLoginName) {
        AdminUserInfo res = null;
        if (!StringUtils.isEmpty(userLoginName)) {
            res = this.userInfoRepository.findByUserLoginName(userLoginName);
        } else {
            logger.error("findAdminUserInfoByUserLoginName param invalid. " + userLoginName);
        }
        return res;
    }

    /**
     * 用戶删除
     */
    public boolean deleteAdminUserInfoById(Long id) {
        boolean res = false;
        try{
            if (id!=null) {
                this.userInfoRepository.deleteById(id);
                res=true;
            } else {
                logger.error("findAdminUserInfoById param invalid. " + id);
            }
        }catch (Exception e){
            logger.error("findAdminUserInfoById param invalid. " + id+",message:"+e.getMessage());
        }
        return res;
    }

    /**
     * 用戶查詢
     */
    public AdminUserInfo findAdminUserInfoById(Long id) {
        AdminUserInfo res = null;
        if (id!=null) {
            res = this.userInfoRepository.getOne(id);
        } else {
            logger.error("findAdminUserInfoById param invalid. " + id);
        }
        return res;
    }

    /**
     * 创建后台管理用户
     */
    public AdminUserInfo addAdminUserInfo(AdminUserInfo adminUserInfo) {
        AdminUserInfo res = null;
        if (adminUserInfo != null) {
            if (!StringUtils.isEmpty(adminUserInfo.getUserName()) &&
                    !StringUtils.isEmpty(adminUserInfo.getUserLoginName()) &&
                    !StringUtils.isEmpty(adminUserInfo.getUserNickName()) &&
                    !StringUtils.isEmpty(adminUserInfo.getUserPassword())) {

                //检查是否有重复的userLoginName
                AdminUserInfo aui=this.findAdminUserInfoByUserLoginName(adminUserInfo.getUserLoginName());
                if(aui!=null){
                    logger.error("addAdminUserInfo duplicate. " );
                    return null;
                }

                //用户密码加密存储
                String key=SysParamsService.getSysParams(SysParamsContst.ITGO_KEY);
                if(StringUtils.isEmpty(key)){
                    logger.error("addAdminUserInfo key is empty. " );
                    return null;
                }
                try {
                    String password=SHAEncrypt.String2SHA256(adminUserInfo.getUserPassword(),key);
                    adminUserInfo.setUserPassword(password);
                } catch (Exception e) {
                    logger.error("addAdminUserInfo password encrypt error. "+e.getMessage() );
                }
                res = this.userInfoRepository.save(adminUserInfo);
            } else {
                logger.error("addAdminUserInfo properties invalid. " + adminUserInfo.toString());
            }
        }
        return res;
    }

    /**
     * 查询后台管理员用户信息
     */
    public Page<AdminUserInfo> findAllAdminUserInfoByCdn(int page, int size, AdminUserInfo adminUserInfo) {
        Page<AdminUserInfo> rtn = null;
        Pageable pageable = PageRequest.of(page == 0 ? 0 : page - 1, size == 0 ? 10 : size);
        Specification<AdminUserInfo> adminUserInfos = new Specification<AdminUserInfo>() {
            @Override
            public Predicate toPredicate(Root<AdminUserInfo> root, CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                Predicate predicate = null;
                if (!StringUtils.isEmpty(adminUserInfo.getUserLoginName())) {
                    Path<String> userLoginName = root.get("userLoginName");
                    predicate = criteriaBuilder.like(userLoginName, "%" + adminUserInfo.getUserLoginName() + "%");
                }
                if (!StringUtils.isEmpty(adminUserInfo.getUserName())) {
                    Path<String> userName = root.get("userName");
                    if (predicate != null) {
                        predicate = criteriaBuilder.and(criteriaBuilder.like(userName, "%" + adminUserInfo.getUserName() + "%"));
                    }
                }
                if(predicate!=null){
                    query.where(predicate);
                }
                return query.getRestriction();
            }
        };
        rtn = userInfoRepository.findAll(adminUserInfos, pageable);
        return rtn;
    }

    /**
     * 更新用户信息，只能更新用户名称和昵称信息
     */
    public boolean updateAllAdminUserInfoNameOrNick(AdminUserInfo orgAdminUserInfo,AdminUserInfo newAdminUserInfo) {
        boolean rtn = false;
        if (orgAdminUserInfo == null||newAdminUserInfo==null) {
            logger.error("更新管理信息错误！adminUserInfo is null.");
            return rtn;
        }

        if (StringUtils.isEmpty(orgAdminUserInfo.getId())) {
            logger.error("更新管理信息错误！orgAdminUserInfo id is null.");
            return rtn;
        }
        if (!StringUtils.isEmpty(newAdminUserInfo.getUserName()) ||
                !StringUtils.isEmpty(newAdminUserInfo.getUserNickName()) ||
                !StringUtils.isEmpty(newAdminUserInfo.getUserPassword())||!StringUtils.isEmpty(newAdminUserInfo.getUserLoginName())) {
            //检查是否有重复的userLoginName
            AdminUserInfo aui=this.findAdminUserInfoByUserLoginName(newAdminUserInfo.getUserLoginName());
            if(newAdminUserInfo.getUserLoginName().equals(aui.getUserLoginName())){
                orgAdminUserInfo.setUserNickName(newAdminUserInfo.getUserNickName());
                orgAdminUserInfo.setUserName(newAdminUserInfo.getUserName());
                orgAdminUserInfo.setUserLoginName(newAdminUserInfo.getUserLoginName());
                orgAdminUserInfo = this.userInfoRepository.saveAndFlush(orgAdminUserInfo);
                if (orgAdminUserInfo == null) {
                    logger.error("更新管理信息错误！orgAdminUserInfo  proerties is copy. id=" + orgAdminUserInfo.getId());
                    return rtn;
                }
                rtn = true;
            }else{
                if(aui!=null){
                    logger.error("updateAllAdminUserInfoNameOrNick duplicate. UserLoginName"+newAdminUserInfo.getUserLoginName() );
                    return rtn;
                }
            }
        }
        return rtn;
    }
}
