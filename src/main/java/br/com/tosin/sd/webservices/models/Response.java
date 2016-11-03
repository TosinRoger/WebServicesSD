package br.com.tosin.sd.webservices.models;

/**
 * Classe que returna a mensagem de erro ou de aceitacao
 * @author tosin
 *
 */
public class Response {
	
	private String status;
	private String msg;

	public Response() {
	}

	/**
	 * Retorna Ok e uma mensagem
	 * @param string
	 * @return
	 */
	public static Response Ok(String string) {
		Response r = new Response();
		r.setStatus("OK");
		r.setMsg(string);
		return r;
	}

	/**
	 * Return ERROR e uma mensagem
	 * @param string
	 * @return
	 */
	public static Response Error(String string) {
		Response r = new Response();
		r.setStatus("ERROR");
		r.setMsg(string);
		return r;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
