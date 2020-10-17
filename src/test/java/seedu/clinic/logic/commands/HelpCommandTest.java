package seedu.clinic.logic.commands;

import static seedu.clinic.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.clinic.logic.commands.HelpCommand.HELP_MESSAGE_FOR_COMMAND_FORMAT;
import static seedu.clinic.logic.commands.HelpCommand.HELP_MESSAGE_FOR_USER_GUIDE;

import org.junit.jupiter.api.Test;

import seedu.clinic.model.Model;
import seedu.clinic.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_genericHelp_success() {
        String aboutHelpCommand = "You can view more of each command using \nhelp COMMAND.\n\n"
                + "Sample Command:\nhelp find\n\nShows what find command does, command format and"
                + " sample command(s) to follow.\n\n"
                + "If you wish to learn more about sample commands found under"
                + " each command, feel free to copy our website User Guide URL,"
                + " which can be found by pressing F1.";

        String aboutAddCommand = "add\nYou can add warehouse, supplier or product to a supplier using the add"
                + " command";
        String aboutClearCommand = "clear\nYou can clear all entries (suppliers and warehouses)"
                + " in CLI-nic using clear command.";
        String aboutDeleteCommand = "delete\nYou can delete warehouse or supplier that are no longer"
                + " needed by using the delete command.";
        String aboutExitCommand = "exit\nYou can exit the application using the exit command.";
        String aboutFindCommand = "find\nYou can find suppliers or warehouses that sells the products using"
                + " the find command.";
        String aboutListCommand = "list\nYou can list all suppliers and warehouses by using list command.";
        String aboutViewCommand = "view\nYou can view a specific warehouse or supplier using"
                + " the view command.";
        String aboutUpdateCommand = "update\nYou can update stock of a product in the warehouse by"
                + " using the update command";

        String finalGenericHelpMessage = aboutHelpCommand + "\n\n" + aboutAddCommand + "\n\n"
                + aboutClearCommand + "\n\n" + aboutDeleteCommand + "\n\n" + aboutExitCommand
                + "\n\n" + aboutFindCommand + "\n\n" + aboutListCommand + "\n\n" + aboutViewCommand
                + "\n\n" + aboutUpdateCommand;
        CommandResult expectedCommandResult = new CommandResult(finalGenericHelpMessage,
                false, false);
        assertCommandSuccess(new HelpCommand("all"), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_addHelp_success() {
        String generalDescriptionOfAdd = "There are three types of add command to add warehouse,"
                + " add supplier or add a product to a supplier.";

        String addWarehouse = "Add Warehouse \nAdds warehouse to the CLI-nic application.";
        String addWarehouseCommandFormat = "Command format: \nadd w/WAREHOUSE_NAME p/CONTACT_NUMBER"
                + " addr/ADDRESS" + " [r/WAREHOUSE_NOTE]";
        String addWarehouseSampleCommand = "Sample Command: \nadd w/warehouseA p/00000000"
                + " addr/John street, block 123, #01-01 r/First warehouse";
        String finalAddWarehouse = addWarehouse + "\n\n" + addWarehouseCommandFormat + "\n\n"
                + addWarehouseSampleCommand;

        String addSupplier = "Add Supplier \nAdds a supplier to the CLI-nic application.";
        String addSupplierCommandFormat = "Command format: \nadd s/SUPPLIER_NAME p/PHONE"
                + " [e/EMAIL_ADDRESS] [r/SUPPLIER_REMARK]";
        String addSupplierSampleCommand = "Sample Command: \nadd s/Philips Pharmaceutical p/00000000"
                + " e/philipsPharm@gmail.com r/largest contractor";
        String finalAddSupplier = addSupplier + "\n\n" + addSupplierCommandFormat + "\n\n"
                + addSupplierSampleCommand;

        String addProductToSupplier = "Add Product to Supplier \nAdds product information to a supplier;"
                + " associates a particular" + " product with the supplier in the CLI-nic application.";
        String addProductToSupplierCommandFormat = "Command format: \naddp s/SUPPLIER_NAME pd/PRODUCT_NAME"
                + " [t/TAG...]";
        String addProductToSupplierSampleCommand = "Sample Command: \n"
                + "add s/SupplierA pd/PANADOL SUSP t/FEVER";
        String finalAddProductToSupplier = addProductToSupplier + "\n\n" + addProductToSupplierCommandFormat
                + "\n\n" + addProductToSupplierSampleCommand;

        String addHelpMessage = HELP_MESSAGE_FOR_COMMAND_FORMAT + "\n\n" + generalDescriptionOfAdd + "\n\n"
                + finalAddWarehouse + "\n\n" + finalAddSupplier + "\n\n" + finalAddProductToSupplier
                + "\n\n" + HELP_MESSAGE_FOR_USER_GUIDE;
        CommandResult expectedCommandResult = new CommandResult(addHelpMessage, false, false);
        assertCommandSuccess(new HelpCommand("add"), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_clearHelp_success() {
        String clearHelpMessage = "Clear\n\nClear all entries (suppliers and warehouses) in CLI-nic."
                + "\n\nSample Command: \nclear";
        CommandResult expectedCommandResult = new CommandResult(clearHelpMessage, false, false);
        assertCommandSuccess(new HelpCommand("clear"), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_deleteHelp_success() {
        String deleteSupplierOrWarehouse = "Delete\nDelete entries of warehouses or suppliers that are"
                + " not needed anymore.";
        String deleteSupplierOrWarehouseCommandFormat = "Command format: \ndelete TYPE INDEX";
        String deleteSupplierOrWarehouseSampleCommand = "Sample Command: \ndelete warehouse 1 \n"
                + "delete supplier 12";

        String deleteHelpMessage = HELP_MESSAGE_FOR_COMMAND_FORMAT + "\n\n" + deleteSupplierOrWarehouse
                + "\n\n" + deleteSupplierOrWarehouseCommandFormat + "\n\n"
                + deleteSupplierOrWarehouseSampleCommand + "\n\n" + HELP_MESSAGE_FOR_USER_GUIDE;
        CommandResult expectedCommandResult = new CommandResult(deleteHelpMessage, false, false);
        assertCommandSuccess(new HelpCommand("delete"), model,
                expectedCommandResult, expectedModel);
    }
    @Test
    public void execute_exitHelp_success() {
        String exitHelpMessage = "Exit\n\nExits the program.\n\nSample Command:\nexit";
        CommandResult expectedCommandResult = new CommandResult(exitHelpMessage, false, false);
        assertCommandSuccess(new HelpCommand("exit"), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_findHelp_success() {
        String findProductsInSupplierOrWarehouse = "Find by products\nFinds all suppliers or warehouses managed by the manager"
                + " that sells the relevant medical products.";
        String findProductsInSupplierOrWarehouseCommandFormat = "Command format: \nfindp TYPE KEYWORD"
                + "[KEYWORD]...\n\n"
                + "KEYWORD specified is case-insensitive and must contain at least one keyword.\n"
                + "The TYPE specified should be one of these values: warehouse / supplier.";
        String findProductsInSupplierOrWarehouseSampleCommand = "Sample Command: \n"
                + "findp warehouse PANADOL SUSP \nfindp supplier masks";
        String findHelpMessage = HELP_MESSAGE_FOR_COMMAND_FORMAT + "\n\n"
                + findProductsInSupplierOrWarehouse + "\n\n"
                + findProductsInSupplierOrWarehouseCommandFormat + "\n\n"
                + findProductsInSupplierOrWarehouseSampleCommand + "\n\n" + HELP_MESSAGE_FOR_USER_GUIDE;
        CommandResult expectedCommandResult = new CommandResult(findHelpMessage, false, false);
        assertCommandSuccess(new HelpCommand("find"), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_listHelp_success() {
        String listHelpMessage = "List\n\nLists all suppliers and warehouses.\n\nSample Command:\nlist";
        CommandResult expectedCommandResult = new CommandResult(listHelpMessage, false, false);
        assertCommandSuccess(new HelpCommand("list"), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_viewHelp_success() {
        String viewWarehouseOrSupplier = "View\nShows a particular supplier/warehouse with their relevant"
                + " information e.g. products associated with the supplier/warehouse, address etc.";
        String viewWarehouseOrSupplierCommandFormat = "Command format: \nview TYPE NAME\n"
                + "The TYPE specified should be one of these values: supplier or warehouse.\n"
                + "The supplier/warehouse NAME specified is case-insensitive.";
        String viewWarehouseOrSupplierSampleCommand = "Sample Command: \nview supplier supplierA"
                + "\nview warehouse warehouseB";
        String viewCommandHelpMessage = HELP_MESSAGE_FOR_COMMAND_FORMAT + "\n\n"
                + viewWarehouseOrSupplier + "\n\n"
                + viewWarehouseOrSupplierCommandFormat + "\n\n"
                + viewWarehouseOrSupplierSampleCommand + "\n\n" + HELP_MESSAGE_FOR_USER_GUIDE;
        CommandResult expectedCommandResult = new CommandResult(viewCommandHelpMessage, false, false);
        assertCommandSuccess(new HelpCommand("view"), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_updateHelp_success() {
        String updateWarehouseProduct = "Update\nIf the product does not exist for that warehouse,"
                + " it will associate the new product with the warehouse and the input quantity."
                + " Otherwise, it will update the stock of the existing product with the new quantity.";
        String updateWarehouseProductCommandFormat = "Command format: \n"
                + "update w/WAREHOUSE_NAME pd/PRODUCT_NAME q/QUANTITY";
        String updateWarehouseProductSampleCommand = "Sample Command: \n"
                + "update w/WarehouseA pd/Panadol q/10";
        String updateCommandHelpMessage = HELP_MESSAGE_FOR_COMMAND_FORMAT + "\n\n"
                + updateWarehouseProduct + "\n\n"
                + updateWarehouseProductCommandFormat + "\n\n"
                + updateWarehouseProductSampleCommand + "\n\n" + HELP_MESSAGE_FOR_USER_GUIDE;
        CommandResult expectedCommandResult = new CommandResult(updateCommandHelpMessage, false, false);
        assertCommandSuccess(new HelpCommand("update"), model,
                expectedCommandResult, expectedModel);
    }
}
