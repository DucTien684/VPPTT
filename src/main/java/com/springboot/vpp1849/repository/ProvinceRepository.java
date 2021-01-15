package com.springboot.vpp1849.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.vpp1849.model.Province;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, String> {
	@Query(value = "SELECT Name FROM Province WHERE ProvinceId = ?1", nativeQuery = true)
	String getNameByProvinceId(String provinceId);
}
