package ccr.tools.bill.forms;

import ccr.tools.bill.Shop;
import com.vaadin.ui.*;

import java.util.Set;


public class ShopForm {
    Layout layout = new VerticalLayout();
    Grid<Shop> shopGrid = new Grid<>();
    TextField shopNameField = new TextField("Type the shop name here:");
    Button registerButton = new Button("Add shop");

    public Layout createFormLayout() {
        initGrid();
        layout.addComponents(shopNameField, registerButton, shopGrid);
        return layout;
    }

    public Shop toShop() {
        return new Shop(shopNameField.getValue());
    }

    public ShopForm addRegisterListener(Button.ClickListener event) {
        registerButton.addClickListener(event);
        return this;
    }

    public void setShops(Set<Shop> shops) {
        shopGrid.setItems(shops);
    }

    private void initGrid() {
        shopGrid.addColumn(Shop::getName).setCaption("Shops");
        shopGrid.setWidth("200px");
    }

}
