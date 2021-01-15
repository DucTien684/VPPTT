package com.springboot.vpp1849.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.vpp1849.model.District;

@Repository
public interface DistrictRepository extends JpaRepository<District, String>{
	
	@Query(value = "FROM District d WHERE d.province.provinceId = ?1")
	List<District> getDistrict(String provinceId);
	
	@Query(value = "SELECT Name FROM District WHERE DistrictId = ?1", nativeQuery = true)
	String getNameByDistrictId(String districtId);
}
