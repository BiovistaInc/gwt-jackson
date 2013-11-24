/*
 * Copyright 2013 Nicolas Morel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.nmorel.gwtjackson.client.ser.bean;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

/**
 * Contains serialization informations about superclass
 *
 * @author Nicolas Morel
 */
public class SuperclassSerializationInfo<T> {

    /**
     * if we include type information in the result
     */
    private final boolean includeTypeInfo;

    /**
     * inclusion mechanism
     */
    private final As include;

    /**
     * name of the property containing information about the subtype
     */
    private final String propertyName;

    private final Map<Class<? extends T>, String> subtypeClassToInfo;

    private final Map<Class<? extends T>, SubtypeSerializer<? extends T>> subtypeClassToSerializer;

    public SuperclassSerializationInfo() {
        this( null, null, false );
    }

    public SuperclassSerializationInfo( As include, String propertyName ) {
        this( include, propertyName, true );
    }

    private SuperclassSerializationInfo( As include, String propertyName, boolean includeTypeInfo ) {
        this.includeTypeInfo = includeTypeInfo;
        this.include = include;
        this.propertyName = propertyName;
        if ( includeTypeInfo ) {
            this.subtypeClassToInfo = new HashMap<Class<? extends T>, String>();
        } else {
            this.subtypeClassToInfo = null;
        }
        this.subtypeClassToSerializer = new HashMap<Class<? extends T>, SubtypeSerializer<? extends T>>();
    }

    public <S extends T> SuperclassSerializationInfo<T> addSubtypeSerializer( SubtypeSerializer<S> subtypeSerializer, Class<S> clazz,
                                                                              String typeInfo ) {
        if ( includeTypeInfo ) {
            subtypeClassToInfo.put( clazz, typeInfo );
        }
        subtypeClassToSerializer.put( clazz, subtypeSerializer );
        return this;
    }

    public boolean isIncludeTypeInfo() {
        return includeTypeInfo;
    }

    public As getInclude() {
        return include;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public SubtypeSerializer<? extends T> getSerializer( Class aClass ) {
        return subtypeClassToSerializer.get( aClass );
    }

    public String getTypeInfo( Class aClass ) {
        return subtypeClassToInfo.get( aClass );
    }
}