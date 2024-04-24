package com.example.authservice.controllers;

import com.example.authservice.services.CommonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.viettel.core.data.response.Response;

@RestController
@RequestMapping(value = {"/v1/common"})
public class CommonController {
    private final CommonService commonService;

    public CommonController(
            CommonService commonService
    ) {
        this.commonService = commonService;
    }
    @GetMapping(value = {"/status"})
    public Response getListStatus() {
        return Response.ok(commonService.getListStatus());
    }
}
