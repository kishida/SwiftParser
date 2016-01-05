/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swift.swiftparser.types;

/**
 *
 * @author naoki
 */
public class SwString extends SwiftType {

    private SwString() {
    }

    private static SwString instance;

    public static SwString of(){
        return instance == null ? instance = new SwString() : instance;
    }

    @Override
    public String getName() {
        return "String";
    }

    @Override
    public SwiftType getParent() {
        return SwAny.of();
    }

    @Override
    public boolean equals(Object obj) {
        return obj == null ? false : obj instanceof SwString;
    }
    
}
