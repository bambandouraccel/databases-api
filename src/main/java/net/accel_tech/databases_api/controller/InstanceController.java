package net.accel_tech.databases_api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.accel_tech.databases_api.dto.ApiResponse;
import net.accel_tech.databases_api.dto.DeleteInstanceResponseDto;
import net.accel_tech.databases_api.dto.InstanceDto;
import net.accel_tech.databases_api.dto.UpdateInstanceRequestDto;
import net.accel_tech.databases_api.exception.BadRequestException;
import net.accel_tech.databases_api.service.InstanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/databases")
@RequiredArgsConstructor
public class InstanceController {

    private final InstanceService instanceService;

    @GetMapping(path = "")
    public ResponseEntity<?> findAllInstances(){
        List<InstanceDto> list = instanceService.findAllInstances();
        list = list.stream()
                .sorted(Comparator.comparing(InstanceDto::getId).reversed())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponse<>(true, list));
    }

    @PostMapping(path = "", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addNewInstance(@Valid @RequestBody InstanceDto instanceDto){
        InstanceDto createdInstance = instanceService.addInstance(instanceDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, createdInstance));


    }

    @PatchMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<ApiResponse<InstanceDto>> updateInstanceById(@PathVariable String id, @Valid @RequestBody String rawBody) {
        // Convertir manuellement le texte en JSON
        ObjectMapper mapper = new ObjectMapper();
        UpdateInstanceRequestDto updateDto;
        try {
            updateDto = mapper.readValue(rawBody, UpdateInstanceRequestDto.class);
        } catch (JsonProcessingException e) {
            throw new BadRequestException("Invalid JSON format");
        }

        InstanceDto updatedInstance = instanceService.updateInstance(id, updateDto);
        return ResponseEntity.ok(new ApiResponse<>(true, updatedInstance));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteInstanceById(@PathVariable("id") String id){
        String deletedId = instanceService.deleteInstanceById(id);
        DeleteInstanceResponseDto response = new DeleteInstanceResponseDto();
        response.setId(deletedId);
        return ResponseEntity.ok(new ApiResponse<>(true, response));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findInstanceById(@PathVariable String id){
        InstanceDto instance = instanceService.findInstanceById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, instance));
    }
}
