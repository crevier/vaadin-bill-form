package ccr.tools.bill.forms;

import ccr.tools.bill.Expense;
import ccr.tools.bill.Shop;
import com.vaadin.ui.*;

public class ExpenseForm {
    private Layout layout = new VerticalLayout();
    private ComboBox<Shop> shopSelector;
    private DateField dateField = new DateField("Select the date");
    private TextField amountField = new TextField("Type the amount here:");
    private Button registerButton = new Button("Register");

    public ExpenseForm(ComboBox<Shop> shopSelector) {
        this.shopSelector = shopSelector;
    }

    public Layout createFormLayout() {
        layout.addComponents(shopSelector, dateField, amountField, registerButton);
        return layout;
    }

    public Expense toExpense() {
        Expense expense = new Expense();
        expense.setAmount(Float.parseFloat(amountField.getValue()));
        expense.setDate(dateField.getValue());
        expense.setShop(shopSelector.getValue().getName());
        return expense;
    }

    public ExpenseForm addRegistrationListener(Button.ClickListener listener) {
        registerButton.addClickListener(listener);
        return this;
    }

}
