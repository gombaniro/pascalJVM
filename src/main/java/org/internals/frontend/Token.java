package org.internals.frontend;

public class Token {
    protected TokenType type;            // language-specific token type
    protected String text;               // token text
    protected Object value;              // token value
    protected Source source;             // source
    protected int lineNum;               // line number of the token's source line
    protected int position;              // position of the first token character

    /**
     * Constructor.
     * @param source the source from where to fetch the token's characters.
     * @throws Exception if an error occurred.
     */
    public Token(Source source) throws Exception{
        this.source = source;
        this.lineNum = source.getLine();
        this.position = source.getPosition();
        extract();
    }

    protected void extract() throws Exception {
        text = Character.toString(currentChar());
        value = null;
        nextChar();     // consume current character
    }

    /**
     * Call the source's currentChar() method.
     * @return the current character from the source.
     * @throws Exception if an error occurred.
     */
    protected char currentChar() throws Exception {
        return source.currentChar();
    }

    /**
     * Call the source's nextChar() method.
     * @return the current character from the source after moving forward.
     * @throws Exception if an error occurred.
     */
    protected char nextChar() throws Exception {
        return source.nextChar();
    }

    /**
     * Call the source's peekCHar() method.
     * @return the next character from the source without moving forward.
     * @throws Exception if an error occurred.
     */
    protected char peekChar() throws Exception {
        return source.peekChar();
    }

}
