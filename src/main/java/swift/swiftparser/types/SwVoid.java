/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swift.swiftparser.types;

import java.util.Collections;

/**
 *
 * @author naoki
 */
public class SwVoid extends SwTuple{

    private static SwVoid instance;
    private SwVoid(){
        super(Collections.emptyList());
    }
    
    public static SwVoid of(){
        return instance == null ? instance = new SwVoid() : instance;
    }
    
    @Override
    public String getName() {
        return "Void";
    }
    
}
