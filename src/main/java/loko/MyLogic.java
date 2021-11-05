package loko;

import loko.gen.CPPGrammarBaseVisitor;
import loko.gen.CPPGrammarParser;

public class MyLogic extends CPPGrammarBaseVisitor<Elem> {
    @Override
    public Elem visitProgram(CPPGrammarParser.ProgramContext ctx) {
        //System.out.println("type " + ctx.getText());
        return super.visitProgram(ctx);
    }

    @Override
    public Elem visitDescription(CPPGrammarParser.DescriptionContext ctx) {
        return super.visitDescription(ctx);
    }

    @Override
    public Elem visitClassOp(CPPGrammarParser.ClassOpContext ctx) {
        //System.out.println("type " + ctx.getText());
        return super.visitClassOp(ctx);
    }

    @Override
    public Elem visitInnerClass(CPPGrammarParser.InnerClassContext ctx) {
        //System.out.println("type " + ctx.getText());
        return super.visitInnerClass(ctx);
    }

    @Override
    public Elem visitMain(CPPGrammarParser.MainContext ctx) {
        //System.out.println("type " + ctx.getText());
        return super.visitMain(ctx);
    }

    @Override
    public Elem visitOpsAndVars(CPPGrammarParser.OpsAndVarsContext ctx) {
        //System.out.println("type " + ctx.getText());
        return super.visitOpsAndVars(ctx);
    }

    @Override
    public Elem visitType(CPPGrammarParser.TypeContext ctx) {
        System.out.println("type " + ctx.getText());
        return super.visitType(ctx);
    }

    @Override
    public Elem visitOperator(CPPGrammarParser.OperatorContext ctx) {
        //System.out.println("type " + ctx.getText());
        return super.visitOperator(ctx);
    }

    @Override
    public Elem visitEmptyOperator(CPPGrammarParser.EmptyOperatorContext ctx) {
        //System.out.println("type " + ctx.getText());
        return super.visitEmptyOperator(ctx);
    }

    @Override
    public Elem visitCompoundOperator(CPPGrammarParser.CompoundOperatorContext ctx) {
        //System.out.println("type " + ctx.getText());
        return super.visitCompoundOperator(ctx);
    }

    @Override
    public Elem visitSimpleOperator(CPPGrammarParser.SimpleOperatorContext ctx) {
        //System.out.println("type " + ctx.getText());
        return super.visitSimpleOperator(ctx);
    }

    @Override
    public Elem visitIfOp(CPPGrammarParser.IfOpContext ctx) {
        //System.out.println("type " + ctx.getText());
        return super.visitIfOp(ctx);
    }

    @Override
    public Elem visitElseOp(CPPGrammarParser.ElseOpContext ctx) {
        //System.out.println("type " + ctx.getText());
        return super.visitElseOp(ctx);
    }

    @Override
    public Elem visitA0(CPPGrammarParser.A0Context ctx) {
        //System.out.println("type " + ctx.getText());
        return super.visitA0(ctx);
    }

    @Override
    public Elem visitA1(CPPGrammarParser.A1Context ctx) {
        //System.out.println("type " + ctx.getText());
        return super.visitA1(ctx);
    }

    @Override
    public Elem visitA2(CPPGrammarParser.A2Context ctx) {
        //System.out.println("type " + ctx.getText());
        return super.visitA2(ctx);
    }

    @Override
    public Elem visitA3(CPPGrammarParser.A3Context ctx) {
        //System.out.println("type " + ctx.getText());
        return super.visitA3(ctx);
    }

    @Override
    public Elem visitA4(CPPGrammarParser.A4Context ctx) {
        //System.out.println("type " + ctx.getText());
        return super.visitA4(ctx);
    }

    @Override
    public Elem visitA5(CPPGrammarParser.A5Context ctx) {
        //System.out.println("type " + ctx.getText());
        return super.visitA5(ctx);
    }

