package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditSupplierDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.attribute.NameContainsKeywordsPredicateForSupplier;
import seedu.address.model.supplier.Supplier;
import seedu.address.testutil.EditSupplierDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {
    // Warehouse test samples
    public static final String VALID_WAREHOUSE_NAME_A = "Warehouse A";
    public static final String VALID_WAREHOUSE_NAME_B = "Warehouse B";
    public static final String VALID_WAREHOUSE_PHONE_A = "11111111";
    public static final String VALID_WAREHOUSE_PHONE_B = "22222222";
    public static final String VALID_WAREHOUSE_ADDRESS_A = "Block 312, Amy Street 1";
    public static final String VALID_WAREHOUSE_ADDRESS_B = "Block 123, Bobby Street 3";
    public static final String VALID_WAREHOUSE_REMARK_A = "Big";
    public static final String VALID_WAREHOUSE_REMARK_B = "Small";
    public static final String VALID_WAREHOUSE_PRODUCT_NAME_A = "Panadol";
    public static final String VALID_WAREHOUSE_PRODUCT_NAME_B = "Aspirin";
    public static final int VALID_WAREHOUSE_PRODUCT_QUANTITY_A = 10;
    public static final int VALID_WAREHOUSE_PRODUCT_QUANTITY_B = 20;
    public static final String VALID_WAREHOUSE_PRODUCT_TAG_FEVER = "fever";
    public static final String VALID_WAREHOUSE_PRODUCT_TAG_HEADACHE = "headache";

    // empty string not allowed for addresses
    public static final String INVALID_WAREHOUSE_ADDRESS_DESC = " " + PREFIX_ADDRESS;

    // Old test samples
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_FEVER = "fever";
    public static final String VALID_TAG_ANTIBIOTICS = "antibiotics";
    public static final String VALID_REMARK_AMY = "Sells a diverse range of products";
    public static final String VALID_REMARK_BOB = "Long term partner";
    public static final String VALID_PRODUCT_NAME_PANADOL = "Panadol";
    public static final String VALID_PRODUCT_NAME_ASPIRIN = "Aspirin";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FEVER = " " + PREFIX_TAG + VALID_TAG_FEVER;
    public static final String TAG_DESC_ANTIBIOTICS = " " + PREFIX_TAG + VALID_TAG_ANTIBIOTICS;
    public static final String REMARK_DESC_AMY = " " + PREFIX_REMARK + VALID_REMARK_AMY;
    public static final String REMARK_DESC_BOB = " " + PREFIX_REMARK + VALID_REMARK_BOB;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_REMARK_DESC = " " + PREFIX_REMARK; // empty string not allowed for remarks

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditSupplierDescriptor DESC_AMY;
    public static final EditSupplierDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditSupplierDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withRemark(VALID_REMARK_AMY)
                .withProducts(Map.of(VALID_PRODUCT_NAME_PANADOL, new String[]{VALID_TAG_FEVER})).build();
        DESC_BOB = new EditSupplierDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withRemark(VALID_REMARK_BOB)
                .withProducts(Map.of(VALID_PRODUCT_NAME_ASPIRIN, new String[]{VALID_TAG_ANTIBIOTICS})).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered supplier list and selected supplier in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Supplier> expectedFilteredList = new ArrayList<>(actualModel.getFilteredSupplierList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredSupplierList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the supplier at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showSupplierAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredSupplierList().size());

        Supplier supplier = model.getFilteredSupplierList().get(targetIndex.getZeroBased());
        final String[] splitName = supplier.getName().fullName.split("\\s+");
        model.updateFilteredSupplierList(new NameContainsKeywordsPredicateForSupplier(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredSupplierList().size());
    }

}
