package com.epam.jamp.patterns.factory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum ServiceType implements ServiceFactory {

    DB("Db") {
        @Override
        public AbstractServiceFactory getServiceFactory() {
            return new DBServiceFactory();
        }
    },
    FILE("File") {
        @Override
        public AbstractServiceFactory getServiceFactory() {
            return new FileServiceFactory();
        }
    };

    private static final Map<String, ServiceType> serviceTypeMap = Collections.unmodifiableMap(initializeServiceTypeMap());

    private String serviceNumber;

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
