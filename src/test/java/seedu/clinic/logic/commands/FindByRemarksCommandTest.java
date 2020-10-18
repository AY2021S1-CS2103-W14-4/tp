package seedu.clinic.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.clinic.commons.core.Messages.MESSAGE_SUPPLIERS_LISTED_OVERVIEW;
import static seedu.clinic.commons.core.Messages.MESSAGE_WAREHOUSE_LISTED_OVERVIEW;
import static seedu.clinic.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.clinic.testutil.Assert.assertThrows;
import static seedu.clinic.testutil.TypicalSupplier.ELLE;
import static seedu.clinic.testutil.TypicalSupplier.FIONA;
import static seedu.clinic.testutil.TypicalSupplier.getTypicalClinic;
import static seedu.clinic.testutil.TypicalWarehouse.getTypicalWarehouseOnlyClinic;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.clinic.logic.parser.FindByRemarksCommandParser;
import seedu.clinic.logic.parser.exceptions.ParseException;
import seedu.clinic.model.Model;
import seedu.clinic.model.ModelManager;
import seedu.clinic.model.UserPrefs;
import seedu.clinic.model.supplier.SupplierRemarkContainsKeywordsPredicate;
import seedu.clinic.model.warehouse.WarehouseRemarkContainsKeywordsPredicate;
import seedu.clinic.testutil.TypicalWarehouse;

/**
 * Contains integration tests (interaction with the Model) for {@code FindByRemarksCommand}.
 */
public class FindByRemarksCommandTest {
    private Model modelForSupplier = new ModelManager(getTypicalClinic(), new UserPrefs());
    private Model expectedModelForSupplier = new ModelManager(getTypicalClinic(), new UserPrefs());
    private Model modelForWarehouse = new ModelManager(getTypicalWarehouseOnlyClinic(), new UserPrefs());
    private Model expectedModelForWarehouse = new ModelManager(getTypicalWarehouseOnlyClinic(), new UserPrefs());

    @Test
    public void equals() {
        SupplierRemarkContainsKeywordsPredicate firstSupplierPredicate =
                new SupplierRemarkContainsKeywordsPredicate(Collections.singletonList("first"));
        SupplierRemarkContainsKeywordsPredicate secondSupplierPredicate =
                new SupplierRemarkContainsKeywordsPredicate(Collections.singletonList("second"));

        WarehouseRemarkContainsKeywordsPredicate firstWarehousePredicate =
                new WarehouseRemarkContainsKeywordsPredicate(Collections.singletonList("first"));
        WarehouseRemarkContainsKeywordsPredicate secondWarehousePredicate =
                new WarehouseRemarkContainsKeywordsPredicate(Collections.singletonList("second"));

        FindByRemarksCommand firstFindCommand = new FindByRemarksCommand(firstSupplierPredicate);
        FindByRemarksCommand secondFindCommand = new FindByRemarksCommand(secondSupplierPredicate);
        FindByRemarksCommand thirdFindCommand = new FindByRemarksCommand(firstWarehousePredicate);
        FindByRemarksCommand fourthFindCommand = new FindByRemarksCommand(secondWarehousePredicate);

        // same object -> returns true
        assertTrue(firstFindCommand.equals(firstFindCommand));
        assertTrue(secondFindCommand.equals(secondFindCommand));
        assertTrue(thirdFindCommand.equals(thirdFindCommand));
        assertTrue(fourthFindCommand.equals(fourthFindCommand));

        // same values -> returns true
        FindByRemarksCommand firstFindCommandCopy = new FindByRemarksCommand(firstSupplierPredicate);
        assertTrue(firstFindCommand.equals(firstFindCommandCopy));
        FindByRemarksCommand thirdFindCommandCopy = new FindByRemarksCommand(firstWarehousePredicate);
        assertTrue(thirdFindCommand.equals(thirdFindCommandCopy));

        // different types -> returns false
        assertFalse(firstFindCommand.equals(1));
        assertFalse(secondFindCommand.equals(1));
        assertFalse(thirdFindCommand.equals(1));
        assertFalse(fourthFindCommand.equals(1));

        // null -> returns false
        assertFalse(firstFindCommand.equals(null));
        assertFalse(secondFindCommand.equals(null));
        assertFalse(thirdFindCommand.equals(null));
        assertFalse(fourthFindCommand.equals(null));

        // different supplier -> returns false
        assertFalse(firstFindCommand.equals(secondFindCommand));

        // different warehouse -> returns false
        assertFalse(thirdFindCommand.equals(fourthFindCommand));
    }

    @Test
    public void execute_noTypeAndZeroKeywords_invalidCommand() {
        assertThrows(ParseException.class, () -> new FindByRemarksCommandParser().parse(""));
    }

    @Test
    public void execute_multipleKeywords_multipleSuppliersFound() {
        String expectedMessage = String.format(MESSAGE_SUPPLIERS_LISTED_OVERVIEW, 2);
        SupplierRemarkContainsKeywordsPredicate predicate =
                prepareSupplierPredicate("supplier industry leader");
        FindByRemarksCommand command = new FindByRemarksCommand(predicate);
        expectedModelForSupplier.updateFilteredSupplierList(predicate);
        assertCommandSuccess(command, modelForSupplier, expectedMessage, expectedModelForSupplier);
        assertEquals(Arrays.asList(ELLE, FIONA), modelForSupplier.getFilteredSupplierList());
    }

    @Test
    public void execute_multipleKeywords_multipleWarehousesFound() {
        String expectedMessage = String.format(MESSAGE_WAREHOUSE_LISTED_OVERVIEW, 2);
        WarehouseRemarkContainsKeywordsPredicate warehousePredicate =
                prepareWarehousePredicate("warehouse one of the biggest");
        FindByRemarksCommand command = new FindByRemarksCommand(warehousePredicate);
        expectedModelForWarehouse.updateFilteredWarehouseList(warehousePredicate);
        assertCommandSuccess(command, modelForWarehouse, expectedMessage, expectedModelForWarehouse);
        assertEquals(Arrays.asList(TypicalWarehouse.ELLE, TypicalWarehouse.FIONA),
                modelForWarehouse.getFilteredWarehouseList());
    }

    /**
     * Parses {@code userInput} into a {@code SupplierRemarkContainsKeywordsPredicate}.
     */
    private SupplierRemarkContainsKeywordsPredicate prepareSupplierPredicate(String userInput) {
        String[] remarkKeywords = userInput.split("\\s+");
        return new SupplierRemarkContainsKeywordsPredicate(Arrays.asList(remarkKeywords).subList(1,
                remarkKeywords.length));
    }

    /**
     * Parses {@code userInput} into a {@code WarehouseRemarkContainsKeywordsPredicate}.
     */
    private WarehouseRemarkContainsKeywordsPredicate prepareWarehousePredicate(String userInput) {
        String[] remarkKeywords = userInput.split("\\s+");
        return new WarehouseRemarkContainsKeywordsPredicate(Arrays.asList(remarkKeywords).subList(1,
                remarkKeywords.length));
    }
}
