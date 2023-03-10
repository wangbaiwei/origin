package com.wbw.internalcommon.dto;

import com.wbw.internalcommon.internalcommon.constant.CommonStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@Data
@Accessors(chain = true)
public class ResponseResult<T> {

    private int code;
    private String message;
    private T data;


    /**
     * 成功响应的方法
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseResult success(T data) {
        return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode()).setMessage(CommonStatusEnum.SUCCESS.getValue()).setData(data);
    }


    /**
     * 失败错误码和信息
     *
     * @param code
     * @param message
     * @return
     */
    public static ResponseResult fail(int code, String message) {
        return new ResponseResult().setCode(code).setMessage(message);
    }

    /**
     * 自定义错误码和错误信息
     *
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static ResponseResult fail(int code, String message, String data) {
        return new ResponseResult().setCode(code).setMessage(message).setData(data);
    }


    /**
     * 失败：统一的失败
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseResult fail(T data) {
        return new ResponseResult().setData(data);
    }


    public static void main(String[] args) {

        int[] nums = new int[] {3,1,4,2};
        int p = 6;

        System.out.println(minSubarray(nums, 6));


    }

    public static int minSubarray(int[] nums, int p) {
        int n = nums.length, ans = n;
        var s = new int[n + 1];
        for (int i = 0; i < n; ++i)
            s[i + 1] = (s[i] + nums[i]) % p;
        int x = s[n];
        if (x == 0) return 0;

        var last = new HashMap<Integer, Integer>();
        for (int i = 0; i <= n; ++i) {
            last.put(s[i], i);
            int j = last.getOrDefault((s[i] - x + p) % p, -n);
            ans = Math.min(ans, i - j);
        }
        return ans < n ? ans : -1;
    }
}
