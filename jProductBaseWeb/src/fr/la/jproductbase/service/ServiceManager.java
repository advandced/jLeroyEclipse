
package fr.la.jproductbase.service;


import org.picocontainer.Characteristics;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.Parameter;
import org.picocontainer.behaviors.Caching;
import org.picocontainer.behaviors.Locking;

public class ServiceManager {
	
	private static ServiceManager serviceManagerInstance = null;
	private DefaultPicoContainer container = new DefaultPicoContainer(new Locking().wrap(new Caching()));
	private String applicationName = null;
	private String rootRessource = null;
	 
	public ServiceManager(String applicationName, String rootRessource) {
		this.applicationName = applicationName;
		this.rootRessource = rootRessource;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public String getRootRessource() {
		return rootRessource;
	}
	
	public static synchronized ServiceManager getServiceManagerInstance() {
		if (serviceManagerInstance == null)
			setServiceManagerInstance(new ServiceManager("",""));
		return serviceManagerInstance;
	}

	public static void setServiceManagerInstance(ServiceManager sm) {
		serviceManagerInstance = sm;
	}
	
	public void registerService(Object service) {
		container.as(Characteristics.LOCK).addComponent(service);
	}

	public void registerService(Object id,Class classImpl,Parameter ... parameters) {
		container.as(Characteristics.LOCK).addComponent(id, classImpl,parameters);
	}

	public void registerLocalService(Object id,Class classImpl,Parameter ... parameters) {
		container.as(Characteristics.LOCK).addComponent(getLocalName(id), classImpl,parameters);
	}

	public void registerServiceAsUnique(Object service) {
		container.as(Characteristics.LOCK, Characteristics.CACHE).addComponent(service);
	}

	public void registerServiceAsUnique(Object id, Class classImpl, Parameter ... parameters) {
		container.as(Characteristics.LOCK ,Characteristics.CACHE).addComponent(id, classImpl,parameters);
	}

	public void registerLocalServiceAsUnique(Object id) {
		container.as(Characteristics.LOCK, Characteristics.CACHE).addComponent(getLocalName(id));
	}

	public void registerLocalServiceAsUnique(Object id, Class classImpl, Parameter ... parameters) {
		container.as(Characteristics.LOCK, Characteristics.CACHE).addComponent(getLocalName(id), classImpl,parameters);
	}

	public void unregisterService(Object id) {
		container.removeComponent(id);
	}

	public void unregisterLocalService(Object id) {
		container.removeComponent(getLocalName(id));
	}

	public Object getService(Object id) {
		return (Object)container.getComponent(id);
	}

	public Object getLocalService(Object id) {
		return (Object)container.getComponent(getLocalName(id));
	}

	public void startServices() {
		container.start();
	}

	public void stopServices() {
		container.stop();
	}

	private String getLocalName(Object name) {
		return name.toString()+"Local";
	}
	
	public DefaultPicoContainer getContainer() {
		return container;
	}
}
