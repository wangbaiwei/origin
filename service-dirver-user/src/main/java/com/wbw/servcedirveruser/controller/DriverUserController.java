package com.wbw.servcedirveruser.controller;


import com.wbw.internalcommon.constant.DriverCarConstants;
import com.wbw.internalcommon.dto.DriverUser;
import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.response.DriverUserExistsResponse;
import com.wbw.servcedirveruser.service.DriverUserService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class DriverUserController {

    @Autowired
    private DriverUserService driverUserService;


    // 新增司机
    @PostMapping("/user")
    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser) {
        log.info("driver user info: {}", driverUser);

        ResponseResult responseResult = driverUserService.addDriverUser(driverUser);
        return responseResult;
    }

    // 更新司机
    @PutMapping("/user")
    public ResponseResult updateUser(@RequestBody DriverUser driverUser) {
        log.info("修改的用户信息：{}", JSONObject.fromObject(driverUser).toString());
        ResponseResult responseResult = driverUserService.updateDriverUser(driverUser);
        return responseResult;

    }

    /**
     * 根据条件查询user
     */
    @GetMapping("/check-driver/{driverPhone}")
    public ResponseResult<DriverUser> getUser(@PathVariable("driverPhone") String driverPhone) {

        ResponseResult<DriverUser> driverUserByPhone = driverUserService.getDriverUserByPhone(driverPhone);
        DriverUser driverPhoneDB = driverUserByPhone.getData();
        DriverUserExistsResponse response = new DriverUserExistsResponse();

        int ifExists = DriverCarConstants.DRIVER_EXISTS;
        if (ObjectUtils.isEmpty(driverPhoneDB)) {
            ifExists = DriverCarConstants.DRIVER_NOT_EXISTS;
            response.setDriverPhone("");
            response.setIsExists(ifExists);
        } else {
            response.setDriverPhone(driverPhoneDB.getDriverPhone());
            response.setIsExists(ifExists);
        }



        return ResponseResult.success(response);
    }
}
