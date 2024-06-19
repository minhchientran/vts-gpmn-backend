package viettel.gpmn.platform.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import viettel.gpmn.platform.core.data.BaseData;
import viettel.gpmn.platform.core.entities.EntityWithULID;
import viettel.gpmn.platform.core.repositories.BaseRepository;

import java.util.List;
import java.util.stream.Collectors;

public abstract class GenericSaveService<
        T extends EntityWithULID,
        K extends BaseData,
        R extends BaseRepository<T>> extends BaseService {

    @Autowired
    protected R repository;

    @Transactional
    public void create(List<K> listData) {
        listData = listData
                .stream()
                .filter(moduleData -> moduleData.getId() == null).collect(Collectors.toList());
        this.save(listData);
    }

    @Transactional
    public void update(List<K> listData) {
        listData = listData
                .stream()
                .filter(moduleData -> moduleData.getId() != null).collect(Collectors.toList());
        this.save(listData);
    }

    public abstract List<T> save(List<K> listData);
}
