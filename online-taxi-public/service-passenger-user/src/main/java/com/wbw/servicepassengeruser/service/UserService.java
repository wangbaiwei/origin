package com.wbw.servicepassengeruser.service;

import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.servicepassengeruser.dto.PassengerUser;
import com.wbw.servicepassengeruser.mapper.PassengerUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private PassengerUserMapper passengerUserMapper;


    public ResponseResult loginOrRegister(String passengerPhone) {
        // 根据手机号查询用户信息

        Map<String, Object> map = new HashMap<>();
        map.put("passenger_phone", passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);
        System.out.println(passengerUsers.size() == 0 ? "无记录" : passengerUsers.get(0).getPassengerPhone());

        // 判断用户信息是否存在
        if (passengerUsers.size() == 0) {
            PassengerUser passengerUser = new PassengerUser();
            passengerUser.setPassengerName("张三");
            passengerUser.setPassengerGender((byte) 0);
            passengerUser.setPassengerPhone(passengerPhone);
            passengerUser.setState((byte) 0);
            LocalDate now = LocalDate.now();
            passengerUser.setGmtCreate(now);
            passengerUser.setGmtUpdate(now);
            passengerUserMapper.insert(passengerUser);

        }
        // 若不存在，插入用户信息
        return ResponseResult.success();

    }

}
