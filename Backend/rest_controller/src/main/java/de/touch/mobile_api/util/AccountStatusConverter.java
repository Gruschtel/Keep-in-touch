/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.touch.mobile_api.util;

import java.util.Arrays;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.touch.mobile_api.config.Constants.AccountStatus;

/**
 * @author Eric G. Werner <gruschtelapps@gmail.com>
 */
@Converter
public class AccountStatusConverter implements AttributeConverter<AccountStatus, Character>{

    private static final Logger log = LoggerFactory.getLogger(RoleConverter.class);

    
    @Override
    public Character convertToDatabaseColumn(AccountStatus attribute) {
        
        if(attribute == null){
            return null;
        }
        
        log.info("Converted role to: " + attribute.getDatabaseValue());
        return attribute.getDatabaseValue();
    }

    @Override
    public AccountStatus convertToEntityAttribute(Character dbData) {

        return Arrays.asList(AccountStatus.values())
                .stream()
                .filter(role -> role.getDatabaseValue() == dbData)
                .findFirst()
                .get();
    }

}
