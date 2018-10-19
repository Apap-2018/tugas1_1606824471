package com.apap.tugas1.service;

import java.util.List;

import com.apap.tugas1.model.JabatanModel;


public interface JabatanService {
	List<JabatanModel> getListJabatan();
	JabatanModel addJabatan(JabatanModel jabatan);
	JabatanModel getJabatanDetailById(long id);
	public void updateJabatan(long idJabatan, JabatanModel jabatan);
	public void deleteJabatan(long id);
}
