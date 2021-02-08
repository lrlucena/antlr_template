package language.parser;

import org.antlr.v4.runtime.tree.ParseTree;

public class ParseTreeProperty {
    private final org.antlr.v4.runtime.tree.ParseTreeProperty<Object> properties;

    public ParseTreeProperty() {
        properties = new org.antlr.v4.runtime.tree.ParseTreeProperty<>();
    }

    public <T> T get(ParseTree node) {
        T value = (T) properties.get(node);
        properties.removeFrom(node);
        return value;
    }

    public <T> void put(ParseTree node, T valor) {
        properties.put(node, valor);
    }
}