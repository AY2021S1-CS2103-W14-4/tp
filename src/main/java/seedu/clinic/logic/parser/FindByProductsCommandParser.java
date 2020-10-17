package seedu.clinic.logic.parser;

import static seedu.clinic.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.List;

import seedu.clinic.logic.commands.FindByProductsCommand;
import seedu.clinic.logic.parser.exceptions.ParseException;
import seedu.clinic.model.supplier.SupplierProductsContainKeywordsPredicate;
import seedu.clinic.model.warehouse.WarehouseProductsContainKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindByProductsCommand object
 */
public class FindByProductsCommandParser implements Parser<FindByProductsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindByProductsCommand
     * and returns a FindByProductsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindByProductsCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindByProductsCommand.MESSAGE_USAGE));
        }

        String[] productNameKeywords = trimmedArgs.split("\\s+");
        String type = productNameKeywords[0].toLowerCase();

        // Ensures that the user enters the type and at least 1 keyword
        if (!(type.equals("supplier") || type.equals("warehouse")) || productNameKeywords.length < 2) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindByProductsCommand.MESSAGE_USAGE));
        }

        List<String> keywords = Arrays.asList(productNameKeywords);
        if (type.equals("supplier")) {
            return new FindByProductsCommand(new SupplierProductsContainKeywordsPredicate(keywords));
        } else {
            return new FindByProductsCommand(new WarehouseProductsContainKeywordsPredicate(keywords));
        }
    }

}
