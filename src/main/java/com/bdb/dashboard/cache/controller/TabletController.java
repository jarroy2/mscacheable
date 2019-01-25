package com.bdb.dashboard.cache.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bdb.dashboard.cache.ProxyCache;
import com.bdb.dashboard.cache.model.Secuencia.Sequences;
import com.bdb.dashboard.cache.model.Tablet;
import com.bdb.dashboard.cache.response.GeneralResponse;
import com.bdb.dashboard.cache.service.SecuenciaService;
import com.bdb.dashboard.cache.service.TabletService;

/**
 * @author Jonathan Arroyo Reina
 * @date 4/12/2018
 * @description Controlador del serivicio Rest de la entidad Tablet
 */

@RestController
@RequestMapping("/tablet")
public class TabletController {

	public static final Logger logger = LoggerFactory.getLogger(TabletController.class);

	/**
	 * Atributo de reopositorio con la lógica de consulta de LDAP agregado por
	 * inyección de dependencia.
	 */
	@Autowired
	private TabletService tabletService;
	
	@Autowired
	private SecuenciaService secuenciaService;
	
	@Autowired
	private ProxyCache proxyCache;

	/**
	 * Método que consulta las tablets de todas las oficinas registradas como
	 * parámetro
	 * 
	 * @param ninguno
	 * @return Array JSON con los objetos que contienen la información de las
	 *         tablets que se encuentran registradas
	 */
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<List<Tablet>> getAllTablet() {
		logger.info("inicio Cargando lista de tablet");
		
		List<Tablet> tablets = proxyCache.getAllTablet();
				
		//List<Tablet> tablets = tabletService.getAllTablet();
		if (tablets == null || tablets.isEmpty()) {
			return new ResponseEntity<List<Tablet>>(HttpStatus.NO_CONTENT);
		}
		
		logger.info("fin Cargando lista de tablet");
		return new ResponseEntity<List<Tablet>>(tablets, HttpStatus.OK);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<GeneralResponse<Tablet>> registarTablet(@RequestBody Tablet tablet) {
		logger.info("inicio insertar tablet: " + tablet.getImei());
		
		GeneralResponse<Tablet> response = new GeneralResponse<>();
		HttpStatus status = null;
		
		try {
			tablet.setId(secuenciaService.getNextValue(Sequences.TABLET.getId()));
			tablet = tabletService.registrarTablet(tablet);
			response.setSuccess(true);
			response.setData(tablet);			
			status = HttpStatus.OK;
			
			logger.info("fin insertar tablet");
			
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			response.setSuccess(false);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			
		}
		
		return new ResponseEntity<>(response, status);
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<GeneralResponse<Tablet>> actualizarTablet(@RequestBody Tablet tablet) {
		logger.info("inicio actualizar tablet: " + tablet.getImei());
		
		GeneralResponse<Tablet> response = new GeneralResponse<>();
		HttpStatus status = null;
		
		try {
			tabletService.actualizarTablet(tablet);
			response.setSuccess(true);
			response.setData(tablet);			
			status = HttpStatus.OK;
			
			logger.info("fin actualizar tablet");
			
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			response.setSuccess(false);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(response, status);
	}

	

}
