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
    public static void main(String[] args) {
        String str = Main.readFile("code.txt");
        CPPGrammarLexer lexer = new CPPGrammarLexer(CharStreams.fromString(str));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CPPGrammarParser parser = new CPPGrammarParser(tokens);
        ParseTree tree = parser.program();
        parser.ll.outTriads();
        parser.ll.optimize();
        System.out.println("optimized");
        parser.ll.outTriads();
    }

    private static String readFile(String path) {
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            return stream.collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
