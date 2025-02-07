package viettel.gpmn.platform.retail.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import viettel.gpmn.platform.core.controllers.BaseController;
import viettel.gpmn.platform.core.data.response.Response;
import viettel.gpmn.platform.retail.services.SupplierUnitService;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/units")
public class SupplierUnitController extends BaseController {

    private SupplierUnitService supplierUnitService;

    @GetMapping
    public Response getList() {
        return Response.ok(supplierUnitService.getList());
    }

}
