/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swift.swiftparser.types;

/**
 *
 * @author naoki
 */
public class SwJavaClass<T> extends SwiftType{

    Class<T> javaClass;

    public SwJavaClass(Class<T> javaClass) {
        this.javaClass = javaClass;
    }

    @Override
    public String getName() {
        return javaClass.getSimpleName();
    }

    @Override
    public SwiftType getParent() {
        return javaClass.equals(Object.class) ? SwAny.of() : 
                new SwJavaClass(javaClass.getSuperclass());
    }

}
