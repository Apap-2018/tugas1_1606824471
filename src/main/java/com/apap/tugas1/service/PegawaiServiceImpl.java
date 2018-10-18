package com.apap.tugas1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.repository.PegawaiDb;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService{
	@Autowired
	private PegawaiDb pegawaiDb;
	
	@Override
	public void addPegawai(PegawaiModel pegawai) {
		pegawaiDb.save(pegawai);
	}


	@Override
	public PegawaiModel getPegawaiDetailByNip(String nip) {
		return pegawaiDb.findByNip(nip);
	}


	@Override
	public PegawaiModel getPegawai(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
