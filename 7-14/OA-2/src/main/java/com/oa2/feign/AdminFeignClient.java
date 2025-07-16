package com.oa2.feign;


import com.oa2.util.RESP;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "oa-admin-service")
public interface AdminFeignClient {
    @GetMapping("/api/v1/admin/employees/departments")
    public RESP selectAllDept();


    @GetMapping("/api/v1/admin/location/address")
    public RESP getAddressByCoordinates(@RequestParam("coordinates") String coordinates);


}
