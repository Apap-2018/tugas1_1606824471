package com.apap.tugas1.controller;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.service.InstansiService;
import com.apap.tugas1.service.JabatanService;
import com.apap.tugas1.service.PegawaiService;
import com.apap.tugas1.service.ProvinsiService;




/**
 * Pegawai Controller
 */
@Controller
public class PegawaiController {
	@Autowired
	private PegawaiService pegawaiService;
	
	@Autowired
	private JabatanService jabatanService;

	@Autowired
	private InstansiService instansiService;
	
	@Autowired
	private ProvinsiService provinsiService;
	
	@RequestMapping("/")
	private String home(Model model) {
		model.addAttribute("title", "Home");
		return "home";
	}
	

	/*
	 * Fitur 1: Menampilkan Data Pegawai Berdasarkan NIP
	 */
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
	
	
	/*
	 * Fitur 2: Menambahkan Data Pegawai di Suatu Instansi
	 */
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.GET)
	private String add(Model model) {

		List<ProvinsiModel> provinsiList = provinsiService.getListProvinsi();
		model.addAttribute("provinsiList", provinsiList);
		
		List<InstansiModel> instansiList = instansiService.getListInstansi();
		model.addAttribute("instansiList", instansiList);
		
		List<JabatanModel> jabatanList = jabatanService.getListJabatan();
		model.addAttribute("jabatanList", jabatanList);
		
		
		PegawaiModel newPegawai = new PegawaiModel();
		model.addAttribute("pegawai", newPegawai);
		
		model.addAttribute("title", "Tambah Pegawai");
		return "tambah-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST)
	private String addPegawaiSubmit(@ModelAttribute PegawaiModel pegawai, Model model) {
		pegawaiService.generateNip(pegawai);
		pegawaiService.addPegawai(pegawai);
		
		model.addAttribute("nip", pegawai.getNip());
		model.addAttribute("title", "Sukses!");
		return "tambah-pegawai-sukses";
	}
	
	
	
	
	
	
	
	
	
}
