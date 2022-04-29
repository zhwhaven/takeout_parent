package com.haven.adminservice.mapper;

import com.haven.adminservice.entity.AclUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2022-03-28
 */
@Mapper
@Repository
public interface AclUserMapper extends BaseMapper<AclUser> {

}
