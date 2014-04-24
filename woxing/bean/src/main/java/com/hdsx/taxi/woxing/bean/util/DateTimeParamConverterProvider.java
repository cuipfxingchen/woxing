package com.hdsx.taxi.woxing.bean.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.util.DateUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
public class DateTimeParamConverterProvider implements ParamConverterProvider {

    @Override
    public <T> ParamConverter<T> getConverter(Class<T> type, Type genericType, Annotation[] annotations) {
        if (type.equals(Date.class)) {
            return (ParamConverter<T>) new DateTimeParamConverter();
        } else {
            return null;
        }

    }

    private static class DateTimeParamConverter implements ParamConverter<Date> {
    	static final SimpleDateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        @Override
        public Date fromString(String value) {
            try {
                return dateformat.parse(value);
            } catch (IllegalArgumentException e) {
            	e.printStackTrace();
            } catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
        }

        @Override
        public String toString(Date value) {
            return value.toString();
        }

    }
}