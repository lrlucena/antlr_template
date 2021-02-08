package language;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import language.parser.*;

public class Main {

	private static Optional<ParseTree> parse(String file) {
		final CharStream input;
		try {
			input = CharStreams.fromFileName(file);
		} catch (IOException e) {
			return Optional.empty();
		}
		final LanguageLexer lexer = new LanguageLexer(input);
		final CommonTokenStream tokens = new CommonTokenStream(lexer);
		final LanguageParser parser = new LanguageParser(tokens);
		return Optional.of(parser.program());
	}

	public static void main(String ... args) {
		final String filename;
		if (args.length > 0) {
			filename = args[0];
		} else {
			System.out.print("Enter file name: ");
			Scanner scanner = new Scanner(System.in);
			filename = scanner.nextLine();
			scanner.close();
		}

		final Optional<ParseTree> tree = parse(filename);
		if (tree.isPresent()) {
			final ParseTreeWalker walker = new ParseTreeWalker();
			final MyListener<Integer> myListener = new MyListener<>();
			walker.walk(myListener, tree.get());
			int program = myListener.getProgram();
			System.out.println(program);
		} else {
			System.out.println("File not found!");
		}
	}
}
