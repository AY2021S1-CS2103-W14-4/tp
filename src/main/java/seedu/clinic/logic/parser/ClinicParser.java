package seedu.clinic.logic.parser;

import static seedu.clinic.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.clinic.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.clinic.logic.commands.AddCommand;
import seedu.clinic.logic.commands.AddProductCommand;
import seedu.clinic.logic.commands.ClearCommand;
import seedu.clinic.logic.commands.Command;
import seedu.clinic.logic.commands.DeleteCommand;
import seedu.clinic.logic.commands.EditCommand;
import seedu.clinic.logic.commands.ExitCommand;
import seedu.clinic.logic.commands.FindByNamesCommand;
import seedu.clinic.logic.commands.FindByProductsCommand;
import seedu.clinic.logic.commands.HelpCommand;
import seedu.clinic.logic.commands.ListCommand;
import seedu.clinic.logic.commands.UpdateCommand;
import seedu.clinic.logic.commands.ViewCommand;
import seedu.clinic.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class ClinicParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case AddProductCommand.COMMAND_WORD:
            return new AddProductCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindByNamesCommand.COMMAND_WORD:
            return new FindByNamesCommandParser().parse(arguments);

        case FindByProductsCommand.COMMAND_WORD:
            return new FindByProductsCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommandParser().parse(arguments);

        case UpdateCommand.COMMAND_WORD:
            return new UpdateCommandParser().parse(arguments);

        case ViewCommand.COMMAND_WORD:
            return new ViewCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
