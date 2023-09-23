package com.wbw.servcedirveruser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wbw.internalcommon.dto.DriverUser;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverUserMapper extends BaseMapper<DriverUser> {

    int selectDriverUserCountByCityCode(String cityCode);

}
