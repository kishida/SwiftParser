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
public class SwOptional extends SwiftType{

    @Override
    public String getName() {
        return "optional";
    }

    @Override
    public SwiftType getParent() {
        return SwAny.of();
    }

}
