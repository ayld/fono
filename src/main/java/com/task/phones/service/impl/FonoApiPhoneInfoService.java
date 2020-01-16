package com.task.phones.service.impl;

import com.aafanasev.fonoapi.DeviceEntity;
import com.aafanasev.fonoapi.retrofit.FonoApiService;
import com.task.phones.config.FonoApiProperties;
import com.task.phones.mapper.DeviceInfoMapper;
import com.task.phones.service.PhoneInfoService;
import com.task.phones.service.model.DeviceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FonoApiPhoneInfoService implements PhoneInfoService {

    private final static String TOKEN = "dbbaee0aeaad6af100596bf8678af8bf4f71c16ab4d90de";

    private final FonoApiService fonoApi;
    private final FonoApiProperties apiProperties;
    private final DeviceInfoMapper deviceInfoMapper;

    @Autowired
    public FonoApiPhoneInfoService(FonoApiService fonoApi, FonoApiProperties apiProperties, DeviceInfoMapper deviceInfoMapper) {
        this.fonoApi = fonoApi;
        this.apiProperties = apiProperties;
        this.deviceInfoMapper = deviceInfoMapper;
    }

    // TODO circuit breaker and/or cache here so we can work when the API is down
    @Override
    public Set<DeviceInfo> findDetails(String model, Integer maxResults) {
        // TODO check our persistence first if we already have info about the device

        final Response<List<DeviceEntity>> response;
        try {
            response = fonoApi
                    .getLatest(TOKEN, model, maxResults) // TODO aquire token via FonoApiProperties
                    .execute();
        } catch (IOException e) {
            // if we can't retrieve details about the device and is not in local storage
            // we can either throw exception if this is unacceptable to user and we can't continue
            // or continue without details, or return default generic details
            log.error(e.getMessage(), e);
            return Collections.emptySet();
        }

        if (!response.isSuccessful()) {
            log.error("Error during FonoApi call {}", response.errorBody());
            return Collections.emptySet();
        }

        if (response.body() == null) {
            // no devices for model
            return Collections.emptySet();
        }

        return response.body()
                .stream()
                .map(deviceInfoMapper::toServiceModel)
                .collect(Collectors.toSet());
    }
}
