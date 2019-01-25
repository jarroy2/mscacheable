package com.bdb.dashboard.cache;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bdb.dashboard.cache.model.Tablet;
import com.bdb.dashboard.cache.service.TabletService;

@Component
public class GestionCache {

	Logger log = LoggerFactory.getLogger(GestionCache.class);

	@Autowired
	TabletService tabletService;

	private List<Tablet> response;

	@Autowired
	private CacheManager cacheManager;

	@Cacheable("tablets")
	public List<Tablet> getAllTablet() {
		return this.response;
	}

	@CacheEvict(value = "tablets", allEntries = true)
	private void clearCacheTablet() {
		log.info("Limpiando la Cache de las tablets");
		Cache cache = cacheManager.getCache("tablets");
		if (cache != null)
			cache.clear();
	}

	@EventListener(ApplicationReadyEvent.class)
	@Scheduled(fixedDelay = 80000)
	public void init() {
		log.info("Generando data para el cache de tablets");
		this.response = tabletService.getAllTablet();
		if (this.response != null && !this.response.isEmpty()) {
			clearCacheTablet();
		}
	}
}
