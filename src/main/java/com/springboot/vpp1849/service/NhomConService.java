package com.springboot.vpp1849.service;

import java.util.List;

import com.springboot.vpp1849.model.SP_NhomCon;

public interface NhomConService {

	List<SP_NhomCon> getNhomCon(String idNhomSP);

	List<SP_NhomCon> getBCNhomCon(String idNhomSPCon);

	List<SP_NhomCon> getBCNhomConByIdSP(String idSanPham);

}
