/**
 * 
 */
package com.bdb.dashboard.cache.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bdb.dashboard.cache.model.Tablet;

/**
 * @author Jonathan Arroyo Reina
 * @date 4/12/2018
 * @description Repositorio para la conexion del modelo de negocio de una tablet
 */
@Repository("tabletRepository")
public interface TabletRepository extends MongoRepository<Tablet, Long>
{

	List<Tablet> findAll();

	Tablet findByImei(String imei);

}
