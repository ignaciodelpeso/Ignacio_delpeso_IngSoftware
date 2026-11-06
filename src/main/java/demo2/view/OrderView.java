package demo2.view;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.util.Locale;

import javax.swing.ImageIcon;

import demo2.model.article;
import demo2.model.order;
import demo2.service.ExchangeRateService;

public class OrderView extends JFrame {
    private JTextField searchField = new JTextField(10);
    private JButton searchButton = new JButton("Search");
    private JTextArea resultArea = new JTextArea(10, 40);

    public OrderView() {
        setTitle("Order Management");

        java.net.URL iconUrl = OrderView.class.getResource("/app.png");
        if (iconUrl != null) {
            setIconImage(new ImageIcon(iconUrl).getImage());
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(new JLabel("Order ID:"));
        add(searchField);
        add(searchButton);
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea));

        pack();
        setVisible(true);
    }

    public String getSearchId() {
        return searchField.getText().trim();
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public void displayOrder(order order, ExchangeRateService.Rate rate) {
        if (order == null) {
            resultArea.setText("Order not found.");
            return;
        }

        double r = rate.value();
        StringBuilder sb = new StringBuilder();
        sb.append("Order ").append(order.getId()).append(":\n");
        for (article a : order.getArticulos()) {
            sb.append(a.getNombre())
              .append(" x").append(a.getCantidad())
              .append(" @ ").append(String.format(Locale.US, "%.2f", a.getPrecio())).append("€")
              .append(" (discount ").append(a.getDescuento()).append("%)")
              .append(" = ").append(String.format(Locale.US, "%.2f", a.getDiscountedAmount())).append("€\n");
        }
        sb.append(String.format(Locale.US, "Gross total: %.2f€ / %.2f$%n",
                order.getGrossTotal(), order.getGrossTotal() * r));
        sb.append(String.format(Locale.US, "Discounted total: %.2f€ / %.2f$%n",
                order.getDiscountedTotal(), order.getDiscountedTotal() * r));
        sb.append(String.format(Locale.US, "Exchange rate: 1 EUR = %.4f USD", r));
        sb.append(rate.live() ? " (live, Frankfurter/ECB)"
                              : " (service unavailable, using fallback rate)");
        resultArea.setText(sb.toString());
    }
}
