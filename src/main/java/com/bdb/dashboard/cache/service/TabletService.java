/**
 * 
 */
package com.bdb.dashboard.cache.service;

import java.util.List;

import com.bdb.dashboard.cache.model.Tablet;

/**
 * @author Jonathan Arroyo Reina
 * @date 04/12/2018
 *
 */
public interface TabletService {
	
	List<Tablet> getAllTablet();

	Tablet registrarTablet(Tablet t) throws Exception ;
	
	boolean actualizarTablet(Tablet t) throws Exception;
}
