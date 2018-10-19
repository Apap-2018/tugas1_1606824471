package com.apap.tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.repository.JabatanDb;

@Service
@Transactional
public class JabatanServiceImpl implements JabatanService {
	@Autowired
	private JabatanDb jabatanDb;
	
	@Override
	public List<JabatanModel> getListJabatan() {
		// TODO Auto-generated method stub
		return jabatanDb.findAll();
	}
	
	@Override
	public JabatanModel addJabatan(JabatanModel jabatan) {
		return jabatanDb.save(jabatan);
	}
	

}
