/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swift.swiftparser.types;

import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 *
 * @author naoki
 */
@Value
public class SwFunction extends SwiftType{
    SwiftType param;
    SwiftType ret;
    
    @Override
    public String getName() {
        return "Func";
    }

    @Override
    public SwiftType getParent() {
        return SwAny.of();
    }

    @Override
    public String toString() {
        return String.format("%s->%s", 
                param instanceof SwTuple ? param : "(" + param + ")",
                ret.equals(SwVoid.of()) ? "Void" : ret);
    }
    
}
