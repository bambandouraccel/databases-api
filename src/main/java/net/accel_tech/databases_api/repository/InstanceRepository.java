package net.accel_tech.databases_api.repository;

import net.accel_tech.databases_api.entity.Instance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstanceRepository extends MongoRepository<Instance, String> {
    boolean existsInstanceByName(String name);
}
