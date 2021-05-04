package rp.edu.sg.c346.id20021576.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText etAmount;
    EditText etPax;
    ToggleButton tbSVS;
    ToggleButton tbGST;
    EditText etDisc;
    RadioGroup rgPay;
    RadioButton rbCash;
    RadioButton rbPayNow;
    Button bSplit;
    Button bReset;
    TextView tvTotal;
    TextView tvSplit;
    EditText etPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAmount = findViewById(R.id.etAmount);
        etPax = findViewById(R.id.etPax);
        tbSVS = findViewById(R.id.tbSVS);
        tbGST = findViewById(R.id.tbGST);
        etDisc = findViewById(R.id.etDiscount);
        rgPay = findViewById(R.id.rgPayment);
        rbCash = findViewById(R.id.rbCash);
        rbPayNow = findViewById(R.id.rbPayNow);
        bSplit = findViewById(R.id.bSplit);
        bReset = findViewById(R.id.bReset);
        tvTotal = findViewById(R.id.tvTotal);
        tvSplit = findViewById(R.id.tvSplit);
        etPhone = findViewById(R.id.etPhone);

        etPhone.setEnabled(false);

        bSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedRadioId = rgPay.getCheckedRadioButtonId();


                String a = etAmount.getText().toString();
                String b = etPax.getText().toString();
                String c = etDisc.getText().toString();

                if (a.isEmpty() || b.isEmpty() || c.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Please input something",
                            Toast.LENGTH_SHORT).show();
                } else if ((checkedRadioId == R.id.rbPayNow) && (etPhone.getText().toString().isEmpty())) {
                    Toast.makeText(MainActivity.this,
                            "Please input phone number",
                            Toast.LENGTH_SHORT).show();
                } else {

                    double amt = Double.parseDouble(etAmount.getText().toString());
                    double pax = Double.parseDouble(etPax.getText().toString());
                    double disc = Double.parseDouble(etDisc.getText().toString());

                    String payment;

                    if (checkedRadioId == R.id.rbCash) {
                        payment = " in cash";
                    } else {
                        payment = " via PayNow to " + etPhone.getText().toString();
                    }

                    if (tbSVS.isChecked()) {
                        amt = ((100 - disc) / 100) * (amt + (amt * 0.10));
                        String actualAmt = String.valueOf(amt);
                        tvTotal.setText("Total Bill: $" + actualAmt);
                        String splitAmt = String.valueOf(amt / pax);
                        tvSplit.setText("Each Pays: $" + splitAmt + payment);

                    } else if (tbGST.isChecked()) {
                        amt = ((100 - disc) / 100) * (amt + (amt * 0.07));
                        String actualAmt = String.valueOf(amt);
                        tvTotal.setText("Total Bill: $" + actualAmt);
                        String splitAmt = String.valueOf(amt / pax);
                        tvSplit.setText("Each Pays: $" + splitAmt + payment);

                    } else if (tbGST.isChecked() || tbSVS.isChecked()) {
                        amt = ((100 - disc) / 100) * (amt + (amt * 0.07) + (amt * 0.10));
                        String actualAmt = String.valueOf(amt);
                        tvTotal.setText("Total Bill: $" + actualAmt);
                        String splitAmt = String.valueOf(amt / pax);
                        tvSplit.setText("Each Pays: $" + splitAmt + payment);
                    } else {
                        amt = ((100 - disc) / 100) * amt;
                        String actualAmt = String.valueOf(amt);
                        tvTotal.setText("Total Bill: $" + actualAmt);
                        String splitAmt = String.valueOf(amt / pax);
                        tvSplit.setText("Each Pays: $" + splitAmt + payment);
                    }


                }

            }
        });

        rbPayNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etPhone.setEnabled(true);
            }
        });

        rbCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etPhone.setEnabled(false);
            }
        });

        bReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tbGST.setChecked(false);
                tbSVS.setChecked(false);
                tvTotal.setText("Total Bill: $");
                tvSplit.setText("Each pays: $");
                rbCash.setChecked(true);
                etAmount.setText("");
                etPax.setText("");
                etDisc.setText("");
                etPhone.setText("");


            }

        });
        }
    }
