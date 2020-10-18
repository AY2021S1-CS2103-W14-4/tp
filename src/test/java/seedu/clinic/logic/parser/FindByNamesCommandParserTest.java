package seedu.clinic.logic.parser;

import static seedu.clinic.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.clinic.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.clinic.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.clinic.logic.commands.FindByNamesCommand;
import seedu.clinic.model.attribute.NameContainsKeywordsPredicateForSupplier;
import seedu.clinic.model.attribute.NameContainsKeywordsPredicateForWarehouse;

/**
 * Tests if {@code FindByNamesCommand} parses the arguments correctly for {@code Supplier} and {@code Warehouse}.
 */
public class FindByNamesCommandParserTest {

    private FindByNamesCommandParser parser = new FindByNamesCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindByNamesCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidType_throwsParseException() {
        assertParseFailure(parser, "s", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindByNamesCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "w", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindByNamesCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "suppliers", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindByNamesCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "warehouses", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindByNamesCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "suppliersqwerty alex", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindByNamesCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "warehouses roy", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindByNamesCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingKeyword_throwsParseException() {
        assertParseFailure(parser, "supplier", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindByNamesCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "warehouse", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindByNamesCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindByNamesCommandForSupplier() {
        // no leading and trailing whitespaces
        FindByNamesCommand expectedFindByNamesCommand =
                new FindByNamesCommand(new NameContainsKeywordsPredicateForSupplier(Arrays.asList("alex")));
        assertParseSuccess(parser, "supplier alex", expectedFindByNamesCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n supplier \n \t alex  \t", expectedFindByNamesCommand);
    }

    @Test
    public void parse_validArgs_returnsFindByNamesCommandForWarehouse() {
        // no leading and trailing whitespaces
        FindByNamesCommand expectedFindByNamesCommand =
                new FindByNamesCommand(new NameContainsKeywordsPredicateForWarehouse(Arrays.asList("bernice")));
        assertParseSuccess(parser, "warehouse bernice", expectedFindByNamesCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n warehouse \n \t bernice  \t", expectedFindByNamesCommand);
    }

}
