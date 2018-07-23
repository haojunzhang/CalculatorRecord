package idv.haojun.calculatorrecorder.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import idv.haojun.calculatorrecorder.helper.DialogHelper;
import idv.haojun.calculatorrecorder.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etEmail;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etLoginEmail);
        etPassword = findViewById(R.id.etLoginPassword);
        findViewById(R.id.tvLoginLogin).setOnClickListener(this);
    }

    private boolean isEmailValid(String email) {
        Matcher matcher = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE).matcher(email);
        return matcher.find();
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 8;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvLoginLogin:
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    DialogHelper.showMessageDialog(this, "empty");
                    return;
                }

                if (!isEmailValid(email)) {
                    DialogHelper.showMessageDialog(this,"email invalid");
                    return;
                }

                if (!isPasswordValid(password)) {
                    DialogHelper.showMessageDialog(this,"password invalid(<8)");
                    return;
                }

                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
    }
}
