package org.internals.frontend.pascal;

import org.internals.frontend.EofToken;
import org.internals.frontend.Parser;
import org.internals.frontend.Scanner;
import org.internals.frontend.Token;
import org.internals.message.Message;
import org.internals.message.MessageType;

/**
 * <h1>PascalParserTD</h1>
 *
 * <p>The top-down Pascal parser.</p>
 */
public class PascalParserTD extends Parser {
    /**
     * Constructor.
     *
     * @param scanner the scanner to be used with this parser
     */
    public PascalParserTD(Scanner scanner) {
        super(scanner);
    }

    /**
     * Parse a Pascal source program and generate the symbol table
     * and the intermediate code.
     * @throws Exception
     */
    @Override
    public void parse() throws Exception {
        Token token;
        long startTime = System.currentTimeMillis();
        while (!((token = nextToken()) instanceof EofToken)){}

        // Send the parser summary message
        float elapsedTime = (System.currentTimeMillis() - startTime) / 1000f;
        sendMessage(new Message(MessageType.PARSER_SUMMARY,
                new Number[]{token.getLineNumber(),
                        getErrorCount(),
                        elapsedTime}));
    }

    /**
     * Return the number of syntax errors found by the parser.
     * @return the error count.
     */
    @Override
    public int getErrorCount() {
        return 0;
    }
}
