package Classes;

public class Curso {
	
	private int     id;
	private String  nome;
	private String  descricao;
	private float   carga_horaria;
	private int     valor;
	private Boolean ativo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public float getCarga_horaria() {
		return carga_horaria;
	}
	public void setCarga_horaria(float carga_horaria) {
		this.carga_horaria = carga_horaria;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	
	

}
