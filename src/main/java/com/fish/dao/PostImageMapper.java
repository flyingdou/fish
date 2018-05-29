package com.fish.dao;

import com.fish.pojo.PostImage;

public interface PostImageMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(PostImage record);

    PostImage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PostImage record);

}