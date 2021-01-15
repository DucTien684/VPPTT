package com.springboot.vpp1849.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.vpp1849.model.NV_ChucVu;

@Repository
public interface NV_ChucVuRepository extends JpaRepository<NV_ChucVu, String> {

}
