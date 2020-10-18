package seedu.clinic.testutil;

import static seedu.clinic.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.clinic.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.clinic.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.clinic.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.clinic.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.clinic.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.clinic.logic.commands.CommandTestUtil.VALID_PRODUCT_NAME_ASPIRIN;
import static seedu.clinic.logic.commands.CommandTestUtil.VALID_PRODUCT_NAME_PANADOL;
import static seedu.clinic.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.clinic.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.clinic.logic.commands.CommandTestUtil.VALID_TAG_ANTIBIOTICS;
import static seedu.clinic.logic.commands.CommandTestUtil.VALID_TAG_FEVER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import seedu.clinic.model.Clinic;
import seedu.clinic.model.supplier.Supplier;
import seedu.clinic.model.warehouse.Warehouse;

/**
 * A utility class containing a list of {@code Supplier} objects to be used in tests.
 */
public class TypicalSupplier {

    public static final Supplier ALICE = new SupplierBuilder().withName("Alice Pauline Ltd")
            .withRemark("long term supplier").withEmail("alice@example.com")
            .withPhone("94351253")
            .withProducts(Map.of("Panadol", new String[]{"fever"})).build();
    public static final Supplier BENSON = new SupplierBuilder().withName("Benson Meier Ltd")
            .withRemark("trusted supplier")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withProducts(Map.of("Panadol", new String[]{"fever"})).build();
    public static final Supplier CARL = new SupplierBuilder().withName("Carl Kurz Ltd").withPhone("95352563")
            .withEmail("heinz@example.com").withRemark("nearby")
            .withProducts(Map.of("Mask", new String[]{"black"}))
            .withProducts(Map.of("Thermometer", new String[]{"digital"})).build();
    public static final Supplier DANIEL = new SupplierBuilder().withName("Daniel Meier Ltd").withPhone("87652533")
            .withEmail("cornelia@example.com").withRemark("premium prices")
            .withProducts(Map.of("Panadol", new String[]{"fever"})).build();
    public static final Supplier ELLE = new SupplierBuilder().withName("Elle Meyer Ltd").withPhone("9482224")
            .withEmail("werner@example.com").withRemark("industry leader")
            .withProducts(Map.of("Mask", new String[]{"black"}))
            .withProducts(Map.of("Medical Glove", new String[]{"rubber"})).build();
    public static final Supplier FIONA = new SupplierBuilder().withName("Fiona Kunz Ltd").withPhone("9482427")
            .withEmail("lydia@example.com").withRemark("specialises in antibiotics, industry leader")
            .withProducts(Map.of("Mask", new String[]{"black"}))
            .withProducts(Map.of("Needle", new String[]{"1mm"}))
            .withProducts(Map.of("Cough Syrup", new String[]{"cough"})).build();
    public static final Supplier GEORGE = new SupplierBuilder().withName("George Best Ltd").withPhone("9482442")
            .withEmail("anna@example.com").withRemark("frequent discount").build();

    // Manually added
    public static final Supplier HOON = new SupplierBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withRemark("small company").build();
    public static final Supplier IDA = new SupplierBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withRemark("big supplier").build();

    // Manually added - Supplier's details found in {@code CommandTestUtil}
    public static final Supplier AMY = new SupplierBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withRemark(VALID_REMARK_AMY)
            .withProducts(Map.of(VALID_PRODUCT_NAME_PANADOL, new String[]{VALID_TAG_FEVER})).build();
    public static final Supplier BOB = new SupplierBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withRemark(VALID_REMARK_BOB)
            .withProducts(Map.of(VALID_PRODUCT_NAME_ASPIRIN, new String[]{VALID_TAG_ANTIBIOTICS}))
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalSupplier() {} // prevents instantiation

    /**
     * Returns an {@code Clinic} with all the typical suppliers and warehouses.
     */
    public static Clinic getTypicalClinic() {
        Clinic clinic = new Clinic();
        for (Supplier supplier : getTypicalSuppliers()) {
            clinic.addSupplier(supplier);
        }

        for (Warehouse warehouse : TypicalWarehouse.getTypicalWarehouses()) {
            clinic.addWarehouse(warehouse);
        }
        return clinic;
    }

    /**
     * Returns an {@code Clinic} with all the typical suppliers.
     */
    public static Clinic getTypicalSupplierOnlyClinic() {
        Clinic clinic = new Clinic();
        for (Supplier supplier : getTypicalSuppliers()) {
            clinic.addSupplier(supplier);
        }
        return clinic;
    }

    public static List<Supplier> getTypicalSuppliers() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
