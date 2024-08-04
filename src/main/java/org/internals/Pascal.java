package org.internals;

import org.internals.backend.Backend;
import org.internals.backend.BackendFactory;
import org.internals.frontend.FrontendFactory;
import org.internals.frontend.Parser;
import org.internals.frontend.Source;
import org.internals.intermediate.ICode;
import org.internals.intermediate.SymbolTable;
import org.internals.message.Message;
import org.internals.message.MessageListener;
import org.internals.message.MessageType;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * <h1>Pascal</h1>
 *
 * <p>Compile or interpret a Pascal source program</p>
 */
public class Pascal {
    private Parser parser;                 // language independent parser
    private  Source source;                // language independent source;
    private ICode iCode;                   // generated intermediate code
    private SymbolTable symTab;            // generated symbol table
    private Backend backend;               // backend

    /**
     * Compile or interpret a Pascal source program.
     *
     * @param operation either "compile" or "execute"
     * @param filePath  filePath the source file path
     * @param flags     flags the command line flags
     */
    public Pascal(String operation, String filePath, String flags) {

        try {
            boolean intermediate = flags.indexOf('i') > -1;
            boolean xref = flags.indexOf('x') > -1;
            source = new Source(new BufferedReader(new FileReader(filePath)));
            source.addMessageListener(new SourceMessageListener());

            parser = FrontendFactory.createParser("Pascal", "top-down", source);
            parser.addMessageListener(new ParserMessageListener());

            backend = BackendFactory.createBackend(operation);
            backend.addMessageListener(new BackendMessageListener());

            parser.parse();
            source.close();

            iCode = parser.getiCode();
            symTab = parser.getSymTab();


        } catch (Exception e) {
            System.out.println("***** Internal translator error. *****");
            e.printStackTrace();
        }
    }

    private static final String FLAGS = "[-ix]";
    private static final String USAGE =
            "Usage: Pascal execute|compile " + FLAGS + " <source file path>";

    public static void main(String[] args) {
        try {
            String operation = args[0];
            // Operation
            if ( !(operation.equalsIgnoreCase("compile")
                    || operation.equalsIgnoreCase("execute"))) {
                throw new Exception();
            }
            int i = 0;

            // Flags
            String flags = "";
            while ((++i < args.length) && (args[i].charAt(0) == '-')) {
                flags += args[i].substring(1);
            }

            // Source path
            if (i < args.length) {
                String path = args[i];
                new Pascal(operation, path, flags);
            } else {
                throw  new Exception();
            }

        }catch (Exception e) {
             System.out.println(USAGE);
        }
    }

    private static String SOURCE_LINE_FORMAT = "%03d %s";


    /**
     * Listener for source message
     */
    private class SourceMessageListener implements MessageListener {

        /**
         * Called by the source whenever it produces a message
         * @param message the message that was sent.
         */
        @Override
        public void messageReceived(Message message) {
            MessageType type = message.getType();
            Object body[] = (Object[]) message.getBody();
            switch (type) {
                case SOURCE_LINE -> {
                    int lineNumber = (Integer) body[0];
                    String lineText = (String) body[1];
                    System.out.println(String.format(SOURCE_LINE_FORMAT, lineNumber, lineText));
                }
            }
        }
    }

    private static  final String PARSER_SUMMARY_FORMAT =
            "\n%,20d source lines." +
            "\n%,20d syntax errors." +
            "\n%,20.2f seconds total parsing time.\n";

    /**
     * Listener for parser message
     */
    private class ParserMessageListener implements MessageListener {

        /**
         * Called by the parser whenever it produces a message.
         * @param message the message that was sent.
         */
        @Override
        public void messageReceived(Message message) {
            MessageType type = message.getType();
              switch (type) {
                  case PARSER_SUMMARY -> {
                      Object body[] = (Object[]) message.getBody();
                      int statementCount = (Integer) body[0];
                      int syntaxErrors = (Integer) body[1];
                      float elapsedTime = (Float) body[2];

                      System.out.printf(PARSER_SUMMARY_FORMAT,
                              statementCount, syntaxErrors, elapsedTime);
                  }
              }
        }
    }


    private static final String INTERPRETER_SUMMARY_FORMAT =
            "\n%,20d statements executed." +
            "\n%,20d runtime errors" +
            "\n%,20.2f seconds total execution time.\n";

    private static final String COMPILER_SUMMARY_FORMAT =
            "\n%,20d instructions generated." +
            "\n%,20.2f seconds total code generation time.\n";

    /**
     * Listener for backend end messages.
     */
    private class BackendMessageListener implements MessageListener {

        /**
         * Called by the back end whenever it produces a message
         * @param message the message that was sent.
         */
        @Override
        public void messageReceived(Message message) {
            MessageType type = message.getType();

            switch (type) {
                case INTERPRETER_SUMMARY -> {
                    Number body[] = (Number[]) message.getBody();
                    int executionCount = (Integer)body[0];
                    int runtimeErrors = (Integer)body[1];
                    float elapsedTime = (Float) body[2];
                    System.out.printf(INTERPRETER_SUMMARY_FORMAT,
                            executionCount, runtimeErrors, elapsedTime);
                }
                case COMPILER_SUMMARY -> {
                    Number body[] = (Number[]) message.getBody();
                    int instructionCount = (Integer)body[0];
                    float elapsedTime = (Float) body[2];
                    System.out.printf(COMPILER_SUMMARY_FORMAT,
                            instructionCount, elapsedTime);
                }
            }

        }
    }
}
