/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swift.swiftparser.ast;

import lombok.Value;

/**
 *
 * @author naoki
 */
@Value
public class ASTBinOp implements ASTExpression{
    ASTExpression left;
    ASTExpression right;
    String op;
}
