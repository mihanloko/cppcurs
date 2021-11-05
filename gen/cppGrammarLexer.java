// Generated from /home/mikhail/Документы/лабы/семетр 2/prog_lang/curs/src/main/java/cppGrammar.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class cppGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		CONSTANT=1, WHITESPACE=2, CLASS=3, LEFT_BRACE=4, RIGHT_BRACE=5, LEFT_ROUND=6, 
		RIGHT_ROUND=7, LEFT_SQUARE=8, RIGHT_SQUARE=9, SEMICOLON=10, COMMA=11, 
		DOT=12, INT=13, CHAR=14, MAIN=15, IF=16, ELSE=17, ASSIGN=18, MINUS=19, 
		PLUS=20, EQUAL=21, NOT_EQUAL=22, LESS=23, GREATER=24, LE=25, GE=26, LEFT_SHIFT=27, 
		RIGHT_SHIFT=28, MUL=29, DIV=30, MOD=31;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"CONSTANT", "WHITESPACE", "CLASS", "LEFT_BRACE", "RIGHT_BRACE", "LEFT_ROUND", 
			"RIGHT_ROUND", "LEFT_SQUARE", "RIGHT_SQUARE", "SEMICOLON", "COMMA", "DOT", 
			"INT", "CHAR", "MAIN", "IF", "ELSE", "ASSIGN", "MINUS", "PLUS", "EQUAL", 
			"NOT_EQUAL", "LESS", "GREATER", "LE", "GE", "LEFT_SHIFT", "RIGHT_SHIFT", 
			"MUL", "DIV", "MOD", "LETTERS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, "'class'", "'{'", "'}'", "'('", "')'", "'['", "']'", 
			"';'", "','", "'.'", "'int'", "'char'", "'main'", "'if'", "'else'", "'='", 
			"'-'", "'+'", "'=='", "'!='", "'<'", "'>'", "'<='", "'>='", "'<<'", "'>>'", 
			"'*'", "'/'", "'%'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "CONSTANT", "WHITESPACE", "CLASS", "LEFT_BRACE", "RIGHT_BRACE", 
			"LEFT_ROUND", "RIGHT_ROUND", "LEFT_SQUARE", "RIGHT_SQUARE", "SEMICOLON", 
			"COMMA", "DOT", "INT", "CHAR", "MAIN", "IF", "ELSE", "ASSIGN", "MINUS", 
			"PLUS", "EQUAL", "NOT_EQUAL", "LESS", "GREATER", "LE", "GE", "LEFT_SHIFT", 
			"RIGHT_SHIFT", "MUL", "DIV", "MOD"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public cppGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "cppGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2!\u00a1\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\3\2\6\2E\n\2\r\2\16\2F\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5"+
		"\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r"+
		"\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20"+
		"\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25"+
		"\3\26\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\32\3\33"+
		"\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!"+
		"\6!\u009e\n!\r!\16!\u009f\2\2\"\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31"+
		"\61\32\63\33\65\34\67\359\36;\37= ?!A\2\3\2\5\3\2\62;\4\2\13\f\"\"\4\2"+
		"C\\c|\2\u00a1\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2"+
		"\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2"+
		"\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2"+
		"\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2"+
		"\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3"+
		"\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\3D\3\2\2\2\5H\3\2\2\2\7L\3\2\2"+
		"\2\tR\3\2\2\2\13T\3\2\2\2\rV\3\2\2\2\17X\3\2\2\2\21Z\3\2\2\2\23\\\3\2"+
		"\2\2\25^\3\2\2\2\27`\3\2\2\2\31b\3\2\2\2\33d\3\2\2\2\35h\3\2\2\2\37m\3"+
		"\2\2\2!r\3\2\2\2#u\3\2\2\2%z\3\2\2\2\'|\3\2\2\2)~\3\2\2\2+\u0080\3\2\2"+
		"\2-\u0083\3\2\2\2/\u0086\3\2\2\2\61\u0088\3\2\2\2\63\u008a\3\2\2\2\65"+
		"\u008d\3\2\2\2\67\u0090\3\2\2\29\u0093\3\2\2\2;\u0096\3\2\2\2=\u0098\3"+
		"\2\2\2?\u009a\3\2\2\2A\u009d\3\2\2\2CE\t\2\2\2DC\3\2\2\2EF\3\2\2\2FD\3"+
		"\2\2\2FG\3\2\2\2G\4\3\2\2\2HI\t\3\2\2IJ\3\2\2\2JK\b\3\2\2K\6\3\2\2\2L"+
		"M\7e\2\2MN\7n\2\2NO\7c\2\2OP\7u\2\2PQ\7u\2\2Q\b\3\2\2\2RS\7}\2\2S\n\3"+
		"\2\2\2TU\7\177\2\2U\f\3\2\2\2VW\7*\2\2W\16\3\2\2\2XY\7+\2\2Y\20\3\2\2"+
		"\2Z[\7]\2\2[\22\3\2\2\2\\]\7_\2\2]\24\3\2\2\2^_\7=\2\2_\26\3\2\2\2`a\7"+
		".\2\2a\30\3\2\2\2bc\7\60\2\2c\32\3\2\2\2de\7k\2\2ef\7p\2\2fg\7v\2\2g\34"+
		"\3\2\2\2hi\7e\2\2ij\7j\2\2jk\7c\2\2kl\7t\2\2l\36\3\2\2\2mn\7o\2\2no\7"+
		"c\2\2op\7k\2\2pq\7p\2\2q \3\2\2\2rs\7k\2\2st\7h\2\2t\"\3\2\2\2uv\7g\2"+
		"\2vw\7n\2\2wx\7u\2\2xy\7g\2\2y$\3\2\2\2z{\7?\2\2{&\3\2\2\2|}\7/\2\2}("+
		"\3\2\2\2~\177\7-\2\2\177*\3\2\2\2\u0080\u0081\7?\2\2\u0081\u0082\7?\2"+
		"\2\u0082,\3\2\2\2\u0083\u0084\7#\2\2\u0084\u0085\7?\2\2\u0085.\3\2\2\2"+
		"\u0086\u0087\7>\2\2\u0087\60\3\2\2\2\u0088\u0089\7@\2\2\u0089\62\3\2\2"+
		"\2\u008a\u008b\7>\2\2\u008b\u008c\7?\2\2\u008c\64\3\2\2\2\u008d\u008e"+
		"\7@\2\2\u008e\u008f\7?\2\2\u008f\66\3\2\2\2\u0090\u0091\7>\2\2\u0091\u0092"+
		"\7>\2\2\u00928\3\2\2\2\u0093\u0094\7@\2\2\u0094\u0095\7@\2\2\u0095:\3"+
		"\2\2\2\u0096\u0097\7,\2\2\u0097<\3\2\2\2\u0098\u0099\7\61\2\2\u0099>\3"+
		"\2\2\2\u009a\u009b\7\'\2\2\u009b@\3\2\2\2\u009c\u009e\t\4\2\2\u009d\u009c"+
		"\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u009d\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0"+
		"B\3\2\2\2\5\2F\u009f\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}