package br.com.foursales.model.candidato;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.foursales.model.cartao.Cartao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_candidato")
public class Candidato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_candidato", nullable = false)
	private Integer id;

	@Column(name = "nm_candidato", nullable = false)
	private String nome;

	@Column(name = "nu_cpf", nullable = false)
	private String cpf;

	@Column(name = "ds_telefone")
	private String telefone;

	@Column(name = "ds_endereco")
	private String endereco;

	@Column(name = "ds_bairro")
	private String bairro;

	@Column(name = "ds_cidade")
	private String cidade;

	@Column(name = "ds_estado")
	private String estado;

//	@ManyToMany(targetEntity = Cartao.class, fetch = FetchType.LAZY)
//	@JoinTable(name = "tb_candidato_cartao", uniqueConstraints = @UniqueConstraint(columnNames = { "id_candidato",
//			"id_cartao" }), joinColumns = @JoinColumn(name = "id_candidato", nullable = false), inverseJoinColumns = @JoinColumn(name = "id_cartao", nullable = false))
	@OneToMany(targetEntity = Cartao.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "tb_candidato_cartao", joinColumns = @JoinColumn(name = "id_candidato", nullable = false), inverseJoinColumns = @JoinColumn(name = "id_cartao", nullable = false))
	private List<Cartao> cartoes;

}
