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
public class SwBoolean extends SwiftType{
    private SwBoolean() {
    }

    private static SwBoolean instance;

    public static SwBoolean of(){
        return instance == null ? instance = new SwBoolean() : instance;
    }

    @Override
    public String getName() {
        return "Bool";
    }
    
    @Override
    public SwiftType getParent() {
        return SwAny.of();
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj == null ? false : obj instanceof SwBoolean;
    }
    
}
