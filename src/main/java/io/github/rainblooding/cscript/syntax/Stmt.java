package io.github.rainblooding.cscript.syntax;
import java.util.List;
import io.github.rainblooding.cscript.base.Token;

public abstract class Stmt {

    public abstract <R> R accept(Visitor<R> visitor);


    public interface Visitor<R> {

        R visitBlockStmt(Stmt.Block stmt);
        R visitClassStmt(Stmt.Class stmt);
        R visitExpressionStmt(Stmt.Expression stmt);
        R visitFunctionStmt(Stmt.Function stmt);
        R visitIfStmt(Stmt.If stmt);
        R visitPrintStmt(Stmt.Print stmt);
        R visitReturnStmt(Stmt.Return stmt);
        R visitVarStmt(Stmt.Var stmt);
        R visitWhileStmt(Stmt.While stmt);
    }

    public static class Block extends Stmt {
        public final List<Stmt> statements;

        public Block( List<Stmt> statements ) {
            this.statements = statements;
        }

        @Override
        public <R> R accept(Visitor<R> visitor) {
            return visitor.visitBlockStmt(this);
        }


    }

    public static class Class extends Stmt {
        public final Token name;
        public final List<Stmt.Function> methods;

        public Class( Token name,  List<Stmt.Function> methods ) {
            this.name = name;
            this.methods = methods;
        }

        @Override
        public <R> R accept(Visitor<R> visitor) {
            return visitor.visitClassStmt(this);
        }


    }

    public static class Expression extends Stmt {
        public final Expr expression;

        public Expression( Expr expression ) {
            this.expression = expression;
        }

        @Override
        public <R> R accept(Visitor<R> visitor) {
            return visitor.visitExpressionStmt(this);
        }


    }

    public static class Function extends Stmt {
        public final Token name;
        public final List<Token> params;
        public final List<Stmt> body;

        public Function( Token name,  List<Token> params,  List<Stmt> body ) {
            this.name = name;
            this.params = params;
            this.body = body;
        }

        @Override
        public <R> R accept(Visitor<R> visitor) {
            return visitor.visitFunctionStmt(this);
        }


    }

    public static class If extends Stmt {
        public final Expr condition;
        public final Stmt thenBranch;
        public final Stmt elseBranch;

        public If( Expr condition,  Stmt thenBranch,  Stmt elseBranch ) {
            this.condition = condition;
            this.thenBranch = thenBranch;
            this.elseBranch = elseBranch;
        }

        @Override
        public <R> R accept(Visitor<R> visitor) {
            return visitor.visitIfStmt(this);
        }


    }

    public static class Print extends Stmt {
        public final Expr expression;

        public Print( Expr expression ) {
            this.expression = expression;
        }

        @Override
        public <R> R accept(Visitor<R> visitor) {
            return visitor.visitPrintStmt(this);
        }


    }

    public static class Return extends Stmt {
        public final Token keyword;
        public final Expr value;

        public Return( Token keyword,  Expr value ) {
            this.keyword = keyword;
            this.value = value;
        }

        @Override
        public <R> R accept(Visitor<R> visitor) {
            return visitor.visitReturnStmt(this);
        }


    }

    public static class Var extends Stmt {
        public final Token name;
        public final Expr initializer;

        public Var( Token name,  Expr initializer ) {
            this.name = name;
            this.initializer = initializer;
        }

        @Override
        public <R> R accept(Visitor<R> visitor) {
            return visitor.visitVarStmt(this);
        }


    }

    public static class While extends Stmt {
        public final Expr condition;
        public final Stmt body;

        public While( Expr condition,  Stmt body ) {
            this.condition = condition;
            this.body = body;
        }

        @Override
        public <R> R accept(Visitor<R> visitor) {
            return visitor.visitWhileStmt(this);
        }


    }

}