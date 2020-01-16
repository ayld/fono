package com.task.phones.service;

import com.task.phones.service.model.DeviceInfo;

import java.util.Set;

public interface PhoneInfoService {
    Set<DeviceInfo> findDetails(String model, Integer maxResults);
}
