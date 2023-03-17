package com.wbw.servcedirveruser.service.impl;

import com.wbw.internalcommon.dto.Car;
import com.wbw.servcedirveruser.mapper.CarMapper;
import com.wbw.servcedirveruser.service.ICarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 王佰伟
 * @since 2023-03-17
 */
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements ICarService {

}
