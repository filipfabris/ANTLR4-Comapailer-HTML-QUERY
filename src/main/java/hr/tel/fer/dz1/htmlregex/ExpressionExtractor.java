package hr.tel.fer.dz1.htmlregex;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;

import hr.fer.ilj.antlr.RequestBaseListener;
import hr.fer.ilj.antlr.RequestParser.ALLTelIDContext;
import hr.fer.ilj.antlr.RequestParser.AllContext;
import hr.fer.ilj.antlr.RequestParser.AllDateContext;
import hr.fer.ilj.antlr.RequestParser.AllDateIDContext;
import hr.fer.ilj.antlr.RequestParser.AllEmailContext;
import hr.fer.ilj.antlr.RequestParser.AllEmailIDContext;
import hr.fer.ilj.antlr.RequestParser.AllIpContext;
import hr.fer.ilj.antlr.RequestParser.AllIpIDContext;
import hr.fer.ilj.antlr.RequestParser.AllTagContext;
import hr.fer.ilj.antlr.RequestParser.AllTagIDContext;
import hr.fer.ilj.antlr.RequestParser.AllTelContext;
import hr.fer.ilj.antlr.RequestParser.EXITContext;
import hr.fer.ilj.antlr.RequestParser.HELPContext;
import hr.fer.ilj.antlr.RequestParser.RegexIDContext;
import hr.fer.ilj.antlr.RequestParser.TagContext;
import hr.fer.ilj.antlr.RequestParser.ValueContext;

public class ExpressionExtractor extends RequestBaseListener {
	
	String error;
	RegexHTML regex;
	PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
	
	public ExpressionExtractor(RegexHTML regex) {
		this.regex = regex;
	}
	
	  public String getError() {
		    return error;
		  }

	@Override
	public void exitAll(AllContext ctx) {
		String ouput = this.regex.All();
		out.println(ouput);
	}

	@Override
	public void exitAllTag(AllTagContext ctx) {
	    String tag = ctx.tag().getText();
	    tag = tag.substring(1,tag.length()-1);
	    
	    
	    var output = this.regex.findTag(tag);
	    
	    output.forEach(element -> {
			out.println(element);
		});
	    
	}

	@Override
	public void exitAllEmail(AllEmailContext ctx) {
	    
	    var output = this.regex.findEmail();
	    
	    output.forEach(element -> {
			out.println(element);
		});
		
	}

	@Override
	public void exitAllIp(AllIpContext ctx) {
	    var output = this.regex.findIP();
	    
	    output.forEach(element -> {
			out.println(element);
		});
	}

	@Override
	public void exitAllDate(AllDateContext ctx) {
	    var output = this.regex.findDate();
	    
	    output.forEach(element -> {
			out.println(element);
		});
	}

	@Override
	public void exitAllTel(AllTelContext ctx) {
	    var output = this.regex.findTel();
	    
	    output.forEach(element -> {
			out.println(element);
		});
	}

	@Override
	public void exitAllTagID(AllTagIDContext ctx) {
		
	    String tag = ctx.tag().getText();
	    tag = tag.substring(1,tag.length()-1);
	    
	    String count = ctx.value().getText();
	    int number = Integer.parseInt(count);
	    
	    var output = this.regex.findTag(tag);
	    
	    
	    output.stream().limit(number).forEach(element -> {
			out.println(element);
		});
	    
	}

	@Override
	public void exitAllEmailID(AllEmailIDContext ctx) {
	    
	    String count = ctx.value().getText();
	    int number = Integer.parseInt(count);
	    
	    var output = this.regex.findEmail();
	    
	    
	    output.stream().limit(number).forEach(element -> {
			out.println(element);
		});
	}

   
	@Override
	public void exitAllIpID(AllIpIDContext ctx) {

		 String count = ctx.value().getText();
		    int number = Integer.parseInt(count);
		    
		    var output = this.regex.findIP();
		    
		    
		    output.stream().limit(number).forEach(element -> {
				out.println(element);
			});
		
		
	}

	@Override
	public void exitAllDateID(AllDateIDContext ctx) {

		 String count = ctx.value().getText();
		    int number = Integer.parseInt(count);
		    
		    var output = this.regex.findDate();
		    
		    
		    output.stream().limit(number).forEach(element -> {
				out.println(element);
			});
	}

	@Override
	public void exitALLTelID(ALLTelIDContext ctx) {

		 String count = ctx.value().getText();
		    int number = Integer.parseInt(count);
		    
		    var output = this.regex.findTel();
		    
		    
		    output.stream().limit(number).forEach(element -> {
				out.println(element);
			});
	}

	@Override
	public void exitHELP(HELPContext ctx) {
		var output = this.regex.help();
		out.println(output);
	}

	  @Override
	  public void visitErrorNode(ErrorNode node) {
	    error = "Request not in format!";
	  }
	
}
