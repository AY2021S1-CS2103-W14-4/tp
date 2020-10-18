package seedu.clinic.model.supplier;

import java.util.List;
import java.util.function.Predicate;

import seedu.clinic.commons.util.StringUtil;

/**
 * Tests that the {@code Remark} of {@code Supplier} matches any of the keywords given.
 */
public class SupplierRemarkContainsKeywordsPredicate implements Predicate<Supplier> {
    private final List<String> keywords;

    public SupplierRemarkContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Supplier supplier) {
        String remark = supplier.getRemark().toString();

        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(remark, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SupplierRemarkContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((SupplierRemarkContainsKeywordsPredicate) other).keywords)); // state check
    }

}
