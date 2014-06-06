package com.aravind.ruthvik.analytics.util;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * A utility class to build the command-line options.
 * 
 * @author aravind.ruthvik
 *
 */
public class CommandLineOptionBuilder {

	/**
	 * Builds the CLI options
	 * 
	 * @param name
	 * @param hasArg
	 * @param argName
	 * @param description
	 * @param isRequired
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static Option build(String name, boolean hasArg, String argName,
			String description, boolean isRequired) {
		OptionBuilder builder = OptionBuilder.withDescription(description)
				.isRequired(isRequired);
		builder = hasArg ? builder.hasArg().withArgName(argName) : builder;
		return builder.create(name);
	}

	/**
	 * Parses the given command line options and arguments.
	 * 
	 * @param arguments
	 * @param options
	 * @return
	 * @throws ParseException
	 */
	public static CommandLine parseCommandLine(String[] arguments,
			Options options) throws ParseException {
		CommandLineParser cliParser = new GnuParser();
		CommandLine line = null;
		line = cliParser.parse(options, arguments);
		return line;
	}
	
	private CommandLineOptionBuilder() {
	}
}
