package com.fish.dao;

import java.util.Map;

import com.fish.pojo.User;

public interface UserMapper {
	/**
	 * 根据id删除用户
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增用户
     * @param record
     * @return
     */
    int insertSelective(User record);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User selectByPrimaryKey(Integer id);

    /**
     * 更新用户
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(User record);
    
    User getUserByWechatid (Map<String, Object> map);

}