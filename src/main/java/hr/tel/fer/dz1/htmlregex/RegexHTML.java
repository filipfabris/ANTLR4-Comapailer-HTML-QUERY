package hr.tel.fer.dz1.htmlregex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexHTML {
	String text;
	String path;

	public RegexHTML(Path path) {
		try {
			this.text = Files.readString(path);
		} catch (IOException e) {
			System.err.println("Could not read path");
			System.exit(0);
		}
	}
	
	public RegexHTML(String text) {
		this.text = text;
	}
	
	public String All() {
		return text;
	}

	public List<String> findTag(String tag) {

		List<String> output = new LinkedList<>();

		String regex = "<" + tag + "[\\s\\w=\",]*>" + "((.|\\n|\\s)*?)" + "<\\/" + tag + ">";

		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(text);

		while (matcher.find()) {
			output.add(matcher.group(1));
		}

		return output;

	}

	public List<String> findIP() {

		List<String> output = new LinkedList<>();

		String regex = "[a-z]*" + "(" + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\."
				+ "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\."
				+ "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)" + ")" + "[a-z]*";

		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(text);

		while (matcher.find()) {
			output.add(matcher.group(1));
		}

		return output;

	}

	public List<String> findEmail() {

		List<String> output = new LinkedList<>();

		String regex = "\s?" + "(" + "([\\w-\\.]+)" + "@" + "([\\w-\\.]+)+"
		// + "\\."
				+ "(([\\w-]){2,4})" + ")" + "\s?";

		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(text);

		while (matcher.find()) {
			output.add(matcher.group(1));
		}

		return output;

	}

	public List<String> findDate() {

		List<String> output = new LinkedList<>();

		String regex = "([012][0-9]|3[01])" + "(\\/)" + "([012][0-9]|3[01]|[0-9])" + "(\\/)" + "([0-9]{4})";

		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(text);

		while (matcher.find()) {
			output.add(matcher.group());
		}

		return output;

	}

	public List<String> findTel() {

		List<String> output = new LinkedList<>();

		String regex = "[\\d]{3}?[ -][\\d]{1,3}[ -][\\d]{3,4}[ -][\\d]{3,4}";

		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(text);

		while (matcher.find()) {
			output.add(matcher.group());
		}

		return output;

	}
	
	public String help() {
		StringBuilder sb = new StringBuilder();

		sb.append("ID:00        		COMMAND:ALL 				FUNCTION:Return the entire HTML file \n");
		sb.append("ID:01 [TAG]  		COMMAND:ALL <tag> 			FUNCTION:Returns the contents of all given tags \n");
		sb.append("ID:02        		COMMAND:ALL email 			FUNCTION:Returns a list of all email addresses \n");
		sb.append("ID:03        		COMMAND:ALL ip 				FUNCTION:Returns a list of all IPv4 addresses \n");
		sb.append("ID:04        		COMMAND:ALL date 			FUNCTION:Returns a list of all dates in the format: dd/mm/yyyy \n");
		sb.append("ID:05        		COMMAND:ALL tel 			FUNCTION:Returns a list of all phone numbers \n");
		sb.append("ID:06 [TAG] [COUNT] 	COMMAND:ONLY <tag> count 		FUNCTION:Returns the first number of contents of the specified <tag> tag \n");
		sb.append("ID:07 [COUNT] 		COMMAND:ONLY email count 		FUNCTION:Returns the first number of email addresses \n");
		sb.append("ID:08 [COUNT] 		COMMAND:ONLY IP count 			FUNCTION:Returns the first number of IPv4 addresses \n");
		sb.append("ID:09 [COUNT] 		COMMAND:ONLY date count 		FUNCTION:Returns the first number of dates \n");
		sb.append("ID:10 [COUNT] 		COMMAND:ONLY tel count 			FUNCTION:Returns the first number of phone numbers \n");
		sb.append("ID:11 		 	COMMAND:HELP		 		FUNCTION:Returns a list of options that have been implemented \n");
		sb.append("ID:12 [ID] 	 	COMMAND:REGEX ID     			FUNCTION:Returns the appearance of the regular expression for the option whose ID is equal to the given argument \n");
		sb.append("ID:13 		 	COMMAND:EXIT		 		FUNCTION:Stops the program \n");

		String output = sb.toString();
		
		return output;
	}

}
