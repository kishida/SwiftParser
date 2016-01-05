/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swift.swiftparser;

/**
 *
 * @author naoki
 */
public class Operators {
    static final String[] punctuations = {
        "(", ")", "{", "}", "[", "]", ".", ",", ":", ";", "=", "@", "#",
        "&", "->", "`", "?", "!"};
    static final String[] operatorHead = {
        "/", "=", "-", "+", "*", "!", "%", "<", ">", "&", "|", "^", "~", "?"
    };
    static final String[] dotOperator = {
        "...", "..<"};
}
