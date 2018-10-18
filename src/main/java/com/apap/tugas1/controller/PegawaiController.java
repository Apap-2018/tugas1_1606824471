package com.apap.tugas1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.service.PegawaiService;




/**
 * Pegawai Controller
 */
@Controller
public class PegawaiController {
	@Autowired
	private PegawaiService pegawaiService;
	
	
	@RequestMapping("/")
	private String home(Model model) {
		model.addAttribute("title", "Home");
		return "home";
	}
	
	@RequestMapping(value="/pegawai")
	private String view(@RequestParam(value="nip") String nip, Model model) {
		PegawaiModel pegawai = pegawaiService.getPegawaiDetailByNip(nip);
		model.addAttribute("nip", nip);
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("title", "view pegawai");
		
		List<JabatanModel> jabatanList = pegawai.getJabatanList();
		model.addAttribute("jabatanList", jabatanList);
		
		//  Gaji pokok yang dihitung adalah gaji pokok yang paling besar
		double gajiPokok = 0;
		for (JabatanModel jabatan : pegawai.getJabatanList()) {
			if (jabatan.getGaji_pokok() > gajiPokok) {
				gajiPokok = jabatan.getGaji_pokok();
			}
		}
		// Gaji pegawai dihitung berdasarkan Gaji = gaji pokok + (%tunjangan x gaji pokok)
		int gaji = (int) (gajiPokok + (pegawai.getInstansi().getProvinsi().getPresentase_tunjangan()/100 * gajiPokok));
		model.addAttribute("gaji", gaji);
		
		return "lihat-data-pegawai";
	}
}
