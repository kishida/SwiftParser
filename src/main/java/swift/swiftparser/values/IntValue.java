/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swift.swiftparser.values;

import lombok.AllArgsConstructor;
import swift.swiftparser.types.SwInteger;
import swift.swiftparser.types.SwiftType;

/**
 *
 * @author naoki
 */
@AllArgsConstructor
public class IntValue implements SwiftValue{
    final int value;
    
    @Override
    public SwiftType getType() {
        return SwInteger.of();
    }

    public int getIntValue(){
        return value;
    }
    
    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value + "";
    }
    
    
}
