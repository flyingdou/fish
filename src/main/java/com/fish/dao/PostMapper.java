package com.fish.dao;

import com.fish.pojo.Post;

public interface PostMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Post record);

    Post selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Post record);

}