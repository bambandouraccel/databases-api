package net.accel_tech.databases_api.service;

import net.accel_tech.databases_api.dto.InstanceDto;
import net.accel_tech.databases_api.dto.UpdateInstanceRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InstanceService {
    public List<InstanceDto> findAllInstances();
    public InstanceDto addInstance(InstanceDto dto);
    public InstanceDto updateInstance(String id, UpdateInstanceRequestDto updateDto);
    public String deleteInstanceById(String id);
    public InstanceDto findInstanceById(String id);
}
