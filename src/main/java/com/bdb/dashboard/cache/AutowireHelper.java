/**
 * 
 */
package com.bdb.dashboard.cache;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Jonathan Arroyo Reina
 * @date 5/12/2018
 * @description AutowireHelper.java
 */
public class AutowireHelper implements ApplicationContextAware {

	private static final AutowireHelper INSTANCE = new AutowireHelper();
	private static ApplicationContext applicationContext;

	private AutowireHelper() {
	}

	/**
	 * Tries to autowire the specified instance of the class if one of the specified
	 * beans which need to be autowired are null.
	 *
	 * @param classToAutowire
	 *            the instance of the class which holds @Autowire annotations
	 * @param beansToAutowireInClass
	 *            the beans which have the @Autowire annotation in the specified
	 *            {#classToAutowire}
	 */
	public static void autowire(Object classToAutowire, Object... beansToAutowireInClass) {
		for (Object bean : beansToAutowireInClass) {
			if (bean == null) {
				applicationContext.getAutowireCapableBeanFactory().autowireBean(classToAutowire);
			}
		}
	}

	@Override
	public void setApplicationContext(final ApplicationContext applicationContext) {
		AutowireHelper.applicationContext = applicationContext;
	}

	/**
	 * @return the singleton instance.
	 */
	public static AutowireHelper getInstance() {
		return INSTANCE;
	}


}
