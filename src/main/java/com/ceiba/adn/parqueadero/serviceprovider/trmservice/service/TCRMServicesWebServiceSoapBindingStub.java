package com.ceiba.adn.parqueadero.serviceprovider.trmservice.service;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;

import com.ceiba.adn.parqueadero.serviceprovider.trmservice.response.Tcrm;
import com.ceiba.adn.parqueadero.serviceprovider.trmservice.response.TcrmResponse;

public class TCRMServicesWebServiceSoapBindingStub extends org.apache.axis.client.Stub
		implements TCRMServicesInterface {
	private List<Object> cachedSerClasses = new ArrayList<>();
	private List<Object> cachedSerQNames = new ArrayList<>();
	private List<Object> cachedSerFactories = new ArrayList<>();
	private List<Object> cachedDeserFactories = new ArrayList<>();
	
	private static final String URL_TCRM_RESPONSE = "http://action.trm.services.generic.action.superfinanciera.nexura.sc.com.co/"; 

	static org.apache.axis.description.OperationDesc[] operations;

	static {
		operations = new org.apache.axis.description.OperationDesc[1];
		initOperationDesc1();
	}

	private static void initOperationDesc1() {
		org.apache.axis.description.OperationDesc oper;
		org.apache.axis.description.ParameterDesc param;
		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("queryTCRM");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("", "tcrmQueryAssociatedDate"),
				org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class,
				false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName(
				URL_TCRM_RESPONSE, "tcrmResponse"));
		oper.setReturnClass(TcrmResponse.class);
		oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		operations[0] = oper;

	}

	public TCRMServicesWebServiceSoapBindingStub() throws org.apache.axis.AxisFault {
		this(null);
	}

	public TCRMServicesWebServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) {
		this(service);
		super.cachedEndpoint = endpointURL;
	}

	public TCRMServicesWebServiceSoapBindingStub(javax.xml.rpc.Service service){
		if (service == null) {
			super.service = new org.apache.axis.client.Service();
		} else {
			super.service = service;
		}
		((org.apache.axis.client.Service) super.service).setTypeMappingVersion("1.2");
		Class cls;
		javax.xml.namespace.QName qName;
		Class<BeanSerializerFactory> beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
		Class<BeanDeserializerFactory> beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
		qName = new javax.xml.namespace.QName(
				URL_TCRM_RESPONSE, "tcrm");
		cachedSerQNames.add(qName);
		cls = Tcrm.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
				URL_TCRM_RESPONSE, "tcrmResponse");
		cachedSerQNames.add(qName);
		cls = TcrmResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

	}
	
	private Call validateNullAndSet(Call call) {
		
		if (super.cachedUsername != null) {
			call.setUsername(super.cachedUsername);
		}
		if (super.cachedPassword != null) {
			call.setPassword(super.cachedPassword);
		}
		if (super.cachedEndpoint != null) {
			call.setTargetEndpointAddress(super.cachedEndpoint);
		}
		if (super.cachedTimeout != null) {
			call.setTimeout(super.cachedTimeout);
		}
		if (super.cachedPortName != null) {
			call.setPortName(super.cachedPortName);
		}
		
		return call;
	}

	protected org.apache.axis.client.Call createCall() throws RemoteException {
		try {
			org.apache.axis.client.Call call = super._createCall();
			
			if (super.maintainSessionSet) {
				call.setMaintainSession(super.maintainSession);
			}
			
			call = validateNullAndSet(call);
			
			Enumeration<Object> keys = super.cachedProperties.keys();
			while (keys.hasMoreElements()) {
				java.lang.String key = (java.lang.String) keys.nextElement();
				call.setProperty(key, super.cachedProperties.get(key));
			}
			// All the type mapping information is registered
			// when the first call is made.
			// The type mapping information is actually registered in
			// the TypeMappingRegistry of the service, which
			// is the reason why registration is only needed for the first call.
			synchronized (this) {
				if (firstCall()) {
					call = registerTypeMapping(call);
				}
			}
			return call;
		} catch (ServiceException e) {
			throw new org.apache.axis.AxisFault("Failure trying to get the Call object", e);
		}
	}
	
	private Call registerTypeMapping(Call call) {
		// must set encoding style before registering serializers
		call.setEncodingStyle(null);
		for (int i = 0; i < cachedSerFactories.size(); ++i) {
			Class cls = (Class) cachedSerClasses.get(i);
			javax.xml.namespace.QName qName = (javax.xml.namespace.QName) cachedSerQNames.get(i);
			java.lang.Object x = cachedSerFactories.get(i);
			if (x instanceof Class) {
				Class sf = (Class) cachedSerFactories.get(i);
				Class df = (Class) cachedDeserFactories.get(i);
				call.registerTypeMapping(cls, qName, sf, df, false);
			} else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
				org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory) cachedSerFactories
						.get(i);
				org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory) cachedDeserFactories
						.get(i);
				call.registerTypeMapping(cls, qName, sf, df, false);
			}
		}
		
		return call;
	}

	public TcrmResponse queryTCRM(java.util.Calendar tcrmQueryAssociatedDate) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call call = createCall();
		call.setOperation(operations[0]);
		call.setUseSOAPAction(true);
		call.setSOAPActionURI("");
		call.setEncodingStyle(null);
		call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		call.setOperationName(new javax.xml.namespace.QName(
				URL_TCRM_RESPONSE, "queryTCRM"));

		setRequestHeaders(call);
		setAttachments(call);

			java.lang.Object resp = call.invoke(new java.lang.Object[] { tcrmQueryAssociatedDate });

			if (resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) resp;
			} else {
				extractAttachments(call);
					return (TcrmResponse) resp;
			}
	}

}
