package com.nelioalves.cursomc.domain.enums;

public enum TipoCliente {

	PESSOAFISICA(1, "Pessoa física"), PESSOAJURIDICA(2, "Pessoa Jurídica");

	private int cod;
	private String descricao;

	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public static TipoCliente toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (TipoCliente c : TipoCliente.values()) {
			if (cod.equals(c.cod)) {
				return c;
			}
		}
		throw new IllegalArgumentException("Id inválido: "+cod);
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
