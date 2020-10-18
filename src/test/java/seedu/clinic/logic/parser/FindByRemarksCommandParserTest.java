package seedu.clinic.logic.parser;

import static seedu.clinic.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.clinic.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.clinic.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.clinic.logic.commands.FindByRemarksCommand;
import seedu.clinic.model.supplier.SupplierRemarkContainsKeywordsPredicate;
import seedu.clinic.model.warehouse.WarehouseRemarkContainsKeywordsPredicate;

/**
 * Tests if {@code FindByRemarksCommand} parses the arguments correctly for {@code Supplier} and {@code Warehouse}.
 */
public class FindByRemarksCommandParserTest {

    private FindByRemarksCommandParser parser = new FindByRemarksCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindByRemarksCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidType_throwsParseException() {
        assertParseFailure(parser, "s", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindByRemarksCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "w", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindByRemarksCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "suppliers", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindByRemarksCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "warehouses", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindByRemarksCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "suppliersqwerty discounts", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindByRemarksCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "warehouses smallest", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindByRemarksCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingKeyword_throwsParseException() {
        assertParseFailure(parser, "supplier", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindByRemarksCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "warehouse", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindByRemarksCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindByRemarksCommandForSupplier() {
        // no leading and trailing whitespaces
        FindByRemarksCommand expectedFindByRemarksCommand =
                new FindByRemarksCommand(new SupplierRemarkContainsKeywordsPredicate(Arrays.asList("long", "term")));
        assertParseSuccess(parser, "supplier long term", expectedFindByRemarksCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n supplier \n \t long term \t", expectedFindByRemarksCommand);
    }

    @Test
    public void parse_validArgs_returnsFindByRemarksCommandForWarehouse() {
        // no leading and trailing whitespaces
        FindByRemarksCommand expectedFindByRemarksCommand =
                new FindByRemarksCommand(new WarehouseRemarkContainsKeywordsPredicate(Arrays.asList("smallest")));
        assertParseSuccess(parser, "warehouse smallest", expectedFindByRemarksCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n warehouse \n \t smallest  \t", expectedFindByRemarksCommand);
    }

}
