package pl.bartekpawlowski.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

import static android.R.id.message;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int numberOfCoffees = 0;
    private int priceOfCoffee = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display(this.numberOfCoffees);
        displayPrice(this.numberOfCoffees);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        displayPrice(this.numberOfCoffees);
        }

    public void decrementQuantity(View view) {
        this.numberOfCoffees--;
        TextView quantityTextView = (TextView) findViewById(R.id.quantity);
        quantityTextView.setText("" + this.numberOfCoffees);
    }

    public void incrementQuantity(View view) {
        this.numberOfCoffees++;
        TextView quantityTextView = (TextView) findViewById(R.id.quantity);
        quantityTextView.setText("" + this.numberOfCoffees);
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
        String coffeEndWord = getString(R.string.coffee_single);
        price *= this.priceOfCoffee;

        if(this.numberOfCoffees > 1) {
            coffeEndWord = getString(R.string.coffree_plural);
        }
        TextView priceTextView = (TextView) findViewById(R.id.price);
        priceTextView.setText(getString(R.string.total) + ": " + NumberFormat.getCurrencyInstance().format(price) + " " + getString(R.string.for_sentence) + " " + this.numberOfCoffees + " " + coffeEndWord);
    }
}