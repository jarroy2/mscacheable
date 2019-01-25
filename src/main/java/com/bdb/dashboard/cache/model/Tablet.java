/**
 * 
 */
package com.bdb.dashboard.cache.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Jonathan Arroyo Reina
 * @date 04/12/2018
 * @description Entidad que contiene los atributos de una tablet
 *
 */
@Document(collection = "tablet")
public class Tablet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	private String imei;
	private Date fechaSistema;
	//@Version Long version;
	
	public Tablet() {
		super();
	}
	
	public Tablet(Long id, String imei, Date fechaSistema) {
		super();
		this.id = id;
		this.imei = imei;
		this.fechaSistema = fechaSistema;
	}	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}


	public Date getFechaSistema() {
		return fechaSistema;
	}

	public void setFechaSistema(Date fechaSistema) {
		this.fechaSistema = fechaSistema;
	}

	@Override
	public String toString() {
		return "Tablet [id=" + id + ", imei=" + imei + ", fechaSistema=" + fechaSistema + "]";
	}

	
	
}
