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
public abstract class SwiftType {
    public abstract String getName();
    public abstract SwiftType getParent();

    @Override
    public String toString() {
        return getName();
    }

}
