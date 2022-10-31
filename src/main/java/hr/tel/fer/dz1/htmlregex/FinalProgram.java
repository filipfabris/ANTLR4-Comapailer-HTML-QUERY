package hr.tel.fer.dz1.htmlregex;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import hr.fer.ilj.antlr.RequestLexer;
import hr.fer.ilj.antlr.RequestParser;


public class FinalProgram {

	public static void main(String[] args) {

		// Read file
		String text = null;
		try {

			text = Files.readString(Path.of("input.txt"), StandardCharsets.UTF_8);
			
		} catch (IOException e) {
			System.out.println(e.getMessage());

		}
		RegexHTML regex = new RegexHTML(text);

		
		System.out.println("Please enter input, use HELP for command list");
		Scanner sc = new Scanner(new InputStreamReader(System.in, StandardCharsets.UTF_8));
		while (true) {
			try {
				System.out.print("> ");
				String input = sc.nextLine().trim();

				if (input.equalsIgnoreCase("exit")) {
					System.out.println("Goodbye!");
					break;
				}
				
				if (input.isBlank()) {
					continue;
				}
				
				parseExpressions(input, regex);
				
				
			} catch (RuntimeException e) {
				System.err.println("Input error\n");
				System.err.println(e.getMessage());
			}
		}

		sc.close();
		

	}

	private static void parseExpressions(String request, RegexHTML regex) {
		CharStream input = CharStreams.fromString(request);
		// ANTLRInputStream input = new ANTLRInputStream(request);

		RequestLexer lexer = new RequestLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		RequestParser parser = new RequestParser(tokens);
		ParseTree tree = parser.request();

		ParseTreeWalker walker = new ParseTreeWalker();
		ExpressionExtractor listener = new ExpressionExtractor(regex);
		walker.walk(listener, tree);

		if (listener.getError() != null)
			throw new RuntimeException(listener.getError());

	}
	
}
