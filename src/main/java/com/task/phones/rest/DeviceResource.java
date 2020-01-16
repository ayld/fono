package com.task.phones.rest;

import com.task.phones.mapper.DeviceMapper;
import com.task.phones.rest.model.CreateDeviceDto;
import com.task.phones.rest.model.DeviceDto;
import com.task.phones.service.TestingDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/devices")
public class DeviceResource {

    private final TestingDeviceService testingDeviceService;
    private final DeviceMapper deviceMapper;

    @Autowired
    public DeviceResource(TestingDeviceService testingDeviceService, DeviceMapper deviceMapper) {
        this.testingDeviceService = testingDeviceService;
        this.deviceMapper = deviceMapper;
    }

    @GetMapping
    public ResponseEntity<DeviceDto> get() {
        return ResponseEntity.ok(new DeviceDto());
    }

    @PostMapping
    public ResponseEntity<DeviceDto> createDevice(@RequestBody CreateDeviceDto newDevice) {
        return ResponseEntity.ok(
                deviceMapper.toDto(
                        testingDeviceService.add(newDevice.getModel())
                )
        );
    }
}
