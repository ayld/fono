package com.task.phones.dao;

import com.task.phones.service.model.TestingDevice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestingDeviceRepository extends MongoRepository<TestingDevice, String> {
}
