package com.isoftstone.randomchoose;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.isoftstone.randomchoose.adapter.GridViewAdapter;
import com.isoftstone.randomchoose.bean.Student;
import com.isoftstone.randomchoose.utils.RandomUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btAdd, btReduce;
    private EditText edtNumber;
    int num = 0; //数量
    private GridView gv;//显示学生
    private TextView begin;//开始抽取
    private List<Student> students;
    private GridViewAdapter adapter;
    boolean isChoose = false;
    boolean isInit = true;
    List<Integer> count = new ArrayList<Integer>();//存储选择的学生标记
    private ScrollView sv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        btAdd = (Button) findViewById(R.id.addbt);
        btReduce = (Button) findViewById(R.id.subbt);
        edtNumber = (EditText) findViewById(R.id.edt);
        gv = (GridView) findViewById(R.id.gv);
        begin = (TextView) findViewById(R.id.begain);
        sv = (ScrollView) findViewById(R.id.sv);
        btAdd.setTag("+");
        btReduce.setTag("-");
        //设置输入类型为数字
        edtNumber.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        edtNumber.setText(String.valueOf(num));
        SetViewListener();
        setBeginListener();
        loadData();
    }

    //开始按钮的点击事件,执行随机抽选的效果
    private void setBeginListener() {
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isInit) {
                    if (num > 0 && students.size() > 0) {
                        begin.setText("清除");
                        isInit = false;
                        count.clear();
                        Log.e("num", "num==" + num);
                        //生成几个随机不重复的数
                        int[] ints = RandomUtils.randomArray(0, students.size() - 1, num);
                        for (int anInt : ints) {
                            count.add(anInt);
                        }
                        //滚动到指定位置
                        //sv.scrollTo(0, 100);
                        for (int i = 0; i < count.size(); i++) {
                            Integer integer = count.get(i);
                            Student student = students.get(integer);
                            student.setSelected(true);
                        }
                        isChoose = true;
                        if (adapter != null) {
                            adapter.updataChange(isChoose, count, students);
                        }
                    } else {
                        isChoose = false;
                        count.clear();
                        if (adapter != null) {
                            adapter.updataChange(isChoose, count, students);
                        }
                        Toast.makeText(MainActivity.this, "请选择人数", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    begin.setText("开始");
                    isInit = true;
                    count.clear();
                    for (Student student : students) {
                        student.setSelected(false);
                    }
                    isChoose = true;
                    if (adapter != null) {
                        adapter.updataChange(isChoose, count, students);
                    }
                }
            }
        });
    }

    /**
     * 模拟数据进行加载
     * 展示
     */
    private void loadData() {
        if (students == null) {
            students = new ArrayList<>();
        }
        for (int i = 0; i < 50; i++) {
            Student stu = new Student();
            stu.setName("测试" + i);
            students.add(stu);
        }
        adapter = new GridViewAdapter(this, students);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "点击了" + students.get(i).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 设置文本变化相关监听事件
     */
    private void SetViewListener() {
        btAdd.setOnClickListener(new OnButtonClickListener());
        btReduce.setOnClickListener(new OnButtonClickListener());
        edtNumber.addTextChangedListener(new OnTextChangeListener());
    }

    /**
     * 加减按钮事件监听器
     */
    class OnButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String numString = edtNumber.getText().toString();
            if (numString == null || numString.equals("")) {
                num = 0;
                edtNumber.setText("0");
            } else {
                if (v.getTag().equals("+")) {
                    count.clear();
                    isChoose = false;
                    if (adapter != null) {
                        adapter.updataChange(isChoose, count, students);
                    }
                    if (++num > students.size()) {//先加，再判断
                        num--;
                        Toast.makeText(MainActivity.this, "选择达到最大上限", Toast.LENGTH_SHORT).show();
                    } else {
                        edtNumber.setText(String.valueOf(num));
                    }
                } else if (v.getTag().equals("-")) {
                    count.clear();
                    isChoose = false;
                    if (adapter != null) {
                        adapter.updataChange(isChoose, count, students);
                    }
                    if (--num < 0) {//先减，再判断
                        num++;
                        Toast.makeText(MainActivity.this, "选择达到最小", Toast.LENGTH_SHORT).show();
                    } else {
                        edtNumber.setText(String.valueOf(num));
                    }
                }
            }
        }
    }

    /**
     * EditText输入变化事件监听器
     */
    class OnTextChangeListener implements TextWatcher {
        @Override
        public void afterTextChanged(Editable s) {
            String numString = s.toString();
            if (numString == null || numString.equals("")) {
                num = 0;
            } else {
                int numInt = Integer.parseInt(numString);
                if (numInt < 0) {
                    Toast.makeText(MainActivity.this, "请输入一个大于0的数字", Toast.LENGTH_SHORT).show();
                } else {           //设置EditText光标位置 为文本末端
                    edtNumber.setSelection(edtNumber.getText().toString().length());
                    num = numInt;
                }
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
    }
}
