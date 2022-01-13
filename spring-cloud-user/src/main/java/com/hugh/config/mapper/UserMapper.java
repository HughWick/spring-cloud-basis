package com.hugh.config.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hugh.config.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author AS
 * @date 2020/9/7 14:53
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
