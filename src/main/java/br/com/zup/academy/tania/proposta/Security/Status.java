package br.com.zup.academy.tania.proposta.Security;

public class Status extends Object {

	public String coderr;
	public String descerr;
	
	public String getCoderr() {
		return coderr;
	}
	public void setCoderr(String coderr) {
		this.coderr = coderr;
	}

	public String getDescerr() {
		return descerr;
	}
	public void setDescerr(String descerr) {
		this.descerr = descerr;
	}

	public Status(String coderr) {
		super();
		this.coderr = coderr;
	}

	public Status(String coderr, String descerr) {
		super();
		this.coderr = coderr;
		this.descerr = descerr;
	}


/*	
	public static final Status(UNKNOWN, "Status indicando que o componente ou subsistema está em um estado desconhecido.") {
		
	}
	public static final Status(UP,"Status indicando que o componente ou subsistema está funcionando conforme o esperado.") {
		
	}
	public static final Status(DOWN,"Status indicando que o componente ou subsistema sofreu uma falha inesperada.") {
		
	}
	public static final Status(OUT_OF_SERVICE,"Status indicando que o componente ou subsistema foi retirado de serviço e não deve ser usado.") {
		
	}
	*/
	public boolean equals(Object obj) {
		return false;
		
	}
	
	public int hashCode() {
		return 0;
		
	}
		
	public  String  toString () {
		return coderr;
		
	}
}
