package com.comze.sanman00.lang;

public class SyntaxError extends Error {
    private static final long serialVersionUID = 1L;

    /**
     * {@inheritDoc}
     */
    public SyntaxError() {
        super();
    }

    /**
     * {@inheritDoc}
     *
     * @param message The detail message
     */
    public SyntaxError(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     *
     * @param message The detail message
     * @param cause The cause of this error
     */
    public SyntaxError(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * {@inheritDoc}
     *
     * @param cause The cause of this error
     */
    public SyntaxError(Throwable cause) {
        super(cause);
    }
}
