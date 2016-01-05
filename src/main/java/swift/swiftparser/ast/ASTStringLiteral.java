/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swift.swiftparser.ast;

import lombok.Value;
import swift.swiftparser.types.SwString;
import swift.swiftparser.types.SwiftType;

/**
 *
 * @author naoki
 */
@Value
public class ASTStringLiteral implements ASTExpression{
    String value;

    @Override
    public String toString() {
        return value;
    }


}
