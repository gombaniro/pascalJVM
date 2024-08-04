package org.internals;

import org.internals.backend.Backend;
import org.internals.frontend.Parser;
import org.internals.frontend.Source;
import org.internals.intermediate.ICode;
import org.internals.intermediate.SymbolTable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * <h1>Pascal</h1>
 *
 * <p>Compile or interpret a Pascal source program</p>
 */
public class Pascal {
    private Parser parser;                 // language independent parser
    private final Source source;           // language independent source;
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
