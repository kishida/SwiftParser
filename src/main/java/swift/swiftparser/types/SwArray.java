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
public class SwArray extends SwiftType {

    @Override
    public String getName() {
        return "array";
    }

    @Override
    public SwiftType getParent() {
        return SwAny.of();
    }
    
}
