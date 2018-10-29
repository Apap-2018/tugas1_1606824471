package com.apap.tugas1.controller;


import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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
		model.addAttribute("home", true);
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
		newPegawai.setJabatanList(new ArrayList<JabatanModel>());
		newPegawai.getJabatanList().add(new JabatanModel());
		model.addAttribute("pegawai", newPegawai);
		System.out.println("masuk add get");
		model.addAttribute("tambahpegawai", true);
		return "tambah-pegawai";
	}
	

	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST)
	private String addPegawaiSubmit(@ModelAttribute PegawaiModel pegawai, Model model) {
		pegawaiService.generateNip(pegawai);
		System.out.println("masuk add post "+pegawai.getNip());
		pegawaiService.addPegawai(pegawai);
		
		model.addAttribute("nip", pegawai.getNip());
		model.addAttribute("tambahpegawai", true);
		return "tambah-pegawai-sukses";
	}
	

	
	/*
	 * Fitur 3: Mengubah Data Pegawai
	 */
	@RequestMapping(value = "/pegawai/ubah", method = RequestMethod.GET)
	private String updateJabatan(@RequestParam(value = "pegawaiId") long id, Model model) {
		List<ProvinsiModel> provinsiList = provinsiService.getListProvinsi();
		model.addAttribute("provinsiList", provinsiList);
		
		List<InstansiModel> instansiList = instansiService.getListInstansi();
		model.addAttribute("instansiList", instansiList);
//		
		List<JabatanModel> jabatanList = jabatanService.getListJabatan();
		model.addAttribute("jabatanList", jabatanList);
		
		PegawaiModel pegawai =  pegawaiService.getPegawaiDetailById(id);
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("pegawai_prov", pegawai.getInstansi().getProvinsi().getNama());
		model.addAttribute("instansi", pegawai.getInstansi().getNama());
//		model.addAttribute("pegawaiJabList", pegawai.getJabatanList());
		model.addAttribute("jabatanList", jabatanService.getListJabatan());
		return "update-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/ubah", method = RequestMethod.POST)
	private String updatePegawaiSubmit(@RequestParam(value = "pegawaiId") long id, @ModelAttribute Optional<PegawaiModel> pegawai, Model model) {
		pegawaiService.updatePegawai(id, pegawai.get());
		model.addAttribute("id", id);
		return "update-pegawai-sukses";
	}
	
	
	/*
	 * Fitur 4
	 */
	
	@RequestMapping(value = "/pegawai/cari")
	private String cariPegawaiForm(Model model) {
		List<ProvinsiModel> provinsiList = provinsiService.getListProvinsi();
		model.addAttribute("provinsiList", provinsiList);
		
		List<JabatanModel> jabatanList = jabatanService.getListJabatan();
		model.addAttribute("jabatanList", jabatanList);
		
		List<InstansiModel> instansiList = instansiService.getListInstansi();
		model.addAttribute("instansiList", instansiList);
		
		model.addAttribute("caripegawai", true);
		return "cari-pegawai";
	}
	
	@RequestMapping(value="/pegawai/cari", method = RequestMethod.GET, params = {"idProvinsi","idInstansi","idJabatan"})
	private String cariPegawaiView(@RequestParam("idProvinsi") long idProvinsi,
						@RequestParam("idInstansi") long idInstansi,
						@RequestParam("idJabatan") long idJabatan,
						Model model) {
		
		List<ProvinsiModel> provinsiList = provinsiService.getListProvinsi();
		model.addAttribute("provinsiList", provinsiList);
		
		List<JabatanModel> jabatanList = jabatanService.getListJabatan();
		model.addAttribute("jabatanList", jabatanList);
		
		List<InstansiModel> instansiList = instansiService.getListInstansi();
		model.addAttribute("instansiList", instansiList);
		
		List<PegawaiModel> foundPegawaiList = new ArrayList<>();
		if (idProvinsi != 0 && idInstansi != 0 && idJabatan != 0) {
			ProvinsiModel provinsi = provinsiService.getProvinsiById(idProvinsi);
			for (InstansiModel i : provinsi.getInstansiList()) {
				if (i.getId() == idInstansi) {
					for(PegawaiModel p : i.getPegawaiInstansi()) {
						for(JabatanModel j : p.getJabatanList()) {
							if(j.getId() == idJabatan) {
								foundPegawaiList.add(p);
							}
						}
					}
				}
			}
		} else {
			//handle jika value id 0 
		}
		model.addAttribute("pegawaiList", foundPegawaiList);
		
		model.addAttribute("caripegawai", true);
		return "cari-pegawai";
	}
	
	
	/*
	 * Fitur 6 : Tertua Termuda
	 */
	@RequestMapping(value="/pegawai/tertua-termuda")
	private String viewTertuaTermuda(@RequestParam(value="instansi") long id, Model model) {
		InstansiModel instansi = instansiService.getInstansi(id);
		List <PegawaiModel> pegawaiList = instansi.getPegawaiInstansi();
		
		PegawaiModel tertua = new PegawaiModel();
		PegawaiModel termuda = new PegawaiModel();
		
		int umurTertua = 0;
		int umurTermuda = 0;
		
		for (PegawaiModel p : pegawaiList) {
			Date bday =  p.getTanggalLahir();
			LocalDate birthDate = bday.toLocalDate();
			LocalDate today = LocalDate.now();
			int umurP = pegawaiService.hitungUmur(birthDate, today);
			
			if (umurTertua!=0) {
				if (umurTertua < umurP) {
					umurTertua = umurP;
					tertua = p;
				}
				
			} else {
				umurTertua = umurP;
				tertua = p;
			}
			
			if (umurTermuda!=0) {
				if (umurTermuda > umurP) {
					umurTermuda = umurP;
					termuda = p;	
				}
			} else {
				umurTermuda = umurP;
				termuda = p;
			}
			
			
		}
		
		model.addAttribute("pegawaiTermuda", termuda);
		model.addAttribute("pegawaiTertua", tertua);
		
		return "tua-muda";
	}
	
	
	
	
	
	
	
	
	
}
