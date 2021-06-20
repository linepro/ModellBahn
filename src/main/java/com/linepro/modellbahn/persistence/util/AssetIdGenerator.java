package com.linepro.modellbahn.persistence.util;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.math.BigInteger;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.encoder.org.apache.commons.lang3.StringUtils;

@Component(PREFIX + "AssetIdGenerator")
@RequiredArgsConstructor
@Slf4j
public class AssetIdGenerator {

    private static final String QUERY = "SELECT INVENTORY_ID.NEXTVAL FROM DUAL";

    private final EntityManager entityManager;

    public String getNextAssetId() {
        try {
            BigInteger seq = (BigInteger) entityManager.createNativeQuery(QUERY).getSingleResult();

            return StringUtils.leftPad(seq.toString(36).toUpperCase(), 5, '0');
        } catch (Exception e) {
            log.error("Error getting assetId: {}", e.getMessage(), e);
        }

        return null;
    }
}
