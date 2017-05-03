package ccr.tools.bill.views;

import ccr.tools.bill.Expense;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.Resource;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class ExpensesView {
    Grid<Expense> expenseGrid = createExpenseGrid();
    List<Expense> expenses;

    public ExpensesView(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public Layout createExpensesViewLayout() {
        Layout layout = new VerticalLayout();
        Button exportButton = new Button("Export");
        layout.addComponents(expenseGrid, exportButton);

        FileDownloader fileDownloader = new FileDownloader(createExpensesCSVResource());
        fileDownloader.extend(exportButton);

        return layout;
    }

    public Grid<Expense> getExpenseGrid() {
        return expenseGrid;
    }

    private Grid<Expense> createExpenseGrid() {
        Grid<Expense> expensesGrid = new Grid<>();
        expensesGrid.addColumn(Expense::getShop).setCaption("Shop");
        expensesGrid.addColumn(Expense::getDate).setCaption("Date");
        expensesGrid.addColumn(Expense::getAmount).setCaption("Amount");
        expensesGrid.setWidth("800px");
        expensesGrid.setHeight("100%");
        return expensesGrid;
    }

    private Resource createExpensesCSVResource() {
        StreamSource streamSource = () -> {
            StringBuilder builder = new StringBuilder();
            expenses.forEach(expense -> builder.append(expense.toString() + "\n"));
            ByteArrayInputStream stream = null;
            try {
                stream = new ByteArrayInputStream(builder.toString().getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            return stream;
        };
        return new StreamResource(streamSource, "expenses.csv");
    }
}
