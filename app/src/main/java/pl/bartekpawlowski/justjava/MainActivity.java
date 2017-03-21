package pl.bartekpawlowski.justjava;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AlignmentSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import static android.icu.lang.UCharacter.toUpperCase;
import static pl.bartekpawlowski.justjava.R.string.toppings;
import static pl.bartekpawlowski.justjava.R.string.total;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int numberOfCoffees = 0;
    private int priceOfCoffee = 5;
    private int priceOfWhippedCream = 2;
    private int priceOfChocolate = 1;

    // Views
    private TextView quantityView;
    private TextView orderSummaryView;
    private CheckBox toppingsCreamCheckBox;
    private CheckBox toppingsChocolate;
    private EditText enterName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        quantityView = (TextView) findViewById(R.id.quantity);
        toppingsCreamCheckBox = (CheckBox) findViewById(R.id.toppings_whipped_cream);
        toppingsChocolate = (CheckBox) findViewById(R.id.toppings_chocolate);
        enterName = (EditText) findViewById(R.id.enter_name);

        numberOfCoffees = 1;
        quantityView.setText("" + numberOfCoffees);

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {


        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
        String message = createOrderSummary(price);
        String subject = getResources().getString(R.string.subject, getEditName());

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        }

    public void decrementQuantity(View view) {
        numberOfCoffees--;
        if(numberOfCoffees < 1) {
            numberOfCoffees = 1;
            makeCenteredToast(getResources().getString(R.string.toast_too_few));
            quantityView.setText("" + numberOfCoffees);
        } else {
            quantityView.setText("" + numberOfCoffees);
        }
    }

    public void incrementQuantity(View view) {
        numberOfCoffees++;
        if(numberOfCoffees > 100) {
            numberOfCoffees = 100;
            makeCenteredToast(getResources().getString(R.string.toast_too_many));
            quantityView.setText("" + numberOfCoffees);
        } else {
            quantityView.setText("" + numberOfCoffees);
        }
    }

    /**
    * This method creates Toast with centered text
     **/
    private void makeCenteredToast(String text){
        Spannable centeredText = new SpannableString(text);
        centeredText.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), 0, text.length() - 1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        Toast.makeText(getApplicationContext(), centeredText, Toast.LENGTH_SHORT).show();
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int numberOfOrders) {
        quantityView.setText("" + numberOfOrders);
    }

    /**
     * This method gets state of topping checkbox state
     */

    private boolean hasWhippedCream() {
        boolean state = false;

        if(toppingsCreamCheckBox.isChecked()) {
            state = true;
        }

        return state;
    }

    private boolean hasChocolate() {
        boolean state = false;

        if(toppingsChocolate.isChecked()) {
            state = true;
        }

        return state;
    }

    private String getEditName() {
        String name = enterName.getText().toString();

        return name;
    }

    /**
     * This method calculate price of the order
     */

    private int calculatePrice() {
        int totalPrice = numberOfCoffees * priceOfCoffee;

        if(hasWhippedCream()) {
            totalPrice += priceOfWhippedCream * numberOfCoffees;
        }

        if(hasChocolate()) {
            totalPrice += priceOfChocolate * numberOfCoffees;
        }

        return totalPrice;
    }

    /**
     * This method return localized price.
     */
    private String formatPrice(int price) {
        String formatedPrice = NumberFormat.getCurrencyInstance().format(price);

        return formatedPrice;
    }

    /**
     * @param string String
     * @return String with capitalized letter of first word
     */

    private String capitalizeFirstLetter(String string) {
        return Character.toUpperCase(string.charAt(0)) + string.substring(1);
    }

    /**
     *
     * @param finalPrice
     * @return
     */

    private String createOrderSummary(int finalPrice) {

        String localizedPrice = formatPrice(finalPrice);
        String name = getResources().getString(R.string.name, getEditName());
        String thanks = getResources().getString(R.string.thanks);
        String quantity = getResources().getString(R.string.quantity);
        String total = getResources().getString(R.string.total);

        String toppingWhippedCream = getResources().getString(R.string.add_whipped_cream);
        String addWhippedCream = "";

        if(hasWhippedCream()) {
            addWhippedCream = capitalizeFirstLetter(getResources().getString(R.string.yes));
        } else {
            addWhippedCream = capitalizeFirstLetter(getResources().getString(R.string.no));
        }

        String toppingChocolate = getResources().getString(R.string.add_chocolate);
        String addChocolate = "";

        if(hasChocolate()) {
            addChocolate = capitalizeFirstLetter(getResources().getString(R.string.yes));
        } else {
            addChocolate = capitalizeFirstLetter(getResources().getString(R.string.no));
        }

        String message = name + "\n" + quantity + numberOfCoffees + "\n" + toppingWhippedCream + " " + addWhippedCream + "\n" + toppingChocolate + " " + addChocolate + "\n" +  total + localizedPrice + "\n" + thanks;

        return message;

    }
}