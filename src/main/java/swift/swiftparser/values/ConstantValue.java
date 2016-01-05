/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swift.swiftparser.values;

import lombok.AllArgsConstructor;
import swift.swiftparser.types.SwiftType;

/**
 *
 * @author naoki
 */
@AllArgsConstructor
public class ConstantValue implements SwiftValue{
    String name;
    SwiftType type;
    SwiftValue value;

    public String getName() {
        return name;
    }
    
    @Override
    public SwiftType getType() {
        return type;
    }

    @Override
    public SwiftValue getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("%s:%s=%s", name, type, value);
    }

}
