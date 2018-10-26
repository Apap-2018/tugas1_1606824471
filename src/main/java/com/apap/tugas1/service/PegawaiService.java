package com.apap.tugas1.service;


import java.time.LocalDate;

import com.apap.tugas1.model.PegawaiModel;



/*
 * CarService
 */
public interface PegawaiService {
	PegawaiModel addPegawai(PegawaiModel pegawai);
	public PegawaiModel getPegawai(Long id);
	PegawaiModel getPegawaiDetailByNip(String nip);
	public void generateNip(PegawaiModel pegawai);
	public void updatePegawai(long idPegawai, PegawaiModel pegawai);
	public PegawaiModel getPegawaiDetailById(long id);
	public int hitungUmur(LocalDate tanggalLahir, LocalDate today);
	
}