    @Override
    public Elem visitA6(CPPGrammarParser.A6Context ctx) {
        //System.out.println("type " + ctx.getText());
        return super.visitA6(ctx);
    }

    @Override
    public Elem visitA7(CPPGrammarParser.A7Context ctx) {
        //System.out.println("type " + ctx.getText());
        return super.visitA7(ctx);
    }

    @Override
    public Elem visitA11(CPPGrammarParser.A11Context ctx) {
        //System.out.println("type " + ctx.getText());
        return super.visitA11(ctx);
    }

    @Override
    public Elem visitA22(CPPGrammarParser.A22Context ctx) {
        //System.out.println("type " + ctx.getText());
        return super.visitA22(ctx);
    }

    @Override
    public Elem visitA33(CPPGrammarParser.A33Context ctx) {
        //System.out.println("type " + ctx.getText());
        return super.visitA33(ctx);
    }

    @Override
    public Elem visitA44(CPPGrammarParser.A44Context ctx) {
        //System.out.println("type " + ctx.getText());
        return super.visitA44(ctx);
    }

    @Override
    public Elem visitA55(CPPGrammarParser.A55Context ctx) {
        //System.out.println("type " + ctx.getText());
        return super.visitA55(ctx);
    }

    @Override
    public Elem visitExpression(CPPGrammarParser.ExpressionContext ctx) {
        //System.out.println("type " + ctx.getText());
        return super.visitExpression(ctx);
    }

    @Override
    public Elem visitVarDeclaration(CPPGrammarParser.VarDeclarationContext ctx) {
        //System.out.println("type " + ctx.getText());
        return super.visitVarDeclaration(ctx);
    }

    @Override
    public Elem visitVarList(CPPGrammarParser.VarListContext ctx) {
        //System.out.println("type " + ctx.getText());
        return super.visitVarList(ctx);
    }

    @Override
    public Elem visitVarDop(CPPGrammarParser.VarDopContext ctx) {
        //System.out.println("type " + ctx.getText());
        return super.visitVarDop(ctx);
    }

    @Override
    public Elem visitVar(CPPGrammarParser.VarContext ctx) {
        //System.out.println("type " + ctx.getText());
        return super.visitVar(ctx);
    }

    @Override
    public Elem visitP(CPPGrammarParser.PContext ctx) {
        //System.out.println("type " + ctx.getText());
        return super.visitP(ctx);
    }

    @Override
    public Elem visitSquare(CPPGrammarParser.SquareContext ctx) {
        //System.out.println("type " + ctx.getText());
        return super.visitSquare(ctx);
    }

    @Override
    public Elem visitObject(CPPGrammarParser.ObjectContext ctx) {
        //System.out.println("type " + ctx.getText());
        return super.visitObject(ctx);
    }

    @Override
    public Elem visitArrayOrClass(CPPGrammarParser.ArrayOrClassContext ctx) {
        //System.out.println("type " + ctx.getText());
        return super.visitArrayOrClass(ctx);
    }

    @Override
    public Elem visitArray(CPPGrammarParser.ArrayContext ctx) {
        //System.out.println("type " + ctx.getText());
        return super.visitArray(ctx);
    }

    @Override
    public Elem visitField(CPPGrammarParser.FieldContext ctx) {
        //System.out.println("type " + ctx.getText());
        return super.visitField(ctx);
    }

    @Override
    public Elem visitIdent(CPPGrammarParser.IdentContext ctx) {
        //System.out.println("type " + ctx.getText());
        return super.visitIdent(ctx);
    }

    @Override
    public Elem visitClassName(CPPGrammarParser.ClassNameContext ctx) {
        System.out.println(ctx.getText());
        return super.visitClassName(ctx);
    }

    @Override
    public Elem visitConstant(CPPGrammarParser.ConstantContext ctx) {
        //System.out.println("type " + ctx.getText());
        return super.visitConstant(ctx);
    }
}
