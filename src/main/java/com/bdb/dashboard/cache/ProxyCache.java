package com.bdb.dashboard.cache;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bdb.dashboard.cache.model.Tablet;

@Component
public class ProxyCache {

	@Autowired
	GestionCache gestionCache;
	
	
	public synchronized List<Tablet> getAllTablet() {
		return gestionCache.getAllTablet();
	}

}
