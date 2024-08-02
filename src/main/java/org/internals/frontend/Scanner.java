package org.internals.frontend;

public abstract class Scanner {

    protected Source source;

    /**
     * Constructor.
     * @param source the source where token characters are coming from.
     */
    public Scanner(Source source) {
        this.source = source;
    }

    public abstract Token currentToken();

    public abstract Token nextToken();

    protected abstract  Token extractToken();
    public  char currentChar() throws Exception {
        return this.source.currentChar();
    }
    public  char nextChar() throws Exception {
        return this.source.nextChar();
    }
}
