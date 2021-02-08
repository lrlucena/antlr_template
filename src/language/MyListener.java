package language;

import language.parser.LanguageBaseListener;
import language.parser.ParseTreeProperty;
import static language.parser.LanguageParser.*;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

public class MyListener<T> extends LanguageBaseListener{
    private final ParseTreeProperty parseTreeProperty;

    private T program;

    public T getProgram() {
        return program;
    }

    public MyListener() {
        this.parseTreeProperty = new ParseTreeProperty();
    }

	@Override public void exitProgram(ProgramContext ctx) { 
        T exp = parseTreeProperty.get(ctx.expression());
        program = exp;
    }

    @Override public void exitInt(IntContext ctx) {
        int a = Integer.parseInt(ctx.getText());
        parseTreeProperty.put(ctx, a);
    }

    @Override public void exitSum(SumContext ctx) {
        int a = parseTreeProperty.get(ctx.expression(0));
        int b = parseTreeProperty.get(ctx.expression(1));
        int c = a + b;
        parseTreeProperty.put(ctx, c);
    }

	@Override public void exitEveryRule(ParserRuleContext ctx) { }

    @Override public void visitTerminal(TerminalNode node) {
    } 
}