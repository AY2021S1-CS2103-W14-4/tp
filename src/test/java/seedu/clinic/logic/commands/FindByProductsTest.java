package seedu.clinic.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.clinic.commons.core.Messages.MESSAGE_SUPPLIERS_LISTED_OVERVIEW;
import static seedu.clinic.commons.core.Messages.MESSAGE_WAREHOUSE_LISTED_OVERVIEW;
import static seedu.clinic.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.clinic.testutil.TypicalSupplier.CARL;
import static seedu.clinic.testutil.TypicalSupplier.ELLE;
import static seedu.clinic.testutil.TypicalSupplier.FIONA;
import static seedu.clinic.testutil.TypicalSupplier.getTypicalClinic;
import static seedu.clinic.testutil.TypicalWarehouse.getTypicalWarehouseOnlyClinic;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.clinic.model.Model;
import seedu.clinic.model.ModelManager;
import seedu.clinic.model.UserPrefs;
import seedu.clinic.model.supplier.SupplierProductsContainKeywordsPredicate;
import seedu.clinic.model.warehouse.WarehouseProductsContainKeywordsPredicate;
import seedu.clinic.testutil.TypicalWarehouse;

/**
 * Contains integration tests (interaction with the Model) for {@code FindByProductsCommand}.
 */
public class FindByProductsTest {
    private Model modelForSupplier = new ModelManager(getTypicalClinic(), new UserPrefs());
    private Model expectedModelForSupplier = new ModelManager(getTypicalClinic(), new UserPrefs());
    private Model modelForWarehouse = new ModelManager(getTypicalWarehouseOnlyClinic(), new UserPrefs());
    private Model expectedModelForWarehouse = new ModelManager(getTypicalWarehouseOnlyClinic(), new UserPrefs());

    @Test
    public void equals() {
        SupplierProductsContainKeywordsPredicate firstSupplierPredicate =
                new SupplierProductsContainKeywordsPredicate(Collections.singletonList("first"));
        SupplierProductsContainKeywordsPredicate secondSupplierPredicate =
                new SupplierProductsContainKeywordsPredicate(Collections.singletonList("second"));

        WarehouseProductsContainKeywordsPredicate firstWarehousePredicate =
                new WarehouseProductsContainKeywordsPredicate(Collections.singletonList("first"));
        WarehouseProductsContainKeywordsPredicate secondWarehousePredicate =
                new WarehouseProductsContainKeywordsPredicate(Collections.singletonList("second"));

        FindByProductsCommand firstFindCommand = new FindByProductsCommand(firstSupplierPredicate);
        FindByProductsCommand secondFindCommand = new FindByProductsCommand(secondSupplierPredicate);
        FindByProductsCommand thirdFindCommand = new FindByProductsCommand(firstWarehousePredicate);
        FindByProductsCommand fourthFindCommand = new FindByProductsCommand(secondWarehousePredicate);

        // same object -> returns true
        assertTrue(firstFindCommand.equals(firstFindCommand));
        assertTrue(thirdFindCommand.equals(thirdFindCommand));

        // same values -> returns true
        FindByProductsCommand firstFindCommandCopy = new FindByProductsCommand(firstSupplierPredicate);
        assertTrue(firstFindCommand.equals(firstFindCommandCopy));
        FindByProductsCommand thirdFindCommandCopy = new FindByProductsCommand(firstWarehousePredicate);
        assertTrue(thirdFindCommand.equals(thirdFindCommandCopy));

        // different types -> returns false
        assertFalse(firstFindCommand.equals(1));
        assertFalse(thirdFindCommand.equals(1));

        // null -> returns false
        assertFalse(firstFindCommand.equals(null));
        assertFalse(thirdFindCommand.equals(null));

        // different supplier -> returns false
        assertFalse(firstFindCommand.equals(secondFindCommand));

        // different warehouse -> returns false
        assertFalse(thirdFindCommand.equals(fourthFindCommand));
    }

    @Test
    public void execute_zeroKeywords_noSupplierFound() {
        String expectedMessage = String.format(MESSAGE_SUPPLIERS_LISTED_OVERVIEW, 0);
        SupplierProductsContainKeywordsPredicate predicate = prepareSupplierPredicate(" ");
        FindByProductsCommand command = new FindByProductsCommand(predicate);
        expectedModelForSupplier.updateFilteredSupplierList(predicate);
        assertCommandSuccess(command, modelForSupplier, expectedMessage, expectedModelForSupplier);
        assertEquals(Collections.emptyList(), modelForSupplier.getFilteredSupplierList());
    }

    @Test
    public void execute_zeroKeywords_noWarehouseFound() {
        String expectedMessage = String.format(MESSAGE_WAREHOUSE_LISTED_OVERVIEW, 0);
        WarehouseProductsContainKeywordsPredicate warehousePredicate = prepareWarehousePredicate(" ");
        FindByProductsCommand command = new FindByProductsCommand(warehousePredicate);
        expectedModelForWarehouse.updateFilteredWarehouseList(warehousePredicate);
        assertCommandSuccess(command, modelForWarehouse, expectedMessage, expectedModelForWarehouse);
        assertEquals(Collections.emptyList(), modelForWarehouse.getFilteredWarehouseList());
    }

    @Test
    public void execute_multipleKeywords_multipleSuppliersFound() {
        String expectedMessage = String.format(MESSAGE_SUPPLIERS_LISTED_OVERVIEW, 3);
        SupplierProductsContainKeywordsPredicate predicate = prepareSupplierPredicate("supplier mask");
        FindByProductsCommand command = new FindByProductsCommand(predicate);
        expectedModelForSupplier.updateFilteredSupplierList(predicate);
        assertCommandSuccess(command, modelForSupplier, expectedMessage, expectedModelForSupplier);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), modelForSupplier.getFilteredSupplierList());
    }

    @Test
    public void execute_multipleKeywords_multipleWarehousesFound() {
        String expectedMessage = String.format(MESSAGE_WAREHOUSE_LISTED_OVERVIEW, 3);
        WarehouseProductsContainKeywordsPredicate warehousePredicate = prepareWarehousePredicate("warehouse syrup");
        FindByProductsCommand command = new FindByProductsCommand(warehousePredicate);
        expectedModelForWarehouse.updateFilteredWarehouseList(warehousePredicate);
        assertCommandSuccess(command, modelForWarehouse, expectedMessage, expectedModelForWarehouse);
        assertEquals(Arrays.asList(TypicalWarehouse.CARL, TypicalWarehouse.ELLE, TypicalWarehouse.FIONA),
                modelForWarehouse.getFilteredWarehouseList());
    }

    /**
     * Parses {@code userInput} into a {@code SupplierProductsContainKeywordsPredicate}.
     */
    private SupplierProductsContainKeywordsPredicate prepareSupplierPredicate(String userInput) {
        return new SupplierProductsContainKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code WarehouseProductsContainKeywordsPredicate}.
     */
    private WarehouseProductsContainKeywordsPredicate prepareWarehousePredicate(String userInput) {
        return new WarehouseProductsContainKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
