package com.task.phones.mapper;

import com.aafanasev.fonoapi.DeviceEntity;
import com.task.phones.service.model.DeviceInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface DeviceInfoMapper {

    @Mappings({
        @Mapping(target = "technology", source = "technology"),
        @Mapping(target = "bands2g", source = "_2g_bands"),
        @Mapping(target = "bands3g", source = "_3g_bands"),
        @Mapping(target = "bands4g", source = "_4g_bands")
    })
    DeviceInfo toServiceModel(DeviceEntity deviceEntity);
}
