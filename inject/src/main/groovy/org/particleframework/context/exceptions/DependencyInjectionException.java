package org.particleframework.context.exceptions;

import org.particleframework.context.ComponentResolutionContext;
import org.particleframework.inject.Argument;
import org.particleframework.inject.FieldInjectionPoint;
import org.particleframework.inject.MethodInjectionPoint;

/**
 * Represents a runtime failure to perform dependency injection
 *
 * @author Graeme Rocher
 * @since 1.0
 */
public class DependencyInjectionException extends BeanInstantiationException {

    public DependencyInjectionException(ComponentResolutionContext resolutionContext, Argument argument, Throwable cause) {
        super(buildMessage(resolutionContext, argument, null, false), cause);
    }
    public DependencyInjectionException(ComponentResolutionContext resolutionContext, Argument argument, String message) {
        super(buildMessage(resolutionContext, argument, message, false));
    }

    public DependencyInjectionException(ComponentResolutionContext resolutionContext, FieldInjectionPoint fieldInjectionPoint, Throwable cause) {
        super(buildMessage(resolutionContext, fieldInjectionPoint, null, false), cause);
    }

    public DependencyInjectionException(ComponentResolutionContext resolutionContext, FieldInjectionPoint fieldInjectionPoint, String message) {
        super(buildMessage(resolutionContext, fieldInjectionPoint, message, false));
    }

    public DependencyInjectionException(ComponentResolutionContext resolutionContext, MethodInjectionPoint methodInjectionPoint,Argument argument, Throwable cause) {
        super(buildMessage(resolutionContext, methodInjectionPoint, argument, null, false), cause);
    }

    public DependencyInjectionException(ComponentResolutionContext resolutionContext, MethodInjectionPoint methodInjectionPoint, Argument argument, String message) {
        super(buildMessage(resolutionContext, methodInjectionPoint, argument, message, false));
    }

    protected DependencyInjectionException(ComponentResolutionContext resolutionContext, MethodInjectionPoint methodInjectionPoint, Argument argument, String message, boolean circular) {
        super(buildMessage(resolutionContext, methodInjectionPoint, argument, message, circular));
    }

    protected DependencyInjectionException(ComponentResolutionContext resolutionContext, FieldInjectionPoint fieldInjectionPoint, String message, boolean circular) {
        super(buildMessage(resolutionContext, fieldInjectionPoint, message, circular));
    }

    protected DependencyInjectionException(ComponentResolutionContext resolutionContext, Argument argument, String message, boolean circular) {
        super(buildMessage(resolutionContext, argument, message, circular));
    }

    private static String buildMessage(ComponentResolutionContext resolutionContext, MethodInjectionPoint methodInjectionPoint, Argument argument, String message, boolean circular) {
        StringBuilder builder = new StringBuilder("Failed to inject value for parameter [");
        String ls = System.getProperty("line.separator");
        builder.append(argument.getName()).append("] of method [")
                .append(methodInjectionPoint.getName())
                .append("] of class: ")
                .append(resolutionContext.getPath().peek().getDeclaringType().getName())
                .append(ls)
                .append(ls);

        if(message != null) {
            builder.append("Message: ").append(message).append(ls);
        }
        String pathString =  circular ? resolutionContext.getPath().toCircularString() : resolutionContext.getPath().toString();
        builder.append("Path Taken: ");
        if(circular) builder.append(ls);
        builder.append(pathString);
        return builder.toString();
    }


    private static String buildMessage(ComponentResolutionContext resolutionContext, FieldInjectionPoint fieldInjectionPoint, String message, boolean circular) {
        StringBuilder builder = new StringBuilder("Failed to inject value for field [");
        String ls = System.getProperty("line.separator");
        builder.append(fieldInjectionPoint.getName()).append("] of class: ")
                .append(resolutionContext.getPath().peek().getDeclaringType().getName())
                .append(ls)
                .append(ls);

        if(message != null) {
            builder.append("Message: ").append(message).append(ls);
        }
        String pathString =  circular ? resolutionContext.getPath().toCircularString() : resolutionContext.getPath().toString();
        builder.append("Path Taken: ");
        if(circular) builder.append(ls);
        builder.append(pathString);
        return builder.toString();
    }




    private static String buildMessage(ComponentResolutionContext resolutionContext, Argument argument, String message, boolean circular) {
        StringBuilder builder = new StringBuilder("Failed to inject value for parameter [");
        String ls = System.getProperty("line.separator");
        builder.append(argument.getName()).append("] of class: ")
               .append(resolutionContext.getPath().peek().getDeclaringType().getName())
               .append(ls)
               .append(ls);
        if(message != null) {
            builder.append("Message: ").append(message).append(ls);;
        }
        builder.append("Path Taken: ").append(resolutionContext.getPath().toString());
        return builder.toString();
    }
}
