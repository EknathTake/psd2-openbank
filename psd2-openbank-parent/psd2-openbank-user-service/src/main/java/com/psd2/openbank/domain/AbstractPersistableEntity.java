package com.psd2.openbank.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/*@Setter
@Getter*/
@MappedSuperclass
public abstract class AbstractPersistableEntity<ID> implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private ID id;

	@Version
	private Long version;

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

}
