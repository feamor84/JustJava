package pl.bartekpawlowski.justjava;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int numberOfCoffees = 0;
    private int priceOfCoffee = 5;

    // Views
    private TextView quantityView;
    private TextView orderSummaryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        quantityView = (TextView) findViewById(R.id.quantity);
        orderSummaryView = (TextView) findViewById(R.id.order_summary_text_view);

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
        orderSummaryView.setText(createOrderSummary(price));
        }

    public void decrementQuantity(View view) {
        this.numberOfCoffees--;
        quantityView.setText("" + numberOfCoffees);
    }

    public void incrementQuantity(View view) {
        this.numberOfCoffees++;
        quantityView.setText("" + numberOfCoffees);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int numberOfOrders) {
        quantityView.setText("" + numberOfOrders);
    }

    /**
     * This method calculate price of the order
     */

    private int calculatePrice() {
        return numberOfCoffees * priceOfCoffee;
    }

    /**
     * This method return localized price.
     */
    private String formatPrice(int price) {
        String formatedPrice = NumberFormat.getCurrencyInstance().format(price);

        return formatedPrice;
    }

    private String createOrderSummary(int finalPrice) {

        String localizedPrice = formatPrice(finalPrice);
        String name = getResources().getString(R.string.name);
        String thanks = getResources().getString(R.string.thanks);
        String quantity = getResources().getString(R.string.quantity);
        String total = getResources().getString(R.string.total);

        String message = name + "\n" + quantity + numberOfCoffees + "\n" + total + localizedPrice + "\n" + thanks;

        Log.i("Podsumowanie zamówieani", message);

        return message;

    }
}