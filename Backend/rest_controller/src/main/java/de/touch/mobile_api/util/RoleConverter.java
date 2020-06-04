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

import de.touch.mobile_api.config.Constants.Role;

/**
 * @author Eric G. Werner <gruschtelapps@gmail.com>
 */
@Converter
public class RoleConverter implements AttributeConverter<Role, Character>{

    private static final Logger log = LoggerFactory.getLogger(RoleConverter.class);

    
    @Override
    public Character convertToDatabaseColumn(Role attribute) {
        
        if(attribute == null){
            return null;
        }
        
        log.info("Converted role to: " + attribute.getDatabaseValue());
        return attribute.getDatabaseValue();
    }

    @Override
    public Role convertToEntityAttribute(Character dbData) {

        return Arrays.asList(Role.values())
                .stream()
                .filter(role -> role.getDatabaseValue() == dbData)
                .findFirst()
                .get();
    }

}
