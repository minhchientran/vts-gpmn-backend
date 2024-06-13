package viettel.gpmn.platform.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import viettel.gpmn.platform.core.data.BaseData;
import viettel.gpmn.platform.core.data.response.Response;
import viettel.gpmn.platform.core.entities.EntityWithULID;
import viettel.gpmn.platform.core.services.GenericSaveService;

import java.util.List;

public class GenericSaveController<
        T extends EntityWithULID,
        K extends BaseData,
        S extends GenericSaveService<T, K, ?>> extends BaseController {

    @Autowired
    protected S service;

    @PostMapping
    public Response create(@RequestBody List<K> listData) {
        service.create(listData);
        return Response.ok();
    }

    @PutMapping
    public Response update(@RequestBody List<K> listData) {
        service.update(listData);
        return Response.ok();
    }

}
