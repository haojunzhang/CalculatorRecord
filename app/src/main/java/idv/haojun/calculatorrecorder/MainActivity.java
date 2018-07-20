package idv.haojun.calculatorrecorder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.ivMainAdd).setOnClickListener(this);

        rv = findViewById(R.id.rvMain);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new MyAdapter());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivMainAdd:
                startActivity(new Intent(this, CalculatorActivity.class));
                break;
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        private List<CalculatorRecord> list;

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView value1;
            TextView operateType;
            TextView value2;
            TextView result;

            ViewHolder(View itemView) {
                super(itemView);
                value1 = itemView.findViewById(R.id.tvItemCalculatorValue1);
                operateType = itemView.findViewById(R.id.tvItemCalculatorOperateType);
                value2 = itemView.findViewById(R.id.tvItemCalculatorValue2);
                result = itemView.findViewById(R.id.tvItemCalculatorResult);
            }

            void setValue1(String s) {
                value1.setText(s);
            }

            void setOperateTypeText(String s) {
                operateType.setText(s);
            }

            void setValue2(String s) {
                value2.setText(s);
            }

            void setResult(String s) {
                result.setText(s);
            }
        }

        MyAdapter() {
            list = new ArrayList<>();
        }

        public void setList(List<CalculatorRecord> list) {
            this.list = list;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calculator, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            CalculatorRecord item = list.get(position);
            holder.setValue1(item.getValue1());
            holder.setOperateTypeText(item.getOperateTypeText());
            holder.setValue2(item.getValue2());
            holder.setResult(item.getResult());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }
}
