package br.com.desafio.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@Lob
	private String email;
	
	@Column
	@Lob
	private String nome;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DigitoUnico> listaResultadoDigitoUnico;
	
	@OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	private UsuarioChavePublica usuarioChavePublica;
	
	public Usuario() {
		super();
	}

	public Usuario(String email, String nome, List<DigitoUnico> listaResultadoDigitoUnico) {
		super();
		this.email = email;
		this.nome = nome;
		this.listaResultadoDigitoUnico = listaResultadoDigitoUnico;
	}
	
	public Usuario(String email, String nome) {
		super();
		this.email = email;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<DigitoUnico> getListaResultadoDigitoUnico() {
		return listaResultadoDigitoUnico;
	}

	public void setListaResultadoDigitoUnico(List<DigitoUnico> listaResultadoDigitoUnico) {
		this.listaResultadoDigitoUnico = listaResultadoDigitoUnico;
	}
	
	public UsuarioChavePublica getUsuarioChavePublica() {
		return usuarioChavePublica;
	}

	public void setUsuarioChavePublica(UsuarioChavePublica usuarioChavePublica) {
		this.usuarioChavePublica = usuarioChavePublica;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}