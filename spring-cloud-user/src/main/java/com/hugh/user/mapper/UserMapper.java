package com.hugh.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hugh.user.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author AS
 * @date 2020/9/7 14:53
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
