package com.task.phones.mapper;

import com.task.phones.rest.model.DeviceDto;
import com.task.phones.service.model.TestingDevice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface DeviceMapper {

    @Mappings({
            @Mapping(target = "booker"),
            @Mapping(target = "isAvailable"),
            @Mapping(target = "bookingDate"),
            @Mapping(target = "technology", source = "deviceInfo.technology"),
            @Mapping(target = "bands2g", source = "deviceInfo.bands2g"),
            @Mapping(target = "bands3g", source = "deviceInfo.bands3g"),
            @Mapping(target = "bands4g", source = "deviceInfo.bands4g"),
    })
    DeviceDto toDto(TestingDevice entity);

    @Mappings({
            @Mapping(target = "booker"),
            @Mapping(target = "isAvailable"),
            @Mapping(target = "name"),
            @Mapping(target = "bookingDate"),
    })
    TestingDevice toEntity(DeviceDto dto);
}
