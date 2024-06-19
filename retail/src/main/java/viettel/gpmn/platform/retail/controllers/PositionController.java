package viettel.gpmn.platform.retail.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import viettel.gpmn.platform.core.controllers.BaseController;
import viettel.gpmn.platform.core.data.response.Response;
import viettel.gpmn.platform.retail.services.PositionService;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/positions")
public class PositionController extends BaseController {

    private PositionService positionService;

    @GetMapping(value = "/name")
    public Response getListPositionName() {
        return Response.ok(positionService.getListPositionName());
    }

}
