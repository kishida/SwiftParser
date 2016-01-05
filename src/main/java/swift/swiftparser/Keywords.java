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
public class Keywords {
    static final String[] forDec = {
        "class", "deinit", "enum", "extension", "func", "import", "init", "inout",
        "internal", "let", "operator", "primitive", "protocol", "public", "static",
        "struct", "subscript", "typealias", "var"};
    static final String[] forStatements = {
        "break", "case", "continue", "default", "defer", "do", "elese", "fallthrough",
        "for", "guard", "if", "in", "repeat", "return", "switch", "where", "while"};
    static final String[] forTypes = {
        "as", "catch", "dynamicType", "false", "is", "nil", "rethrows", "super",
        "self", "Self", "throw", "throws", "true", "try", "__COLUMN__", "__FILE__",
        "__FUNCTION__", "__LINE__"};
    static final String[] forPatterns = {
        "_"};
}
