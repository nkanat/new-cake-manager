package com.waracle.cakemgr.repository;

import com.waracle.cakemgr.entity.CakeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CakeManagerRepository extends JpaRepository<CakeEntity,Integer> {
}
