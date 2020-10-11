package seedu.clinic.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import seedu.clinic.commons.core.Messages;
import seedu.clinic.model.Model;
import seedu.clinic.model.supplier.SupplierProductsContainKeywordsPredicate;
import seedu.clinic.model.warehouse.WarehouseProductsContainKeywordsPredicate;

/**
 * Finds and lists all suppliers/warehouses in the CLI-nic app that sell/hold products matching any of the argument
 * keywords.
 * Keyword matching is case insensitive.
 * Keyword only matches whole word e.g. band will match "band aid" but not "bandaid".
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all suppliers/warehouses that sell/hold products"
            + "matching the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: TYPE KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " supplier panadol"
            + "Example: " + COMMAND_WORD + " warehouse face mask";

    private final Optional<SupplierProductsContainKeywordsPredicate> supplierPredicate;
    private final Optional<WarehouseProductsContainKeywordsPredicate> warehousePredicate;

    /**
     * Constructs a new FindCommand object.
     *
     * @param supplierPredicate takes in the predicate used to filter a supplier's products.
     */
    public FindCommand(SupplierProductsContainKeywordsPredicate supplierPredicate) {
        this.supplierPredicate = Optional.of(supplierPredicate);
        this.warehousePredicate = Optional.empty();
    }

    /**
     * Constructs a new FindCommand object.
     *
     * @param warehousePredicate takes in the predicate used to filter a warehouse's products.
     */
    public FindCommand(WarehouseProductsContainKeywordsPredicate warehousePredicate) {
        this.warehousePredicate = Optional.of(warehousePredicate);
        this.supplierPredicate = Optional.empty();
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (supplierPredicate.isPresent()) {
            model.updateFilteredSupplierList(supplierPredicate.get());
            return new CommandResult(
                    String.format(Messages.MESSAGE_SUPPLIERS_LISTED_OVERVIEW, model.getFilteredSupplierList().size()));
        } else {
            model.updateFilteredWarehouseList(warehousePredicate.get());
            return new CommandResult(
                    String.format(Messages.MESSAGE_WAREHOUSE_LISTED_OVERVIEW, model.getFilteredWarehouseList().size()));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && (supplierPredicate.isPresent()
                        ? supplierPredicate.equals(((FindCommand) other).supplierPredicate)
                        : warehousePredicate.equals(((FindCommand) other).warehousePredicate)));
    }
}
