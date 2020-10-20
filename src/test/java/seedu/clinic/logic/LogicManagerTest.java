package seedu.clinic.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.clinic.commons.core.Messages.MESSAGE_INVALID_SUPPLIER_DISPLAYED_INDEX;
//import static seedu.clinic.commons.core.Messages.MESSAGE_INVALID_WAREHOUSE_DISPLAYED_INDEX;
import static seedu.clinic.commons.core.Messages.MESSAGE_INVALID_WAREHOUSE_DISPLAYED_INDEX;
import static seedu.clinic.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
//import static seedu.clinic.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
//import static seedu.clinic.logic.commands.CommandTestUtil.NAME_DESC_AMY;
//import static seedu.clinic.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
//import static seedu.clinic.logic.commands.CommandTestUtil.REMARK_DESC_AMY;
import static seedu.clinic.testutil.Assert.assertThrows;
//import static seedu.clinic.testutil.TypicalSupplier.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

//import seedu.clinic.logic.commands.AddCommand;
import seedu.clinic.logic.commands.CommandResult;
import seedu.clinic.logic.commands.ListCommand;
import seedu.clinic.logic.commands.exceptions.CommandException;
import seedu.clinic.logic.parser.exceptions.ParseException;
import seedu.clinic.model.Model;
import seedu.clinic.model.ModelManager;
import seedu.clinic.model.ReadOnlyClinic;
import seedu.clinic.model.UserMacros;
import seedu.clinic.model.UserPrefs;
//import seedu.clinic.model.supplier.Supplier;
import seedu.clinic.storage.JsonClinicStorage;
import seedu.clinic.storage.JsonUserMacrosStorage;
import seedu.clinic.storage.JsonUserPrefsStorage;
import seedu.clinic.storage.StorageManager;
//import seedu.clinic.testutil.SupplierBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonClinicStorage clinicStorage =
                new JsonClinicStorage(temporaryFolder.resolve("clinic.json"));
        JsonUserMacrosStorage userMacrosStorage =
                new JsonUserMacrosStorage(temporaryFolder.resolve("userMacros.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(clinicStorage, userPrefsStorage, userMacrosStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete ct/s i/9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_SUPPLIER_DISPLAYED_INDEX);

        deleteCommand = "delete ct/w i/9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_WAREHOUSE_DISPLAYED_INDEX);
    }


    /*
    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonClinicIoExceptionThrowingStub
        JsonClinicStorage clinicStorage =
                new JsonClinicIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionClinic.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(clinicStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + REMARK_DESC_AMY;
        Supplier expectedSupplier = new SupplierBuilder(AMY).build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addSupplier(expectedSupplier);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }
    */

    @Test
    public void getFilteredSupplierList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredSupplierList().remove(0));
    }

    @Test
    public void getFilteredWarehouseList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredWarehouseList().remove(0));
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
            Model expectedModel) throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage) {
        Model expectedModel = new ModelManager(model.getClinic(), new UserPrefs(), new UserMacros());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandSuccess(String, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage, Model expectedModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonClinicIoExceptionThrowingStub extends JsonClinicStorage {
        private JsonClinicIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveClinic(ReadOnlyClinic clinic, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
