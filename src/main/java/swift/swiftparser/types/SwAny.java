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
public class SwAny extends SwiftType{
    private SwAny() {
    }

    private static SwAny instance;

    public static SwAny of(){
        return instance == null ? instance = new SwAny() : instance;
    }

    @Override
    public String getName() {
        return "Any";
    }

    @Override
    public SwiftType getParent() {
        return null;
    }
    
}
