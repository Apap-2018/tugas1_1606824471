package com.apap.tugas1.service;

import java.util.List;

import com.apap.tugas1.model.ProvinsiModel;


public interface ProvinsiService {
	public List<ProvinsiModel> getListProvinsi();
	ProvinsiModel getProvinsiById(long id);
}
