package com.apap.tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tugas1.model.JabatanModel;
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

	@Override
	public JabatanModel getJabatanDetailById(long id) {
		return jabatanDb.findById(id);
	}

	@Override
	public void updateJabatan(long idJabatan, JabatanModel jabatan) {
		JabatanModel updatedJabatan = jabatanDb.getOne(idJabatan);
		updatedJabatan.setNama(jabatan.getNama());
		updatedJabatan.setDeskripsi(jabatan.getDeskripsi());
		updatedJabatan.setGaji_pokok(jabatan.getGaji_pokok());
		jabatanDb.save(updatedJabatan);
	}
	
	@Override
	public void deleteJabatan(long id) {
		jabatanDb.delete(jabatanDb.findById(id));
	}

	

}
