package com.oa7.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 腾讯地图 —— 逆地理编码
 */
@FeignClient(
        name = "tencentMapClient",
        url = "https://apis.map.qq.com/ws"
)
public interface TencentMapClient {

    /**
     * 根据坐标获取地址
     *
     * @param location  "lat,lng"
     * @param key       你在控制台申请的 key
     * @param getPoi    是否返回周边 POI
     * @param poiOpts   poi_options 参数字符串
     */
    @GetMapping("/geocoder/v1")
    TencentMapResp geocoder(
            @RequestParam("location") String location,
            @RequestParam("key")      String key,
            @RequestParam(value = "get_poi", required = false, defaultValue = "0") Integer getPoi,
            @RequestParam(value = "poi_options", required = false) String poiOpts
    );

    /* —— 简单 DTO —— */
    class TencentMapResp {
        public Integer status;
        public String  message;
        public Result  result;

        public static class Result {
            public String address;
            /* 根据需要再补 formatted_addresses、address_component 等字段 */
        }

        /** 业务层判定成功 */
        public boolean ok() {
            return status != null && status == 0;
        }
    }
}