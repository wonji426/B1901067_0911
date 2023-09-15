package com.example.b1901067_0911;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvResult;
    EditText edtNum1, edtOper, edtNum2, dlgEdt;
    Button btnCalc;
    View dlgView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,"덧셈");
        menu.add(0,2,0,"뻴셈");
        menu.add(0,3,0,"곱셈");
        menu.add(0,4,0,"나눗셈");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 1:
                edtOper.setText("+");
                break;
            case 2:
                edtOper.setText("-");
                break;
            case 3:
                edtOper.setText("*");
                break;
            case 4:
                edtOper.setText("/");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,1,0,"빨강");
        menu.add(0,2,0,"초록");
        menu.add(0,3,0,"파랑");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 1:
                btnCalc.setBackgroundColor(Color.RED);
                break;
            case 2:
                btnCalc.setBackgroundColor(Color.GREEN);
                break;
            case 3:
                btnCalc.setBackgroundColor(Color.BLUE);
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = (TextView) findViewById(R.id.tvResult);
        edtNum1 = (EditText) findViewById(R.id.edtNum1);
        edtNum2 = (EditText) findViewById(R.id.edtNum2);
        edtOper = (EditText) findViewById(R.id.edtOper);
        btnCalc = (Button) findViewById(R.id.btnCalc);
        registerForContextMenu(btnCalc);

        edtNum1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    dlg.setTitle("숫자1을 선택하였습니다.");
                    dlgView = (View) View.inflate(MainActivity.this,
                            R.layout.dialog, null);
                    dlg.setView(dlgView);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dlgEdt = (EditText) dlgView.findViewById(R.id.dlgEdt);
                            edtNum1.setText(dlgEdt.getText().toString());
                        }
                    });
                    dlg.setNegativeButton("취소", null);
                    dlg.show();
                }
            }
        });

        edtNum2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    dlg.setTitle("숫자2을 선택하였습니다.");
                    dlgView = (View) View.inflate(MainActivity.this,
                            R.layout.dialog, null);
                    dlg.setView(dlgView);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dlgEdt = (EditText) dlgView.findViewById(R.id.dlgEdt);
                            edtNum2.setText(dlgEdt.getText().toString());
                        }
                    });
                    dlg.setNegativeButton("취소", null);
                    dlg.show();
                }
            }
        });

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str1 = edtNum1.getText().toString();
                String str2 = edtNum2.getText().toString();
                String strOper = edtOper.getText().toString();

                try {
                    Integer number1 = Integer.parseInt(str1);
                    Integer number2 = Integer.parseInt(str2);
                    Integer result;

                    if(strOper.equals("+")){
                        result = number1 + number2;
                        tvResult.setText("결과 : " + result.toString());
                    } else if (strOper.equals("-")) {
                        result = number1 - number2;
                        tvResult.setText("결과 : " + result.toString());
                    } else if (strOper.equals("*")) {
                        result = number1 * number2;
                        tvResult.setText("결과 : " + result.toString());
                    } else if (strOper.equals("/")) {
                        result = number1 / number2;
                        tvResult.setText("결과 : " + result.toString());
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"입력 오류입니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}