/**
 * 
 */
package com.bdb.dashboard.cache.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.bdb.dashboard.cache.model.Secuencia;
import com.bdb.dashboard.cache.model.Secuencia.Sequences;
import com.bdb.dashboard.cache.service.SecuenciaService;


/**
 * @author programador
 * @date 7/12/2018
 * @description SecuenciaServiceImpl.java
 */
@Service("SecuenciaService")
public class SecuenciaServiceImpl implements SecuenciaService {

	@Autowired
	private MongoOperations mongo;
	/**
	 * Returns the next formatted value for the {@link Sequences#INVOICE} sequence
	 *
	 * @return the next value for {@link Sequences#INVOICE}
	 */
	public final String getNextInvoiceId(){
		return getNextValue(Sequences.TABLET);
	}

	/**
	 * Returns the next formatted value available for the specified {@link Sequences}
	 *
	 * @param sequence for which to retrieve the next sequence value
	 * @return the next value for the {@link Sequences}
	 */
	private String getNextValue(Sequences sequence) {
		return sequence.format(getNextValue(sequence.getId()));
	}
	
	@Override
	public Long getNextValue(String sequenceId) {
		
		final Secuencia sequence = mongo.findAndModify(
				query(where("_id").is(sequenceId)),
				new Update().inc("value",1),
				options().returnNew(true).upsert(true),
				Secuencia.class);
		return sequence.getValue();
	}

}
