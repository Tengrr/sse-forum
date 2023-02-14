package com.matt.community.dao;

import com.matt.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {

    //查询帖子信息(如指定userId!=0)则必须是指定用户的帖子，且未被拉黑
    List<DiscussPost> selectDiscussPosts(int userId, int limit, int offset);

    //查询帖子总行数，用于分页时计算页数
    int selectDiscussPostRows(@Param("userId") int userId);
}
