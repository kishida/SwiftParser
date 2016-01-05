/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swift.swiftparser.types;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author naoki
 */
public class SwTupleTest {
    
    public SwTupleTest() {
    }

    @Test
    public void testSomeMethod() {
        System.out.println(SwVoid.of().equals(SwTuple.of()));
        System.out.println(SwTuple.of(SwString.of()).equals(SwTuple.of(SwString.of())));
        System.out.println(SwTuple.of(SwInteger.of()).equals(SwTuple.of(SwInteger.of())));
        System.out.println(SwTuple.of(SwString.of()).equals(SwTuple.of(SwInteger.of())));
        System.out.println(SwTuple.of(SwInteger.of()));
        System.out.println(SwTuple.of(SwInteger.of(), SwString.of()));
    }
    
}
