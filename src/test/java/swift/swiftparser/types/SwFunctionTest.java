/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swift.swiftparser.types;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author naoki
 */
public class SwFunctionTest {
    
    public SwFunctionTest() {
    }

    @Test
    public void testSomeMethod() {
        assertThat(new SwFunction(SwString.of(), SwVoid.of()).toString(), is("(String)->Void"));
        assertThat(new SwFunction(SwVoid.of(), SwString.of()).toString(), is("()->String"));
        assertThat(new SwFunction(SwTuple.of(SwString.of(), SwInteger.of()), SwString.of()),
            is("(String, Int)->String"));
    }
    
}
