package zoot.arbre.variable;

import zoot.arbre.ArbreAbstrait;
import zoot.arbre.expressions.Expression;

public abstract class Variable extends Expression {
    protected Variable(int n) {
        super(n);
    }
}
