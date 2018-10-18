package com.apap.tugas1.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "jabatan_pegawai")
public class JabatanPegawaiModel implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
//	@NotNull
//	@Size(max = 20)
//	@Column(name = "idInstansi", nullable = false)
//	private long id_pegawai;
//	
//	@NotNull
//	@Size(max = 20)
//	@Column(name = "idInstansi", nullable = false)
//	private long id_jabatan;
//	
//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinColumn(name = "pegawai_id", referencedColumnName = "id", nullable = false)
//	@OnDelete(action = OnDeleteAction.NO_ACTION)
//	@JsonIgnore
//	private JabatanModel pegawai;
//	
//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinColumn(name = "jabatan_id", referencedColumnName = "id", nullable = false)
//	@OnDelete(action = OnDeleteAction.NO_ACTION)
//	@JsonIgnore
//	private JabatanModel jabatan;
//	

}
