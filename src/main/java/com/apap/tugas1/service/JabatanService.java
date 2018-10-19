package com.apap.tugas1.service;

import java.util.List;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;


public interface JabatanService {
	List<JabatanModel> getListJabatan();
	JabatanModel addJabatan(JabatanModel jabatan);
}
