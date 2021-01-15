package com.springboot.vpp1849.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.vpp1849.model.Ward;

@Repository
public interface WardRepository extends JpaRepository<Ward, String>{

	@Query(value = "FROM Ward w WHERE w.district.districtId = ?1")
	List<Ward> getWard (String districtId);
	
	@Query(value = "SELECT Name FROM Ward WHERE WardId = ?1", nativeQuery = true)
	String getNameByWardId(String wardId);
}
