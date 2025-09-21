package net.accel_tech.databases_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstanceDto {

    @JsonProperty("id")
    private String id;

    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "Name must be less than 50 characters")
    @JsonProperty("name")
    private String name;

    @NotBlank(message = " is required")
    @JsonProperty("database")
    private String database;

    @NotBlank(message = "Version is required")
    @JsonProperty("version")
    private String Version;

    @NotBlank(message = "Sizing is required")
    @JsonProperty("sizing")
    private String sizing;

}
