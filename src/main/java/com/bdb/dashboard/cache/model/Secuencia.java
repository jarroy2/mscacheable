/**
 * 
 */
package com.bdb.dashboard.cache.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Jonathan Arroyo Reina
 * @date 7/12/2018
 * @description Secuencia.java
 */

@Document(collection = "secuencia")
public class Secuencia {
	
	@Id
	private String id;
	private Long value;
	
	public Secuencia() {
		super();
	}

	public Secuencia(String id, Long value) {
		super();
		this.id = id;
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}	
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Secuencia sequence = (Secuencia) o;

		if (id != null ? !id.equals(sequence.id) : sequence.id != null) return false;
		return value != null ? value.equals(sequence.value) : sequence.value == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (value != null ? value.hashCode() : 0);
		return result;
	}
	
	public enum Sequences {

		TABLET("tablet", "TABLET");

		private final String id;
		private final String prefix;

		Sequences(String id, String prefix) {
			this.id = id;
			this.prefix = prefix;
		}

		public String getId() {
			return id;
		}

		public String getPrefix() {
			return prefix;
		}

		public String format(Long value) {
			return String.format("%s%07d", getPrefix(), value);
		}
	}
}



