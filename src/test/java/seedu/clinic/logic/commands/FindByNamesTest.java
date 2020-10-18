package seedu.clinic.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.clinic.commons.core.Messages.MESSAGE_SUPPLIERS_LISTED_OVERVIEW;
import static seedu.clinic.commons.core.Messages.MESSAGE_WAREHOUSE_LISTED_OVERVIEW;
import static seedu.clinic.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.clinic.testutil.Assert.assertThrows;
import static seedu.clinic.testutil.TypicalSupplier.CARL;
import static seedu.clinic.testutil.TypicalSupplier.ELLE;
import static seedu.clinic.testutil.TypicalSupplier.FIONA;
import static seedu.clinic.testutil.TypicalSupplier.getTypicalClinic;
import static seedu.clinic.testutil.TypicalWarehouse.getTypicalWarehouseOnlyClinic;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.clinic.logic.parser.FindByNamesCommandParser;
import seedu.clinic.logic.parser.exceptions.ParseException;
import seedu.clinic.model.Model;
import seedu.clinic.model.ModelManager;
import seedu.clinic.model.UserPrefs;
import seedu.clinic.model.attribute.NameContainsKeywordsPredicateForSupplier;
import seedu.clinic.model.attribute.NameContainsKeywordsPredicateForWarehouse;
import seedu.clinic.testutil.TypicalWarehouse;

/**
 * Contains integration tests (interaction with the Model) for {@code FindByNamesCommand}.
 */
public class FindByNamesTest {
    private Model modelForSupplier = new ModelManager(getTypicalClinic(), new UserPrefs());
    private Model expectedModelForSupplier = new ModelManager(getTypicalClinic(), new UserPrefs());
    private Model modelForWarehouse = new ModelManager(getTypicalWarehouseOnlyClinic(), new UserPrefs());
    private Model expectedModelForWarehouse = new ModelManager(getTypicalWarehouseOnlyClinic(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicateForSupplier firstSupplierPredicate =
                new NameContainsKeywordsPredicateForSupplier(Collections.singletonList("first"));
        NameContainsKeywordsPredicateForSupplier secondSupplierPredicate =
                new NameContainsKeywordsPredicateForSupplier(Collections.singletonList("second"));

        NameContainsKeywordsPredicateForWarehouse firstWarehousePredicate =
                new NameContainsKeywordsPredicateForWarehouse(Collections.singletonList("first"));
        NameContainsKeywordsPredicateForWarehouse secondWarehousePredicate =
                new NameContainsKeywordsPredicateForWarehouse(Collections.singletonList("second"));

        FindByNamesCommand firstFindCommand = new FindByNamesCommand(firstSupplierPredicate);
        FindByNamesCommand secondFindCommand = new FindByNamesCommand(secondSupplierPredicate);
        FindByNamesCommand thirdFindCommand = new FindByNamesCommand(firstWarehousePredicate);
        FindByNamesCommand fourthFindCommand = new FindByNamesCommand(secondWarehousePredicate);

        // same object -> returns true
        assertTrue(firstFindCommand.equals(firstFindCommand));
        assertTrue(secondFindCommand.equals(secondFindCommand));
        assertTrue(thirdFindCommand.equals(thirdFindCommand));
        assertTrue(fourthFindCommand.equals(fourthFindCommand));

        // same values -> returns true
        FindByNamesCommand firstFindCommandCopy = new FindByNamesCommand(firstSupplierPredicate);
        assertTrue(firstFindCommand.equals(firstFindCommandCopy));
        FindByNamesCommand thirdFindCommandCopy = new FindByNamesCommand(firstWarehousePredicate);
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
        assertThrows(ParseException.class, () -> new FindByNamesCommandParser().parse(""));
    }

    @Test
    public void execute_multipleKeywords_multipleSuppliersFound() {
        String expectedMessage = String.format(MESSAGE_SUPPLIERS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicateForSupplier predicate =
                prepareSupplierPredicate("supplier carl elle fiona");
        FindByNamesCommand command = new FindByNamesCommand(predicate);
        expectedModelForSupplier.updateFilteredSupplierList(predicate);
        assertCommandSuccess(command, modelForSupplier, expectedMessage, expectedModelForSupplier);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), modelForSupplier.getFilteredSupplierList());
    }

    @Test
    public void execute_multipleKeywords_multipleWarehousesFound() {
        String expectedMessage = String.format(MESSAGE_WAREHOUSE_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicateForWarehouse warehousePredicate =
                prepareWarehousePredicate("warehouse Carl Elle Fiona");
        FindByNamesCommand command = new FindByNamesCommand(warehousePredicate);
        expectedModelForWarehouse.updateFilteredWarehouseList(warehousePredicate);
        assertCommandSuccess(command, modelForWarehouse, expectedMessage, expectedModelForWarehouse);
        assertEquals(Arrays.asList(TypicalWarehouse.CARL, TypicalWarehouse.ELLE, TypicalWarehouse.FIONA),
                modelForWarehouse.getFilteredWarehouseList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicateForSupplier}.
     */
    private NameContainsKeywordsPredicateForSupplier prepareSupplierPredicate(String userInput) {
        return new NameContainsKeywordsPredicateForSupplier(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicateForWarehouse}.
     */
    private NameContainsKeywordsPredicateForWarehouse prepareWarehousePredicate(String userInput) {
        String[] nameKeywords = userInput.split("\\s+");
        return new NameContainsKeywordsPredicateForWarehouse(Arrays.asList(nameKeywords).subList(1,
                nameKeywords.length));
    }
}
