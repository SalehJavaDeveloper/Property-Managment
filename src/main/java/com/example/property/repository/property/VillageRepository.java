package com.example.property.repository.property;

import com.example.property.entity.property.VillageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VillageRepository extends JpaRepository<VillageEntity, Long> {
    VillageEntity getVillageEntityByVillageName(String villageName);
}
