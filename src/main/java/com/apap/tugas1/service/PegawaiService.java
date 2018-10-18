package com.apap.tugas1.service;

import com.apap.tugas1.model.PegawaiModel;



/*
 * CarService
 */
public interface PegawaiService {
	void addPegawai(PegawaiModel pegawai);
	public PegawaiModel getPegawai(Long id);
	PegawaiModel getPegawaiDetailByNip(String nip);
	
}
