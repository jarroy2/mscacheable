/**
 * 
 */
package com.bdb.dashboard.cache.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdb.dashboard.cache.model.Tablet;
import com.bdb.dashboard.cache.repository.TabletRepository;

/**
 * @author Jonathan Arroyo Reina
 * @date 04/12/2018
 * @description contiene la logica de la implementacion de los metodos de la interfaz
 */

@Service("tabletService")
public class TabletServiceImpl implements TabletService {

	@Autowired
	TabletRepository tabletRepository;

	@Override
	public Tablet registrarTablet(Tablet t) throws Exception {
		
		Tablet tablet = tabletRepository.findByImei(t.getImei());
		if (tablet == null) {
			t.setFechaSistema(new Date());
			t = tabletRepository.insert(t);
		}
			
		else
			throw new Exception("el imei de la table ya existe");
		return t;
	}

	@Override
	public boolean actualizarTablet(Tablet t) throws Exception {
		
		Tablet tablet = tabletRepository.findByImei(t.getImei());
		if (tablet == null)
			throw new Exception("el imei de la table no existe");
		else {
			tablet.setFechaSistema(new Date());
			tabletRepository.save(tablet);
		}
		return true;
	}

	@Override
	public List<Tablet> getAllTablet() {
		List<Tablet> lista = tabletRepository.findAll();
		return lista;
	}

}
