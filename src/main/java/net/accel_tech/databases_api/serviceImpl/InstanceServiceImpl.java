package net.accel_tech.databases_api.serviceImpl;

import lombok.RequiredArgsConstructor;
import net.accel_tech.databases_api.dto.InstanceDto;
import net.accel_tech.databases_api.dto.UpdateInstanceRequestDto;
import net.accel_tech.databases_api.entity.Instance;
import net.accel_tech.databases_api.exception.BadRequestException;
import net.accel_tech.databases_api.exception.ResourceNotFoundException;
import net.accel_tech.databases_api.repository.InstanceRepository;
import net.accel_tech.databases_api.service.InstanceService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("instanceService")
@RequiredArgsConstructor
public class InstanceServiceImpl implements InstanceService {

    private final InstanceRepository instanceRepository;

    @Override
    public List<InstanceDto> findAllInstances() {
        List<InstanceDto>  instanceDtos = new ArrayList<>();
        List<Instance> list = instanceRepository.findAll();
        list.stream()
                .forEach(instance -> {
                    InstanceDto instanceDto = mapEntityToDto(instance);
                    instanceDtos.add(instanceDto);
                });
        return instanceDtos;
    }

    @Override
    public InstanceDto addInstance(InstanceDto dto) {

        if (instanceRepository.existsInstanceByName(dto.getName())) {
            throw new BadRequestException("Database with name '" + dto.getName() + "' already exists");
        }

        Instance instance = new Instance();
        mapDtoToEntity(dto, instance);
        Instance addInstance = instanceRepository.save(instance);
        return mapEntityToDto(addInstance);
    }

    @Override
    public InstanceDto updateInstance(String id, UpdateInstanceRequestDto updateDto) {
        Instance existingInstance = instanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instance not found with id: " + id));

        if (updateDto.getName() != null) {
            existingInstance.setName(updateDto.getName());
        }
        if (updateDto.getDatabase() != null) {
            existingInstance.setDatabase(updateDto.getDatabase());
        }
        if (updateDto.getSizing() != null) {
            existingInstance.setSizing(updateDto.getSizing());
        }
        if (updateDto.getVersion() != null) {
            existingInstance.setVersion(updateDto.getVersion());
        }
        Instance updatedInstance = instanceRepository.save(existingInstance);
        return mapEntityToDto(updatedInstance);
    }

    @Override
    public String deleteInstanceById(String id) {
        Instance existingInstance = findInstance(id);
        instanceRepository.delete(existingInstance);
        return id;
    }

    @Override
    public InstanceDto findInstanceById(String id) {
        Instance findingInstance = findInstance(id);
        return mapEntityToDto(findingInstance);
    }

    private Instance findInstance(String id){
        return instanceRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Instance not found with id:"+id));
    }


    private InstanceDto mapEntityToDto(Instance instance){
        InstanceDto instanceDto = new InstanceDto();
        instanceDto.setId(instance.getId());
        instanceDto.setName(instance.getName());
        instanceDto.setDatabase(instance.getDatabase());
        instanceDto.setVersion(instance.getVersion());
        instanceDto.setSizing(instance.getSizing());
        return instanceDto;
    }

    private void mapDtoToEntity(InstanceDto instanceDto, Instance instance){
        instance.setId(instanceDto.getId());
        instance.setName(instanceDto.getName());
        instance.setDatabase(instanceDto.getDatabase());
        instance.setVersion(instanceDto.getVersion());
        instance.setSizing(instanceDto.getSizing());
    }

}
