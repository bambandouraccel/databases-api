package net.accel_tech.databases_api.dto;

import lombok.Data;

@Data
public class UpdateInstanceRequestDto {
    private String name;
    private String version;
    private String database;
    private String sizing;
}
