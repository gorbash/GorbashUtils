package com.gorbash.utils;

/**
 * Created by Gorbash on 2014-12-22.
 *
 * Exception is thrown when forbidden operation is performed on empty container.
 */
public class EmptyContainerException extends RuntimeException {

    public EmptyContainerException() {
        super();
    }

}
