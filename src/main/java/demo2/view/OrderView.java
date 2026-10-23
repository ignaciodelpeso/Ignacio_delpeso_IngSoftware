package demo2.view;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import demo2.model.article;
import demo2.model.order;

public class OrderView extends JFrame {
    private JTextField searchField = new JTextField(10);
    private JButton searchButton = new JButton("Search");
    private JTextArea resultArea = new JTextArea(10, 40);

    public OrderView() {
        setTitle("Order Management");

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

    public void displayOrder(order order, double rate) {
        if (order == null) {
            resultArea.setText("Order not found.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Order ").append(order.getId()).append(":\n");
        for (article a : order.getArticulos()) {
            sb.append(a.getNombre())
              .append(" x").append(a.getCantidad())
              .append(" @ ").append(a.getPrecio()).append("€")
              .append(" (discount ").append(a.getDescuento()).append("%)")
              .append(" = ").append(a.getDiscountedAmount()).append("€\n");
        }
        sb.append("Gross total: ").append(order.getGrossTotal()).append("$\n");
        sb.append("Discounted total: ").append(order.getDiscountedTotal()).append("$\n");
        sb.append("Gross total (USD): ").append(order.getGrossTotal() * rate).append("$\n");
        sb.append("Discounted total (USD): ").append(order.getDiscountedTotal() * rate).append("$");
        resultArea.setText(sb.toString());
    }
}
