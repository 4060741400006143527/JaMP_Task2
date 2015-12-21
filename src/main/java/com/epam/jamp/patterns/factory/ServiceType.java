package com.epam.jamp.patterns.factory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum ServiceType implements FactoryProducer {

    DB("Db") {
        @Override
        public ServiceFactory getServiceFactory() {
            return new DBServiceFactory();
        }
    },
    FILE("File") {
        @Override
        public ServiceFactory getServiceFactory() {
            return new FileServiceFactory();
        }
    };

    private static final Map<String, ServiceType> serviceTypeMap = Collections.unmodifiableMap(initializeServiceTypeMap());

    private final String serviceNumber;

    private ServiceType(String serviceNumber) {
        this.serviceNumber = serviceNumber;
    }

    public static ServiceType getByServiceNumber(String serviceNumber) {
        ServiceType serviceType = serviceTypeMap.get(serviceNumber);
        return serviceType;
    }

    private static Map<String, ServiceType> initializeServiceTypeMap() {
        Map<String, ServiceType> serviceTypeMap = new HashMap<String, ServiceType>();
        for (ServiceType type : ServiceType.values()) {
            serviceTypeMap.put(type.serviceNumber, type);
        }
        return serviceTypeMap;
    }

    public String getServiceNumber() {
        return serviceNumber;
    }
}
