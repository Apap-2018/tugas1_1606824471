package com.apap.tugas1.service;

import java.text.SimpleDateFormat;
import java.util.List;

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
	public PegawaiModel addPegawai(PegawaiModel pegawai) {
		return pegawaiDb.save(pegawai);
	}


	@Override
	public PegawaiModel getPegawaiDetailByNip(String nip) {
		return pegawaiDb.findByNip(nip);
	}


	@Override
	public PegawaiModel getPegawai(Long id) {
		return null;
	}


	@Override
	public void generateNip(PegawaiModel pegawai) {
		SimpleDateFormat formatDate = new SimpleDateFormat("ddMMyy");
		String newDateFormat = formatDate.format(pegawai.getTanggalLahir());
		
		int urutan = 1;
		List<PegawaiModel> listPegawai = pegawaiDb.findByInstansi(pegawai.getInstansi());
		for (PegawaiModel oPegawai : listPegawai) {
			if (pegawai.getTanggalLahir().equals(oPegawai.getTanggalLahir()))
				urutan++;
		}
		
		String strUrutan = "";
		if (urutan<10) {
			strUrutan = "0" + urutan;
		}
		
		String nip = pegawai.getInstansi().getId()+""+newDateFormat+
					pegawai.getTahunMasuk()+""+strUrutan;
		pegawai.setNip(nip);
		
	}

}
