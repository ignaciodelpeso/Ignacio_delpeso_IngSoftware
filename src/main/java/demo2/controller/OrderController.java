package demo2.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import demo2.model.order;
import demo2.view.OrderView;

public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    private static final double USD_RATE = 1.162976;

    OrderView view;
    private List<order> orders;

    public OrderController(OrderView view, List<order> orders) {
        this.view = view;
        this.orders = orders;

        view.getSearchButton().addActionListener(e -> searchOrder());
    }

    private void searchOrder() {
        String id = view.getSearchId();
        log.debug("Searching order: {}", id);

        order found = orders.stream()
                .filter(o -> o.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);

        view.displayOrder(found, USD_RATE);
    }
}
