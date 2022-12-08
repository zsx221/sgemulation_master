package com.macrochina.net.dao;

import com.macrochina.net.entity.AdminUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserInfoRepository extends JpaRepository<AdminUserInfo,Long> , JpaSpecificationExecutor {

    AdminUserInfo findByUserLoginName(String userLoginName);

    AdminUserInfo findByUserLoginNameAndUserPassword(String userLoginName, String userPassword);
}
