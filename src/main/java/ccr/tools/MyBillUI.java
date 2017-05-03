package ccr.tools;

import javax.servlet.annotation.WebServlet;

import ccr.tools.bill.Expense;
import ccr.tools.bill.Shop;
import ccr.tools.bill.forms.ExpenseForm;
import ccr.tools.bill.forms.ShopForm;
import ccr.tools.bill.views.ExpensesView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.*;
import com.vaadin.ui.*;
import com.vaadin.ui.ComboBox;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyBillUI extends UI {

    static List<Expense> expences = new ArrayList<>();
    static Set<Shop> shops = new HashSet<>();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final Layout masterLayout = new HorizontalLayout();
        ComboBox<Shop> shopSelector = new ComboBox<>("Type the shop name here:", shops);
        ExpensesView expensesView = new ExpensesView(expences);

        masterLayout.setHeight("100%");
        masterLayout.addComponents(
                createExpenseForm(shopSelector,expensesView.getExpenseGrid()),
                expensesView.createExpensesViewLayout(),
                createShopListLayout(shopSelector));
        setContent(masterLayout);
    }

    private Layout createExpenseForm(ComboBox<Shop> shopSelector, Grid<Expense> expenseGrid) {
        ExpenseForm expenseForm = new ExpenseForm(shopSelector);
        return expenseForm.addRegistrationListener( e -> {
            expences.add(expenseForm.toExpense());
            expenseGrid.setItems(expences);
        }).createFormLayout();
    }
    
    private Layout createShopListLayout(ComboBox<Shop> shopSelector) {
        ShopForm shopForm = new ShopForm();
        return shopForm.addRegisterListener(e -> {
            shops.add(shopForm.toShop());
            shopForm.setShops(shops);
            shopSelector.setItems(shops);
        }).createFormLayout();
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyBillUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

}
