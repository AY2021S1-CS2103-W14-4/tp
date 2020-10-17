package seedu.clinic.logic.parser;

import static seedu.clinic.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.List;

import seedu.clinic.logic.commands.FindByNamesCommand;
import seedu.clinic.logic.parser.exceptions.ParseException;
import seedu.clinic.model.attribute.NameContainsKeywordsPredicateForSupplier;
import seedu.clinic.model.attribute.NameContainsKeywordsPredicateForWarehouse;

/**
 * Parses input arguments and creates a new FindByNamesCommand object
 */
public class FindByNamesCommandParser implements Parser<FindByNamesCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindByNamesCommand
     * and returns a FindByNamesCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public FindByNamesCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindByNamesCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");
        String type = nameKeywords[0].toLowerCase();

        // Ensures that the user enters the type and at least 1 keyword
        if (!(type.equals("supplier") || type.equals("warehouse")) || nameKeywords.length < 2) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindByNamesCommand.MESSAGE_USAGE));
        }

        List<String> keywords = Arrays.asList(nameKeywords).subList(1, nameKeywords.length);
        if (type.equals("supplier")) {
            return new FindByNamesCommand(new NameContainsKeywordsPredicateForSupplier(keywords));
        }

        return new FindByNamesCommand(new NameContainsKeywordsPredicateForWarehouse(keywords));
    }

}
