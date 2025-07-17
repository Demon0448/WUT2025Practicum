package com.oa2.feign;

import com.oa2.util.RESP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;


/**
 * OA 签到管理服务 —— 地址信息获取
 */
@FeignClient(name = "oa-admin-service")
public interface AdminFeignClient {

    /**
     * 根据坐标获取地址信息。
     * 
     * @param coordinates 坐标字符串，格式为"经度,纬度"（例如："116.397428,39.90923"）
     * @return 包含地址信息的RESP对象，其中包含解析后的地址或错误信息
     */
    @GetMapping("/api/v1/admin/location/address")
    public RESP getAddressByCoordinates(@RequestParam("coordinates") String coordinates);


    /**
     * 根据部门ID查询部门名称。
     * @param dept_id
     * @return
     */
    @GetMapping("/api/v1/admin/departments/{dept_id}")
    public String selectDeptById(@PathVariable("dept_id") String dept_id);

}

///***
// * 地址工具类 —— 对外仍暴露静态方法，
// */
//@Component
//@Slf4j
//public class LocationUtil {
//
//    /**
//     * 根据坐标调用腾讯地图API获取地址信息。
//     *
//     * @param coordinates 坐标字符串，格式为"经度,纬度"
//     * @return 解析后的地址信息字符串。如果成功返回地址，否则返回错误提示。
//     */
//    public static String getAddressFromCoordinates(String coordinates) {
//        ...//前期校验、参数处理
//        try {
//            log.info("请求腾讯地图API：" + coordinates);
//
//            TencentMapClient.TencentMapResp resp = tencentMapClient.geocoder(
//                    coordinates,
//                    TENCENT_MAP_KEY,
//                    0,
//                    "radius=1"
//            );
//
//            log.info("响应结果：" + resp);
//            return resp.result.address;
//        } catch (Exception e) {
//            ...//异常处理
//        }
//    }
//    ...//其他属性和方法
//}