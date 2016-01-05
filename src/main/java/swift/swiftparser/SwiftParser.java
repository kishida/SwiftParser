/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swift.swiftparser;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Value;
import org.codehaus.jparsec.OperatorTable;
import org.codehaus.jparsec.Parser;
import org.codehaus.jparsec.Parsers;
import static org.codehaus.jparsec.Parsers.*;
import org.codehaus.jparsec.Scanners;
import org.codehaus.jparsec.Terminals;
import swift.swiftparser.ast.AST;
import swift.swiftparser.ast.ASTBinOp;
import swift.swiftparser.ast.ASTDecConst;
import swift.swiftparser.ast.ASTDeclaration;
import swift.swiftparser.ast.ASTExpIdent;
import swift.swiftparser.ast.ASTExpression;
import swift.swiftparser.ast.ASTIdentifier;
import swift.swiftparser.ast.ASTIntLiteral;
import swift.swiftparser.ast.ASTPatIdentifier;
import swift.swiftparser.ast.ASTPattern;
import swift.swiftparser.ast.ASTStringLiteral;
import swift.swiftparser.ast.ASTType;

/**
 *
 * @author naoki
 */
public class SwiftParser {
    static final Terminals terms = Terminals
            .operators(
                Stream.of(Operators.dotOperator, Operators.operatorHead, Operators.punctuations)
                    .flatMap(Stream::of).collect(Collectors.toList()))
            .words(Scanners.IDENTIFIER)
            .caseInsensitiveKeywords(
                Stream.of(Keywords.forDec, Keywords.forPatterns, Keywords.forStatements, Keywords.forTypes)
                    .flatMap(Stream::of).collect(Collectors.toList()))
            .build();
    static Parser<Void> ignored = Scanners.WHITESPACES.optional();
    static Parser<?> tokenizer = Parsers.or(
            terms.tokenizer(),
            Terminals.Identifier.TOKENIZER,
            Terminals.StringLiteral.DOUBLE_QUOTE_TOKENIZER,
            Terminals.IntegerLiteral.TOKENIZER);

    public static Parser<ASTIntLiteral> integer(){
        return Terminals.IntegerLiteral.PARSER.map(s -> new ASTIntLiteral(Integer.parseInt(s)));
    }

    public static Parser<ASTStringLiteral> str(){
        return Terminals.StringLiteral.PARSER.map(ASTStringLiteral::new);
    }

    public static Parser<ASTExpression> literal(){
        return Parsers.or(integer(), str());
    }
    
    public static Parser<ASTExpIdent> identExpression(){
        return identifier().map(id -> new ASTExpIdent(id.getIdent()));
    }
    
    public static Parser<ASTExpression> expression(){
        return Parsers.or(literal(), identExpression());
    }
    
    public static Parser<ASTExpression> biOperator(){
        return new OperatorTable<ASTExpression>()
                .infixl(terms.token("+").retn((l, r) -> new ASTBinOp(l, r, "+")), 10)
                .build(expression());
    }
    
    
    
    public static Parser<ASTIdentifier> identifier(){
        return Terminals.Identifier.PARSER.map(s -> new ASTIdentifier(s));
    }
    // type
    public static Parser<ASTIdentifier> typeIdentifier(){
        return identifier();
    }
    
    public static Parser<ASTType> type(){
        return Parsers.or(typeIdentifier());
    }
    
    public static Parser<ASTType> typeAnnotation(){
        return terms.token(":").next(type());
    }
    
    // pattern
    public static Parser<ASTPatIdentifier> identifierPattern(){
        return identifier().next(id -> typeAnnotation().optional()
                .map(t -> new ASTPatIdentifier(id.getIdent(), Optional.ofNullable(t))));
    }
    
    public static Parser<ASTPattern> pattern(){
        return Parsers.or(identifierPattern());
    }
    
    // declaration
    public static Parser<ASTDecConst> constantDeclaration(){
        return terms.token("let").next(pattern()).next(p -> 
                terms.token("=").next(biOperator())
            .map(exp -> new ASTDecConst(p, exp)));
    }
    
    @Value
    public static class ASTParameter{
        // Optional<String> prefix // let/var/inout
        // Optional<String> externalParamName
        String localParamName;
        ASTType type;
        // Optoinal<ASTExpression> defaultExp
    }
    
    // func
    public static Parser<ASTIdentifier> wildcard(){
        return terms.token("_").map(t -> new ASTIdentifier("_"));
    }
    
    public static Parser<ASTIdentifier> paramName(){
        return Parsers.or(wildcard(), identifier());
    }
    
    public static Parser<ASTParameter> parameter(){
        return Parsers.sequence(paramName(), typeAnnotation(), 
                (p, t) -> new ASTParameter(p.getIdent(), t));
    }
    
    public static Parser<ASTIdentifier> funcName(){
        return identifier(); //  or operator();
    }
   
    public static Parser<ASTType> funcResult(){
        return terms.token("->").next(type());
    }
    
    public static Parser<List<AST>> codeBlock(){
        return Parsers.between(terms.token("{"), statement().many(), terms.token("}"));
    }
    
   
    public static Parser funcDecl(){
        return Parsers.sequence(
                terms.token("func").next(funcName()),
                between(terms.token("("), 
                        parameter().sepBy(terms.token(",")),
                        terms.token(")")),
                funcResult(),
                codeBlock().optional());
    }
    
    public static Parser<ASTDeclaration> declaration(){
        return Parsers.or(constantDeclaration());
    }
    
    public static Parser<AST> statement(){
        return Parsers.or(biOperator(), declaration());
    }
    
    public static Parser<AST> swift(){
        return statement().from(tokenizer, ignored);
    }
}
