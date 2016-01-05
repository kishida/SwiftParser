/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swift.swiftparser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static swift.swiftparser.ObjectPatternMatch.*;
import swift.swiftparser.ast.AST;
import swift.swiftparser.ast.ASTBinOp;
import swift.swiftparser.ast.ASTDecConst;
import swift.swiftparser.ast.ASTExpIdent;
import swift.swiftparser.ast.ASTIdentifier;
import swift.swiftparser.ast.ASTIntLiteral;
import swift.swiftparser.ast.ASTPatIdentifier;
import swift.swiftparser.ast.ASTPattern;
import swift.swiftparser.ast.ASTStringLiteral;
import swift.swiftparser.ast.ASTType;
import swift.swiftparser.exception.SwiftException;
import swift.swiftparser.types.SwBoolean;
import swift.swiftparser.types.SwInteger;
import swift.swiftparser.types.SwString;
import swift.swiftparser.types.SwiftType;
import swift.swiftparser.values.ConstantValue;
import swift.swiftparser.values.IntValue;
import swift.swiftparser.values.StringValue;
import swift.swiftparser.values.SwiftValue;
/**
 *
 * @author naoki
 */
public class Main {
    static Map<String, SwiftType> types = new HashMap<>();
    static Map<String, SwiftValue> variables = new HashMap<>();
    
    public static void main(String[] args) throws IOException {
        types.putAll(Stream.of(SwString.of(), SwInteger.of(), SwBoolean.of())
                .collect(Collectors.toMap(t -> t.getName(), t -> t)));
        System.out.print("> ");
        BufferedReader bur = new BufferedReader(new InputStreamReader(System.in));
        for(String line; (line = bur.readLine()) != null;){
            try{
            AST result = SwiftParser.swift().parse(line);
            if(result instanceof ASTDecConst){
                ASTDecConst dec = (ASTDecConst) result;
                SwiftValue val = eval(dec.getExpression());
                ASTPattern pat = dec.getPattern();
                if(pat instanceof ASTPatIdentifier){
                    ASTPatIdentifier ident = (ASTPatIdentifier) pat;
                    SwiftType type = ident.getType().map(t -> {
                        Optional<SwiftType> sw = getType(t);
                        if(!sw.isPresent()){
                            throw new SwiftException("type not found." + t);
                        }
                        return sw.get();
                                }).orElse(val.getType());
                    if(!type.equals(val.getType())){
                        throw new SwiftException(String.format("型が一致しません %s:%s", type, val.getType()));
                    }
                    ConstantValue con = new ConstantValue(ident.getIdentifier(), type, val);
                    variables.put(ident.getIdentifier(), con);
                    System.out.println(con);
                } else {
                    System.out.println(result);
                }
            }else{
                try{
                System.out.printf("%s:%s%n", eval(result), type(result).getName());
                }catch(NullPointerException ex){
                    throw new SwiftException("ぬるぽ" + result);
                }
            }
            }catch(SwiftException ex){
                System.out.println(ex);
            }
            System.out.print("> ");
            
        }
    }
    
    static Optional<SwiftType> getType(ASTType ident){
        if(ident instanceof ASTIdentifier){
            return Optional.ofNullable(types.get(((ASTIdentifier)ident).getIdent()));
        }else{
            throw new SwiftException("not support yet." + ident);
        }
    }
    
    static SwiftValue eval(AST exp){
        return match(exp, 
            caseOf(ASTIntLiteral.class, i -> new IntValue(i.getValue())),
            caseOf(ASTStringLiteral.class, s -> new StringValue(s.getValue())),
            caseOf(ConstantValue.class, c -> c.getValue()),
            caseOf(ASTExpIdent.class, id -> {
                SwiftValue var = variables.get(id.getIdent());
                if(var == null){
                    throw new SwiftException("var not found." + id.getIdent());
                }
                return ((ConstantValue)var).getValue();
            }),
            caseOf(ASTBinOp.class, bo -> {
                SwiftValue leftValue = eval(bo.getLeft());
                SwiftValue rightValue = eval(bo.getRight());
                switch(bo.getOp()){
                    case "+":
                        if(leftValue.getType().equals(SwString.of()) || rightValue.getType().equals(SwString.of())){
                            //どちらかがstringの足し算はstring
                            return new StringValue(leftValue.getValue().toString() + rightValue.getValue().toString());
                        } else if(leftValue.getType().equals(SwInteger.of()) && rightValue.getType().equals(SwInteger.of())){
                            //int同士の足し算はint
                            return new IntValue(((IntValue)leftValue).getIntValue() + ((IntValue)rightValue).getIntValue());
                        } else {
                            throw new SwiftException("足し算できません");
                        }
                }
                throw new SwiftException("not support op." + bo.getOp());
            })
        );
    }
    static SwiftType type(AST exp){
        return match(exp,
            caseOf(ASTIntLiteral.class, i -> SwInteger.of()),
            caseOf(ASTStringLiteral.class, s -> SwString.of()),
            caseOf(ConstantValue.class, c -> c.getType()),
            caseOf(ASTExpIdent.class, id -> {
                SwiftValue var = variables.get(id.getIdent());
                if(var == null){
                    throw new SwiftException("var not found." + id.getIdent());
                }
                return var.getType();
            }),
            caseOf(ASTBinOp.class, bo -> {
                SwiftType leftType = type(bo.getLeft());
                SwiftType rightType = type(bo.getRight());
                switch(bo.getOp()){
                    case "+":
                        if(leftType.equals(SwString.of()) || rightType.equals(SwString.of())){
                            //どちらかがstringの足し算はstring
                            return SwString.of();
                        } else if(leftType.equals(SwInteger.of()) && rightType.equals(SwInteger.of())){
                            //int同士の足し算はint
                            return SwInteger.of();
                        } else {
                            throw new SwiftException(leftType.getName() + "と" + rightType.getName() + "の足し算はできません");
                        }
                }
                throw new SwiftException("not support op." + bo.getOp());
            })
        );
    }
    
}
