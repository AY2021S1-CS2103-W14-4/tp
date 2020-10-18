package seedu.clinic.logic.parser;

import static seedu.clinic.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.List;

import seedu.clinic.logic.commands.FindByRemarksCommand;
import seedu.clinic.logic.parser.exceptions.ParseException;
import seedu.clinic.model.supplier.SupplierRemarkContainsKeywordsPredicate;
import seedu.clinic.model.warehouse.WarehouseRemarkContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindByRemarksCommand object
 */
public class FindByRemarksCommandParser implements Parser<FindByRemarksCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindByRemarksCommand
     * and returns a FindByRemarksCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public FindByRemarksCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindByRemarksCommand.MESSAGE_USAGE));
        }

        String[] remarkKeywords = trimmedArgs.split("\\s+");
        String type = remarkKeywords[0].toLowerCase();

        // Ensures that the user enters the type and at least 1 keyword
        if (!(type.equals("supplier") || type.equals("warehouse")) || remarkKeywords.length < 2) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindByRemarksCommand.MESSAGE_USAGE));
        }

        List<String> keywords = Arrays.asList(remarkKeywords).subList(1, remarkKeywords.length);
        if (type.equals("supplier")) {
            return new FindByRemarksCommand(new SupplierRemarkContainsKeywordsPredicate(keywords));
        }

        return new FindByRemarksCommand(new WarehouseRemarkContainsKeywordsPredicate(keywords));
    }

}
