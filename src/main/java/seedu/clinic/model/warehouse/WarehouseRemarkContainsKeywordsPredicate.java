package seedu.clinic.model.warehouse;

import java.util.List;
import java.util.function.Predicate;

import seedu.clinic.commons.util.StringUtil;

/**
 * Tests that the {@code Remark} of {@code Warehouse} matches any of the keywords given.
 */
public class WarehouseRemarkContainsKeywordsPredicate implements Predicate<Warehouse> {
    private final List<String> keywords;

    public WarehouseRemarkContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Warehouse warehouse) {
        String remark = warehouse.getRemark().toString();

        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(remark, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof WarehouseRemarkContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((WarehouseRemarkContainsKeywordsPredicate) other).keywords)); // state check
    }

}
