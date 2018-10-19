package com.apap.tugas1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.service.JabatanService;

/**
 * Jabatan Controller
 */
@Controller
public class JabatanController {
	
	@Autowired
	private JabatanService jabatanService;
	
	/*
	 * Fitur 5: Menambahkan Jabatan
	 */
	@RequestMapping(value = "/jabatan/tambah", method = RequestMethod.GET)
	private String add(Model model) {
		JabatanModel newJabatan = new JabatanModel();
		model.addAttribute("jabatan", newJabatan);
		
		model.addAttribute("title", "Tambah Jabatan");
		return "tambah-jabatan";
	}
	
	@RequestMapping(value = "/jabatan/tambah", method = RequestMethod.POST)
	private String addJabatanSubmit(@ModelAttribute JabatanModel jabatan, Model model) {
		jabatanService.addJabatan(jabatan);
		
		model.addAttribute("title", "Sukses!");
		return "tambah-jabatan";
	}
	
	
	/*
	 * Fitur 6: Menampilkan data suatu jabatan
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	private String view(Model model) {
		
		List<JabatanModel> jabatanList = jabatanService.getListJabatan();
		model.addAttribute("jabatanList", jabatanList);

		model.addAttribute("title", "Detail Jabatan");
		return "home";
	}
	
	@RequestMapping(value="/jabatan/view")
	private String view(@RequestParam(value="jabatan") long id, Model model) {
		JabatanModel jabatan = jabatanService.getJabatanDetailById(id);
		model.addAttribute("jabatan", jabatan);
		model.addAttribute("title", "view jabatan");
		return "view-jabatan";
	}
	
	/*
	 * Fitur 7: Mengubah data jabatan
	 */
	@RequestMapping(value = "/jabatan/ubah", method = RequestMethod.GET)
	private String updateJabatan(@RequestParam(value = "jabatanId") long id, Model model) {
		JabatanModel jabatan =  jabatanService.getJabatanDetailById(id);
		model.addAttribute("jabatan", jabatan);
		return "update-jabatan";
	}
	
	@RequestMapping(value = "/jabatan/ubah", method = RequestMethod.POST)
	private String updateDealerSubmit(@RequestParam(value = "jabatanId") long id, @ModelAttribute Optional<JabatanModel> jabatan, Model model) {
		jabatanService.updateJabatan(id, jabatan.get());
		model.addAttribute("id", id);
		return "update-jabatan-sukses";
	}
	
	/*
	 * Fitur 8: Menghapus jabatan
	 */
	@RequestMapping(value = "/jabatan/hapus/{id}", method = RequestMethod.GET)
	private String deleteJabatan(@PathVariable(value = "id") long id, Model model) {
		jabatanService.deleteJabatan(id);
		return "delete-sukses";
	}

}
