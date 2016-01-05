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
public class SwInteger extends SwiftType {
    private SwInteger() {
    }

    private static SwInteger instance;

    public static SwInteger of(){
        return instance == null ? instance = new SwInteger() : instance;
    }

    @Override
    public String getName() {
        return "Int";
    }
    
    @Override
    public SwiftType getParent() {
        return SwAny.of();
    }

    @Override
    public boolean equals(Object obj) {
        return obj == null ? false : obj instanceof SwInteger;
    }
    
}
