package idv.haojun.calculatorrecorder.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import idv.haojun.calculatorrecorder.helper.DialogHelper;
import idv.haojun.calculatorrecorder.R;
import idv.haojun.calculatorrecorder.model.CalculatorRecord;
import idv.haojun.calculatorrecorder.sqlite.CalculatorRecordDao;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etValue1;
    private EditText etValue2;
    private TextView tvResult;

    private int lastOperateType = -1;
    private String lastResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        etValue1 = findViewById(R.id.etCalculatorValue1);
        etValue2 = findViewById(R.id.etCalculatorValue2);
        tvResult = findViewById(R.id.tvCalculatorResult);
        findViewById(R.id.btCalculatorAdd).setOnClickListener(operateClickListener);
        findViewById(R.id.btCalculatorSub).setOnClickListener(operateClickListener);
        findViewById(R.id.btCalculatorMul).setOnClickListener(operateClickListener);
        findViewById(R.id.btCalculatorDiv).setOnClickListener(operateClickListener);
        findViewById(R.id.btCalculatorSave).setOnClickListener(this);

    }

    private View.OnClickListener operateClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String value1 = etValue1.getText().toString();
            String value2 = etValue2.getText().toString();

            if (value1.isEmpty() || value2.isEmpty()) {
                DialogHelper.showMessageDialog(CalculatorActivity.this, "empty");
                return;
            }

            int v1 = Integer.parseInt(value1);
            int v2 = Integer.parseInt(value2);

            switch (v.getId()) {
                case R.id.btCalculatorAdd:
                    lastOperateType = CalculatorRecord.ADD;
                    lastResult = String.valueOf(v1 + v2);
                    tvResult.setText(lastResult);
                    break;
                case R.id.btCalculatorSub:
                    lastOperateType = CalculatorRecord.SUB;
                    lastResult = String.valueOf(v1 - v2);
                    tvResult.setText(String.valueOf(v1 - v2));
                    break;
                case R.id.btCalculatorMul:
                    lastOperateType = CalculatorRecord.MUL;
                    lastResult = String.valueOf(v1 * v2);
                    tvResult.setText(String.valueOf(v1 * v2));
                    break;
                case R.id.btCalculatorDiv:
                    if (v2 == 0) {
                        DialogHelper.showMessageDialog(CalculatorActivity.this, "value2 can't be 0");
                        break;
                    }
                    lastOperateType = CalculatorRecord.DIV;
                    lastResult = String.valueOf(v1 / v2);
                    tvResult.setText(String.valueOf(v1 / v2));
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btCalculatorSave:
                String value1 = etValue1.getText().toString();
                String value2 = etValue2.getText().toString();

                if (value1.isEmpty() || value2.isEmpty()) {
                    DialogHelper.showMessageDialog(CalculatorActivity.this, "empty");
                    return;
                }

                if (lastOperateType == -1) {
                    DialogHelper.showMessageDialog(CalculatorActivity.this, "no operate");
                    return;
                }

                boolean success = new CalculatorRecordDao(this).insert(new CalculatorRecord(value1, value2, lastOperateType, lastResult));
                if (success) {
                    setResult(RESULT_OK);
                    finish();
                }
                break;
        }
    }
}
