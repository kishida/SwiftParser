/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swift.swiftparser.values;

import lombok.AllArgsConstructor;
import swift.swiftparser.types.SwString;
import swift.swiftparser.types.SwiftType;

/**
 *
 * @author naoki
 */
@AllArgsConstructor
public class StringValue implements SwiftValue{

    final String value;
    
    @Override
    public SwiftType getType() {
        return SwString.of();
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "\"" + value + "\"";
    }
    
}
