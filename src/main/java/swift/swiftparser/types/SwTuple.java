/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swift.swiftparser.types;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author naoki
 */
public class SwTuple extends SwiftType {

    List<SwiftType> types;

    SwTuple(List<SwiftType> types) {
        this.types = types;
    }
    
    public static SwiftType of(SwiftType... types){
        switch(types.length){
            case 0:
                return SwVoid.of();
            case 1:
                return types[0];
            default:
                return new SwTuple(Arrays.asList(types));
        }
    }
    
    @Override
    public String getName() {
        return "Tuple";
    }
    
    @Override
    public SwiftType getParent() {
        return SwAny.of();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof SwTuple)) return false;
        return ((SwTuple)obj).types.equals(types);
    }

    @Override
    public String toString() {
        return "(" + types.stream().map(t -> t.toString()).collect(Collectors.joining(", ")) + ")";
    }

}
