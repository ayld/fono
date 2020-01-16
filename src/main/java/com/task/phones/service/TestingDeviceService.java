package com.task.phones.service;

import com.task.phones.service.model.TestingDevice;

import java.util.Optional;

public interface TestingDeviceService {
    Optional<TestingDevice> find(String name);
    TestingDevice add(String model);
}
