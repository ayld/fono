package com.task.phones.service.impl;

import com.task.phones.dao.TestingDeviceRepository;
import com.task.phones.service.PhoneInfoService;
import com.task.phones.service.TestingDeviceService;
import com.task.phones.service.model.DeviceInfo;
import com.task.phones.service.model.TestingDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class DefaultTestingDeviceService implements TestingDeviceService {

    private final TestingDeviceRepository repository;
    private final PhoneInfoService phoneInfoService;

    @Autowired
    public DefaultTestingDeviceService(TestingDeviceRepository repository, PhoneInfoService phoneInfoService) {
        this.repository = repository;
        this.phoneInfoService = phoneInfoService;
    }

    @Override
    public Optional<TestingDevice> find(String name) {
        return Optional.empty();
    }

    @Override
    public TestingDevice add(String model) {
        // TODO existence check for idempotence
//        if (repository.exists()) {
//
//        }
        final Set<DeviceInfo> details = phoneInfoService.findDetails(model, 1);
        // its possible that we don't find any details about the model here
        // we can wither refuse the adding of the device or add with some default parameters depending
        // on requirements, current implementation refuses for simplicity

        if (details.isEmpty()) {
            throw new IllegalArgumentException("Could not find details for model: " + model);
        }
        final DeviceInfo modelDetails = details.stream().findFirst().get();

        final TestingDevice testingDevice = new TestingDevice();
        testingDevice.setIsAvailable(true);
        testingDevice.setDeviceInfo(modelDetails);

        return repository.insert(testingDevice);
    }
}
