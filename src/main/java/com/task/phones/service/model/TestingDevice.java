package com.task.phones.service.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "devices")
public class TestingDevice {

    @Id
    private String id; // internal mongo ID

    private String name;
    private DeviceInfo deviceInfo;
    private Boolean isAvailable;
    private String booker;
    private LocalDateTime bookingDate;
}
