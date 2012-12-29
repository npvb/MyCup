import java_cup.runtime.*;

public class Symbol {

    public int sym;
    public int parse_state;
    boolean used_by_parser;
    public int left;
    public int right;
    public Object value;

    public Symbol(int id, Symbol left, Symbol right, Object o) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public Symbol(int id, Symbol left, Symbol right) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public Symbol(int id, int l, int r, Object o) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public Symbol(int id, Object o) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public Symbol(int id, int l, int r) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public Symbol(int sym_num) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    Symbol(int sym_num, int state) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public String toString() {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }
}
