package com.github.nmorel.gwtjackson.client.advanced.jsontype;

import com.github.nmorel.gwtjackson.client.GwtJacksonTestCase;
import com.github.nmorel.gwtjackson.client.JsonDeserializationContext;
import com.github.nmorel.gwtjackson.client.ObjectMapper;
import com.github.nmorel.gwtjackson.shared.ObjectMapperTester;
import com.github.nmorel.gwtjackson.shared.advanced.jsontype.PolymorphismNoTypeInfoTester;
import com.github.nmorel.gwtjackson.shared.advanced.jsontype.PolymorphismNoTypeInfoTester.Employee;
import com.github.nmorel.gwtjackson.shared.advanced.jsontype.PolymorphismNoTypeInfoTester.Person;
import com.google.gwt.core.client.GWT;

/**
 * @author Nicolas Morel
 */
public class PolymorphismNoTypeInfoGwtTest extends GwtJacksonTestCase {

    public interface PolymorphismPersonMapper extends ObjectMapper<Person[]>, ObjectMapperTester<Person[]> {

        static PolymorphismPersonMapper INSTANCE = GWT.create( PolymorphismPersonMapper.class );
    }

    public interface PolymorphismEmployeeMapper extends ObjectMapper<Employee[]> {

        static PolymorphismEmployeeMapper INSTANCE = GWT.create( PolymorphismEmployeeMapper.class );
    }

    private PolymorphismNoTypeInfoTester tester = PolymorphismNoTypeInfoTester.INSTANCE;

    public void testSerialize() {
        tester.testSerialize( PolymorphismPersonMapper.INSTANCE );
    }

    public void testDeserializeNonInstantiableBean() {
        tester.testDeserializeNonInstantiableBean( PolymorphismPersonMapper.INSTANCE );
    }

    public void testDeserializeInstantiableBean() {
        tester.testDeserializeInstantiableBean( createMapper( PolymorphismEmployeeMapper.INSTANCE, new JsonDeserializationContext.Builder()
            .failOnUnknownProperties( false ).build(), newDefaultSerializationContext() ) );
    }
}