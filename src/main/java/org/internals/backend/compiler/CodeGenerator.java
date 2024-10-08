package org.internals.backend.compiler;

import org.internals.backend.Backend;
import org.internals.intermediate.ICode;
import org.internals.intermediate.SymbolTable;
import org.internals.message.Message;
import org.internals.message.MessageType;


public class CodeGenerator extends Backend {

    /**
     * Process the intermediate code and the symbol table generated by the
     * parser to execute the source program.
     * @param iCode the intermediate code.
     * @param symTab the symbol table.
     * @throws Exception if an error occurred.
     */
    @Override
    public void process(ICode iCode, SymbolTable symTab) throws Exception {
        long startTime = System.currentTimeMillis();
        float elapsedTime  =(System.currentTimeMillis() - startTime) / 1000f;
        int executionCount = 0;
        int runtimeErrors = 0;

        // Send the interpreter summary message.
        sendMessage(new Message(MessageType.INTERPRETER_SUMMARY,
                new Number[]{executionCount, runtimeErrors, elapsedTime}));
    }
}
