package seedu.clinic.logic.commands;

import seedu.clinic.model.Model;

/**
 * Format full help instructions for respective help command to display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows all command with"
            + " respective utility description\n"
            + "Example: " + COMMAND_WORD + "\n\nSpecific commands with respective input samples "
            + "can be shown as well \n" + "Example: help find";
    public static final String MESSAGE_TOO_MANY_ARGUMENTS = "You can only enter one command at a time\n\n"
            + MESSAGE_USAGE;
    public static final String MESSAGE_WRONG_ARGUMENT = "You can only enter command from one of the "
            + "following keywords: add, clear, delete, exit, find, view, update";

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";
    public static final String[] ALLOWED_ARGUMENTS = new String[]{
        "add", "clear", "delete", "exit", "find", "list", "view", "update"};

    public static final String HELP_MESSAGE_FOR_COMMAND_FORMAT = "How to interpret command format?\n"
            + "Words in UPPER_CASE are the parameters to be supplied by the user."
            + " Items in square brackets are optional. Items with ... after them can be used multiple times."
            + " Parameters can be in any order.";

    public static final String HELP_MESSAGE_FOR_USER_GUIDE = "Press F1 to copy the URL to view User Guide"
            + " on our website for more details on each command sample";

    private final String commandArgument;

    /**
     * Constructs a new HelpCommand object.
     *
     * @param commandArgument takes in the command argument (if any) specified for help command.
     */
    public HelpCommand(String commandArgument) {
        this.commandArgument = commandArgument;
    }

    @Override
    public CommandResult execute(Model model) {
        String helpMessage = "test";
        switch(commandArgument) {
        case "add":
            helpMessage = generateHelpAddMessage();
            break;
        case "clear":
            helpMessage = generateHelpClearMessage();
            break;
        case "delete":
            helpMessage = generateHelpDeleteMessage();
            break;
        case "exit":
            helpMessage = generateHelpExitMessage();
            break;
        case "find":
            helpMessage = generateHelpFindMessage();
            break;
        case "list":
            helpMessage = generateHelpListMessage();
            break;
        case "view":
            helpMessage = generateHelpViewMessage();
            break;
        case "update":
            helpMessage = generateHelpUpdateMessage();
            break;
        default:
            helpMessage = generateHelpGenericMessage();
        }

        return new CommandResult(helpMessage, false, false);
    }

    private String generateHelpGenericMessage() {
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
        return finalGenericHelpMessage;
    }

    private String generateHelpAddMessage() {
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

        return addHelpMessage;
    }

    private String generateHelpClearMessage() {
        return "Clear\n\nClear all entries (suppliers and warehouses) in CLI-nic.\n\nSample Command: \nclear";
    }

    private String generateHelpDeleteMessage() {
        String deleteSupplierOrWarehouse = "Delete\nDelete entries of warehouses or suppliers that are"
                + " not needed anymore.";
        String deleteSupplierOrWarehouseCommandFormat = "Command format: \ndelete TYPE INDEX";
        String deleteSupplierOrWarehouseSampleCommand = "Sample Command: \ndelete warehouse 1 \n"
                + "delete supplier 12";

        String deleteHelpMessage = HELP_MESSAGE_FOR_COMMAND_FORMAT + "\n\n" + deleteSupplierOrWarehouse
                + "\n\n" + deleteSupplierOrWarehouseCommandFormat + "\n\n"
                + deleteSupplierOrWarehouseSampleCommand + "\n\n" + HELP_MESSAGE_FOR_USER_GUIDE;
        return deleteHelpMessage;
    }

    private String generateHelpExitMessage() {
        return "Exit\n\nExits the program.\n\nSample Command:\nexit";
    }

    private String generateHelpFindMessage() {
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
        return findHelpMessage;
    }

    private String generateHelpListMessage() {
        return "List\n\nLists all suppliers and warehouses.\n\nSample Command:\nlist";
    }

    private String generateHelpViewMessage() {
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
        return viewCommandHelpMessage;
    }

    private String generateHelpUpdateMessage() {
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
        return updateCommandHelpMessage;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HelpCommand // instanceof handles nulls
                && commandArgument.equals(((HelpCommand) other).commandArgument)); // state check
    }
}
