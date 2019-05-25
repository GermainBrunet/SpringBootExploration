package ca.gb.sf.exceptions;

import java.io.Serializable;

public class ApplicationRuntimeException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = -7376764941827658694L;

    public ApplicationRuntimeException() {
        super();
    }
    public ApplicationRuntimeException(String s) {
        super(s);
    }
    public ApplicationRuntimeException(String s, Throwable throwable) {
        super(s, throwable);
    }
    public ApplicationRuntimeException(Throwable throwable) {
        super(throwable);
    }
	
}
