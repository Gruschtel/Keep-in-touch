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

import de.touch.mobile_api.config.Constants.Gender;

/**
 * @author Eric G. Werner <gruschtelapps@gmail.com>
 */
@Converter
public class GenderConverter implements AttributeConverter<Gender, Character> {
    
    private static final Logger log = LoggerFactory.getLogger(GenderConverter.class);


    @Override
    public Character convertToDatabaseColumn(Gender attribute) {
        log.info("Converted gender to: " + attribute.getDatabaseValue());
        return attribute.getDatabaseValue();
    }

    @Override
    public Gender convertToEntityAttribute(Character dbData) {
        return Arrays.asList(Gender.values())
                .stream()
                .filter(gender -> gender.getDatabaseValue() == dbData)
                .findFirst()
                .get();
    }

}
