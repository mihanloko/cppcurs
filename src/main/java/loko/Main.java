package loko;

import loko.gen.CPPGrammarLexer;
import loko.gen.CPPGrammarParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        String str = Main.readFile("code.txt");
//        System.out.println(str);
        CPPGrammarLexer lexer = new CPPGrammarLexer(CharStreams.fromString(str));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CPPGrammarParser parser = new CPPGrammarParser(tokens);
        ParseTree tree = parser.program();
        parser.ll.outTriads();
        parser.ll.optimize();
        System.out.println("optimized");
        parser.ll.outTriads();
//        walk(tree);
//        new MyLogic().visit(tree);
//        System.out.println(tree.toStringTree(parser));
    }

    private static String readFile(String path) {
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            return stream.collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static void walk(ParseTree tree) {
        System.out.println("node text: " + tree.getText());
        for (int i = 0; i < tree.getChildCount(); i++) {
            System.out.println("node child " + i + " text: " + tree.getChild(i).getText());
        }
        for (int i = 0; i < tree.getChildCount(); i++) {
            walk(tree.getChild(i));
        }
    }
}
