package com.linepro.modellbahn.persistence.util;

import java.util.Collection;

import org.hibernate.collection.internal.PersistentSet;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.util.CollectionUtils;

/**
 * A slight re-work on Hibernate.isInitialized, because we care about null and empty.
 * @author JohnG
 */
public class ProxyUtils {

    public static boolean isAvailable(Object source) {
        if (source instanceof Collection) {
            if (source instanceof PersistentSet) {
                return ((PersistentSet) source).wasInitialized();
            }

            return !CollectionUtils.isEmpty((Collection<?>) source);
        }

        if (source instanceof HibernateProxy) {
            return !((HibernateProxy) source).getHibernateLazyInitializer().isUninitialized();
        }

        return source != null;
    }
}
