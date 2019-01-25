/**
 * 
 */
package com.bdb.dashboard.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * @author programador
 * @date 5/12/2018
 * @description Configuracion del Mongo DB
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.bdb.dashboard.cache.repository")
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "com.bdb.dashboard.cache.controller")
//@EnableWebMvc
public class MongoConfig extends AbstractMongoConfiguration {

	private final Logger log = LoggerFactory.getLogger(MongoConfig.class);

	/*@Value("${spring.data.mongodb.host}")
	private String host;
	@Value("${spring.data.mongodb.port}")
	private Integer port;
	@Value("${spring.data.mongodb.username}")
	private String username;
	
	@Value("${spring.data.mongodb.password}")
	private String password;*/
	@Value("${spring.data.mongodb.uri}")
	private String mongoURI;

	@Value("${spring.data.mongodb.database}")
	private String database;
	
	@Autowired
	private Environment env;

	@Override
	public MongoClient mongoClient() {
		//log.info("Conectando a MongoDb host:" + host + ", username:" + username + ", database:" + database
			//	+ ", password:" + password);
		log.info("Conectando a Mongo URI host:" + mongoURI);

		// return new MongoClient(Collections.singletonList(new ServerAddress(host,
		// port)), Collections
		// .singletonList(MongoCredential.createCredential(username, database,
		// password.toCharArray())));

		// return new MongoClient(host, port);
		MongoClientURI uri = new MongoClientURI(mongoURI);
		return new MongoClient(uri);
	}

	@Override
	protected String getDatabaseName() {
		return database;
	}

	@Override
	public MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory(), mappingMongoConverter());
		return mongoTemplate;

	}

	@Override
	protected String getMappingBasePackage() {
		return "com.bdg.dashboard.model";
	}

}
