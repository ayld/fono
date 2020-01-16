package com.task.phones.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDto {

    @NotEmpty
    private String technology;

    @NotEmpty
    private String bands2g;

    @NotEmpty
    private String bands3g;

    @NotEmpty
    private String bands4g;

    private Boolean isAvailable;

    private String booker;

    private LocalDateTime bookingDate;
}
