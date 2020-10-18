package seedu.clinic.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import seedu.clinic.commons.core.Messages;
import seedu.clinic.model.Model;
import seedu.clinic.model.supplier.SupplierRemarkContainsKeywordsPredicate;
import seedu.clinic.model.warehouse.WarehouseRemarkContainsKeywordsPredicate;

/**
 * Finds and lists all suppliers/warehouses in the CLI-nic app whose remark matches any of the argument keywords.
 * Keyword matching is case insensitive.
 * Keyword only matches whole word e.g. partner will match "partner" but not "partners".
 */
public class FindByRemarksCommand extends Command {

    public static final String COMMAND_WORD = "findr";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all suppliers/warehouses whose remark matches"
            + " the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: TYPE KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " supplier long term\n"
            + "Example: " + COMMAND_WORD + " warehouse biggest";

    private final Optional<SupplierRemarkContainsKeywordsPredicate> supplierPredicate;
    private final Optional<WarehouseRemarkContainsKeywordsPredicate> warehousePredicate;

    /**
     * Constructs a new FindByRemarksCommand object.
     *
     * @param supplierPredicate takes in the predicate used to filter a supplier's remark.
     */
    public FindByRemarksCommand(SupplierRemarkContainsKeywordsPredicate supplierPredicate) {
        this.supplierPredicate = Optional.of(supplierPredicate);
        this.warehousePredicate = Optional.empty();
    }

    /**
     * Constructs a new FindByRemarksCommand object.
     *
     * @param warehousePredicate takes in the predicate used to filter a warehouse's remark.
     */
    public FindByRemarksCommand(WarehouseRemarkContainsKeywordsPredicate warehousePredicate) {
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
                || (other instanceof FindByRemarksCommand // instanceof handles nulls
                && (supplierPredicate.isPresent()
                ? supplierPredicate.equals(((FindByRemarksCommand) other).supplierPredicate)
                : warehousePredicate.equals(((FindByRemarksCommand) other).warehousePredicate)));
    }
}
