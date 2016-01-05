/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swift.swiftparser.values;

import swift.swiftparser.types.SwiftType;

/**
 *
 * @author naoki
 */
public interface SwiftValue {
    SwiftType getType();
    Object getValue();
}
