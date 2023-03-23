package com.wbw.servcedirveruser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wbw.internalcommon.dto.Car;
import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.response.TerminalResponse;
import com.wbw.internalcommon.response.TrackResponse;
import com.wbw.servcedirveruser.mapper.CarMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wbw.servcedirveruser.remote.ServiceMapClient;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.util.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 王佰伟
 * @since 2023-03-17
 */
@Service
@Slf4j
public class CarService extends ServiceImpl<CarMapper, Car> implements IService<Car> {

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private ServiceMapClient serviceMapClient;

    public ResponseResult addCar(Car car) {

        // 获得车辆的tid
        ResponseResult<TerminalResponse> responseResult = serviceMapClient.addTerminal(car.getVehicleNo());
        log.info("创建终端接口响应信息：{}", JSONUtils.valueToString(responseResult));
        LocalDateTime now = LocalDateTime.now();
        car.setGmtModified(now);
        car.setGmtCreate(now);
        String tid = responseResult.getData().getTid();
        car.setTid(responseResult.getData().getTid());


        ResponseResult<TrackResponse> trackResponseResponseResult = serviceMapClient.addTrack(tid);
        log.info("创建轨迹接口响应信息：{}", JSONUtils.valueToString(trackResponseResponseResult));
        TrackResponse data = trackResponseResponseResult.getData();
        String trid = data.getTrid();
        String trname = data.getTrname();
        car.setTrid(trid);
        car.setTrname(trname);
        log.info("获得车辆的轨迹trid：{}, trname:{}", trid, trname);

        int insert = carMapper.insert(car);
        return ResponseResult.success(insert);
    }

}
