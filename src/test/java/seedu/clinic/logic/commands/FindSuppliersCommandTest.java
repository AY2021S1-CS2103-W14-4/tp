package seedu.clinic.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.clinic.commons.core.Messages.MESSAGE_SUPPLIERS_LISTED_OVERVIEW;
import static seedu.clinic.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.clinic.testutil.TypicalSupplier.CARL;
import static seedu.clinic.testutil.TypicalSupplier.ELLE;
import static seedu.clinic.testutil.TypicalSupplier.FIONA;
import static seedu.clinic.testutil.TypicalSupplier.getTypicalClinic;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.clinic.model.Model;
import seedu.clinic.model.ModelManager;
import seedu.clinic.model.UserPrefs;
import seedu.clinic.model.supplier.SupplierProductsContainKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindByProductsCommand}.
 */
public class FindSuppliersCommandTest {
    private Model model = new ModelManager(getTypicalClinic(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalClinic(), new UserPrefs());

    @Test
    public void equals() {
        SupplierProductsContainKeywordsPredicate firstPredicate =
                new SupplierProductsContainKeywordsPredicate(Collections.singletonList("first"));
        SupplierProductsContainKeywordsPredicate secondPredicate =
                new SupplierProductsContainKeywordsPredicate(Collections.singletonList("second"));

        FindByProductsCommand findFirstCommand = new FindByProductsCommand(firstPredicate);
        FindByProductsCommand findSecondCommand = new FindByProductsCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindByProductsCommand findFirstCommandCopy = new FindByProductsCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different supplier -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noSupplierFound() {
        String expectedMessage = String.format(MESSAGE_SUPPLIERS_LISTED_OVERVIEW, 0);
        SupplierProductsContainKeywordsPredicate predicate = preparePredicate(" ");
        FindByProductsCommand command = new FindByProductsCommand(predicate);
        expectedModel.updateFilteredSupplierList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredSupplierList());
    }

    @Test
    public void execute_multipleKeywords_multipleSuppliersFound() {
        String expectedMessage = String.format(MESSAGE_SUPPLIERS_LISTED_OVERVIEW, 3);
        SupplierProductsContainKeywordsPredicate predicate = preparePredicate("supplier mask");
        FindByProductsCommand command = new FindByProductsCommand(predicate);
        expectedModel.updateFilteredSupplierList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredSupplierList());
    }

    /**
     * Parses {@code userInput} into a {@code SupplierProductsContainKeywordsPredicate}.
     */
    private SupplierProductsContainKeywordsPredicate preparePredicate(String userInput) {
        return new SupplierProductsContainKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
