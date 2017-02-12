package pl.bartekpawlowski.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private static int numberOfCoffees = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        display(this.numberOfCoffees);
        displayPrice(this.numberOfCoffees);
    }

    public void decrementQuantity(View view) {
        this.numberOfCoffees--;
        TextView quantityTextView = (TextView) findViewById(R.id.quantity);
        quantityTextView.setText("" + this.numberOfCoffees);
        displayPrice(this.numberOfCoffees);
    }

    public void incrementQuantity(View view) {
        this.numberOfCoffees++;
        TextView quantityTextView = (TextView) findViewById(R.id.quantity);
        quantityTextView.setText("" + this.numberOfCoffees);
        displayPrice(this.numberOfCoffees);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int numberOfOrders) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity);
        quantityTextView.setText("" + numberOfOrders);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int price) {
        price *= 10;
        TextView priceTextView = (TextView) findViewById(R.id.price);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(price));
    }
}