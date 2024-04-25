package com.example.authservice.services;

import com.example.authservice.data.modules.ModuleData;
import com.example.authservice.entities.Modules;
import com.example.authservice.repositories.ModuleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.viettel.core.services.BaseService;

@Service
public class ModuleService extends BaseService {

    private final ModuleRepository moduleRepository;
    public ModuleService(
            ModuleRepository moduleRepository
    ) {
        this.moduleRepository = moduleRepository;
    }

    @Transactional
    public void createModule(ModuleData moduleData) {
        Modules module = modelMapper.map(moduleData, Modules.class);
        moduleRepository.save(module);
    }
}
