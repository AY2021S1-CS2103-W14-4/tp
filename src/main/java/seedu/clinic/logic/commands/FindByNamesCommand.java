package seedu.clinic.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import seedu.clinic.commons.core.Messages;
import seedu.clinic.model.Model;
import seedu.clinic.model.attribute.NameContainsKeywordsPredicateForSupplier;
import seedu.clinic.model.attribute.NameContainsKeywordsPredicateForWarehouse;

/**
 * Finds and lists all suppliers/warehouses in the CLI-nic app whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 * Keyword only matches whole word e.g. alex will match "alex" but not "alexis".
 */
public class FindByNamesCommand extends Command {

    public static final String COMMAND_WORD = "findn";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all suppliers/warehouses whose name contains any"
            + "  of the argument keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: TYPE KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " supplier alex\n"
            + "Example: " + COMMAND_WORD + " warehouse bernice";

    private final Optional<NameContainsKeywordsPredicateForSupplier> supplierPredicate;
    private final Optional<NameContainsKeywordsPredicateForWarehouse> warehousePredicate;

    /**
     * Constructs a new FindByNamesCommand object.
     *
     * @param supplierPredicate takes in the predicate used to filter a supplier's name.
     */
    public FindByNamesCommand(NameContainsKeywordsPredicateForSupplier supplierPredicate) {
        this.supplierPredicate = Optional.of(supplierPredicate);
        this.warehousePredicate = Optional.empty();
    }

    /**
     * Constructs a new FindByNamesCommand object.
     *
     * @param warehousePredicate takes in the predicate used to filter a warehouse's name.
     */
    public FindByNamesCommand(NameContainsKeywordsPredicateForWarehouse warehousePredicate) {
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
        }

        model.updateFilteredWarehouseList(warehousePredicate.get());
        return new CommandResult(
                String.format(Messages.MESSAGE_WAREHOUSE_LISTED_OVERVIEW, model.getFilteredWarehouseList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindByNamesCommand // instanceof handles nulls
                && (supplierPredicate.isPresent()
                ? supplierPredicate.equals(((FindByNamesCommand) other).supplierPredicate)
                : warehousePredicate.equals(((FindByNamesCommand) other).warehousePredicate)));
    }
}
