package com.lgj.answersystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lgj.answersystem.bean.UserLoginBean;
import com.lgj.answersystem.network.RetrofitUtil;
import com.lgj.answersystem.util.ActivityUtils;
import com.lgj.answersystem.util.OtherUtils;
import com.lgj.answersystem.util.PrefUtils;
import com.lgj.answersystem.util.RegexUtils;
import com.lgj.answersystem.util.RequestUtils;
import com.lgj.answersystem.util.ToastUtils;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private static String TAG = "lgj LoginActivity";
    private EditText etPhone;
    private EditText etPassword;
    private Button btnLogin;
    private TextView tvRegister;
    private TextView tvForgetPassword;
    private ImageView ivClearAccount;
    private ImageView ivSwitch;
    //    这个默认是明文显示为false，那么就是密文显示
    private boolean clearTextDisplay = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        ivClearAccount = findViewById(R.id.iv_clear_account);
        ivSwitch = findViewById(R.id.iv_switch);
        tvRegister = findViewById(R.id.tvRegister);
        tvForgetPassword = findViewById(R.id.tvForgetPassword);

        etPhone.addTextChangedListener(new MyTextWatcher(etPhone));
        etPassword.addTextChangedListener(new MyTextWatcher(etPassword));

        btnLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        tvForgetPassword.setOnClickListener(this);
        ivSwitch.setOnClickListener(this);
        ivClearAccount.setOnClickListener(this);
        changeBtnStatus();

    }

    class MyTextWatcher implements TextWatcher {
        public EditText editTextWatcher;
        public int editTextId;

        public MyTextWatcher(EditText editTextWatcher) {
            this.editTextWatcher = editTextWatcher;
            editTextId = editTextWatcher.getId();
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.i(TAG, "editTextId " + editTextId);
            CharSequence content = "";
            if (editTextId == R.id.etPhone) {
                if (s != null) {
                    content = s.toString();
                    if (TextUtils.isEmpty(content)) {
                        ivClearAccount.setVisibility(View.GONE);
                    } else {
                        ivClearAccount.setVisibility(View.VISIBLE);
                    }
                }
            }
//            改变btn的状态
            changeBtnStatus();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    public void changeBtnStatus() {
        String inputPhone = etPhone.getText().toString();
        String inputPassword = etPassword.getText().toString();
        if (!TextUtils.isEmpty(inputPhone) && !TextUtils.isEmpty(inputPassword)) {
            btnLogin.setSelected(true);
        } else {
            btnLogin.setSelected(false);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_clear_account:
                etPhone.setText("");
                break;
            case R.id.btnLogin:
                confirmLogin();
                break;
            case R.id.tvRegister:
                ActivityUtils.startActivity(RegisterActivity.class,this);
                break;
            case R.id.tvForgetPassword:
                break;
            case R.id.iv_switch:
                String passsword = etPassword.getText().toString();
                if (!clearTextDisplay) {
                    //让密码明文显示
                    ivSwitch.setImageResource(R.mipmap.ic_display);
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //让密码密文显示
                    ivSwitch.setImageResource(R.mipmap.ic_hide);
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
//                让光标移到文字后面进行显示
                if (!TextUtils.isEmpty(passsword)) {
                    etPassword.setSelection(passsword.length());
                }
                clearTextDisplay = !clearTextDisplay;
                break;
        }
    }

    private void confirmLogin() {
        if (TextUtils.isEmpty(etPhone.getText().toString())) {
            ToastUtils.showShort("请输入手机号码");
            return;
        }

        if (!RegexUtils.isMobileSimple(etPhone.getText().toString())) {
            ToastUtils.showShort("请输入正确的手机号码");
            return;
        }

        if (TextUtils.isEmpty(etPassword.getText().toString())) {
            ToastUtils.showShort("请输入密码");
            return;
        }
        HashMap<String, Object> param = new HashMap<>();
        param.put("phone", etPhone.getText().toString());
        param.put("password", etPassword.getText().toString());
        param.put("deviceId", OtherUtils.getDeviceId());
        try {
            showLoading("登录中...");
            RetrofitUtil.getApiService().login(RequestUtils.getRequestBody(param))
                    .enqueue(new Callback<UserLoginBean>() {
                        @Override
                        public void onResponse(Call<UserLoginBean> call, Response<UserLoginBean> response) {
                            dismissLoading();
                            if (response.body().getCode() == 1) {
                                ToastUtils.showShort("登录成功");
                                PrefUtils.putBoolean(MyApplication.mApplicationContext, "isLogin", true);
                                PrefUtils.putString(MyApplication.mApplicationContext, "token", response.body().getData().getToken());
                                ActivityUtils.startActivity(MainActivity.class,LoginActivity.this);
//                                ActivityUtils.startActivity(ChapterActivity.class,LoginActivity.this);
                                finish();
                            } else {
                                ToastUtils.showShort(response.body().getMessage());
                            }
                        }

                        @Override
                        public void onFailure(Call<UserLoginBean> call, Throwable t) {
                            dismissLoading();
                            ToastUtils.showShort("登录失败");
                        }
                    });
        } catch (RuntimeException e) {
            e.printStackTrace();
            dismissLoading();
            ToastUtils.showShort("登录失败");
        }
    }

    //防止快速连续点击
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (isFastDoubleClick()) {
//                这里就是分发事件，如果进来就不再进行往下分发，然后自己进行消费
                Log.w(TAG, "dispatchTouchEvent 快速点击了两下...");
                return true;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private long lastClickTime;
    private int SPACE_TIME = 200;//间隔时间

    public boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
//        当前时间赋值给最后的时间
        lastClickTime = time;
        return timeD <= SPACE_TIME;
    }
}