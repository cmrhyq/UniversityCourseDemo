package com.chinasoft.shop.dao;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface UserMapper {

    public Map<String,Object> queryUserIsExist(Map<String,Object> map);

    public int insertUser(Map<String,Object> map);

    public Map<String,Object> userLogin(Map<String,Object> map);

    public int updateUser(Map<String,Object> map);
}
