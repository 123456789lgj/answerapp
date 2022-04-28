package com.lgj.answersystem;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lgj.answersystem.bean.RegisterRequest;
import com.lgj.answersystem.bean.SmsResponse;
import com.lgj.answersystem.bean.UserLoginBean;
import com.lgj.answersystem.network.RetrofitUtil;
import com.lgj.answersystem.util.ActivityUtils;
import com.lgj.answersystem.util.OtherUtils;
import com.lgj.answersystem.util.PrefUtils;
import com.lgj.answersystem.util.RegexUtils;
import com.lgj.answersystem.util.RequestUtils;
import com.lgj.answersystem.util.ToastUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private EditText etName;
    private EditText etPhone;
    private EditText etCode;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private EditText etCompanyName;
    private EditText etAge;
    private Button btnRegister;
    private RadioGroup sexRadioGroup;
    private RadioButton ivService;
    private String sexStr = "";
    private TextView tvService;
    private TextView tvGetCode;
    RadioButton radioMan;
    RadioButton radioWoman;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findTitle("注册");
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etCode = findViewById(R.id.etCode);
        tvGetCode = findViewById(R.id.tvCode);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        etCompanyName = findViewById(R.id.etCompanyName);
        etAge = findViewById(R.id.etAge);
        btnRegister = findViewById(R.id.btnRegister);
        radioMan = findViewById(R.id.radioMan);
        radioWoman = findViewById(R.id.radioWoman);

        btnRegister.setOnClickListener(this);
        sexRadioGroup = findViewById(R.id.sexRadioGroup);
        ivService = findViewById(R.id.iv_service);
        tvService = findViewById(R.id.tv_service);
        ivService.setOnClickListener(this);
        tvService.setOnClickListener(this);
        tvGetCode.setOnClickListener(this);
        boolean selected = ivService.isSelected();
        System.out.println("selected 22:"+ selected);
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (RegexUtils.isMobileSimple(editable.toString())) {
                    tvGetCode.setEnabled(true);
                    tvGetCode.setBackground(getResources().getDrawable(R.drawable.shap_code_btn_press));
                } else {
                    tvGetCode.setEnabled(false);
                    tvGetCode.setBackground(getResources().getDrawable(R.drawable.shap_code_btn_normal));
                }
            }
        });
        sexRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    // 这块的状态选择是通过状态选择器选择的，radioGroup的是state_checked 控件处于被勾选
                    // state_selected true表示被选择的状态，例如在一个下拉列表中用方向键下选择其中一个选项。
                    // https://blog.csdn.net/wenzhi20102321/article/details/62928568
                    case R.id.radioWoman:
                        sexStr = "女";
                        break;
                    case R.id.radioMan:
                        sexStr = "男";
                        break;
                }
            }
        });
        initDebugFillData();
    }

    private void initDebugFillData() {
        etName.setText("李国菁");
        etPhone.setText("18291777144");
        etPassword.setText("123456");
        etConfirmPassword.setText("123456");
        etCompanyName.setText("润和软件");
        etAge.setText("31");
    }

    private CountDownTimer countDownTimer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            if (isFinishing()) {
                return;
            }
            String value = String.valueOf((int) (millisUntilFinished / 1000));
            tvGetCode.setText(value + getString(R.string.second));
            tvGetCode.setTextColor(Color.WHITE);
        }

        @Override
        public void onFinish() {
            tvGetCode.setText("验证码");
            tvGetCode.setEnabled(true);
            tvGetCode.setBackground(getResources().getDrawable(R.drawable.shap_code_btn_normal));
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRegister:
                register();
                break;
            case R.id.tv_service:
                ActivityUtils.startActivity(UserServiceActivity.class, this);
                break;
            case R.id.iv_service:
                ivService.setSelected(!ivService.isSelected());
                System.out.println("selected :" + ivService.isSelected());
                break;
            case R.id.tvCode:
                getMessageCode();
                break;
        }
    }

    private void getMessageCode() {
        if (TextUtils.isEmpty(etPhone.getText().toString())) {
            ToastUtils.showShort("请输入手机号码");
            return;
        }
        tvGetCode.setEnabled(false);
        tvGetCode.setBackground(getResources().getDrawable(R.drawable.shap_code_btn_normal));
        RetrofitUtil.getApiService().getSmsCode(etPhone.getText().toString())
                .enqueue(new Callback<SmsResponse>() {
                    @Override
                    public void onResponse(Call<SmsResponse> call, Response<SmsResponse> response) {
                        System.out.println("lgj getCode :" + response.body().getCode() );
                        if (response.body() != null && response.body().getCode() == 1) {
                            ToastUtils.showShort(getString(R.string.has_send_sms));
                            countDownTimer.start();
                        } else {
                            ToastUtils.showShort((getString(R.string.send_sms_error)));
                            tvGetCode.setEnabled(true);
                            tvGetCode.setBackground(getResources().getDrawable(R.drawable.shap_code_btn_press));
                        }
                    }

                    @Override
                    public void onFailure(Call<SmsResponse> call, Throwable t) {
                        System.out.println("lgj onFailure getCode :" + t.getMessage());
                        ToastUtils.showShort((getString(R.string.send_sms_error)));
                        tvGetCode.setEnabled(true);
                        tvGetCode.setBackground(getResources().getDrawable(R.drawable.shap_code_btn_press));
                    }
                });
    }

    private void register() {
        if (TextUtils.isEmpty(etName.getText().toString())) {
            ToastUtils.showShort("请输入姓名");
            return;
        }
        if (TextUtils.isEmpty(etPhone.getText().toString())) {
            ToastUtils.showShort("请输入手机号码");
            return;
        }
        if (!RegexUtils.isMobileSimple(etPhone.getText().toString())) {
            ToastUtils.showShort("请输入正确的手机号码");
            return;
        }
        if (TextUtils.isEmpty(etCode.getText().toString())) {
            ToastUtils.showShort("请输入验证码");
            return;
        }
        if (TextUtils.isEmpty(etPassword.getText().toString())) {
            ToastUtils.showShort("请输入密码");
            return;
        }
        if (TextUtils.isEmpty(etConfirmPassword.getText().toString())) {
            ToastUtils.showShort("请再次输入密码");
            return;
        }
        if (TextUtils.isEmpty(etCompanyName.getText().toString())) {
            ToastUtils.showShort("请输入院校/公司名称");
            return;
        }

        if (TextUtils.isEmpty(etAge.getText().toString())) {
            ToastUtils.showShort("请输入年龄");
            return;
        }

        if (!ivService.isSelected()) {
            ToastUtils.showShort("请同意用户协议及隐私声明");
            return;
        }
        showLoading("注册中");
        int sex = 1;
        if ("女".equals(sexStr)) {
            sex = 2;
        }
        RegisterRequest request = new RegisterRequest();
        request.setPhone(etPhone.getText().toString());
        request.setPassword(etPassword.getText().toString());
        if (!TextUtils.isEmpty(etAge.getText().toString())) {
            request.setAge(Integer.parseInt(etAge.getText().toString()));
        } else {
            request.setAge(0);
        }
        request.setSex(sex);
        request.setNickname(etName.getText().toString());
        request.setAuthCode(etCode.getText().toString());
        request.setCompany(etCompanyName.getText().toString());
        request.setDeviceId(OtherUtils.getDeviceId());
        RetrofitUtil.getApiService().register(RequestUtils.getRequestBody(request))
                .enqueue(new Callback<UserLoginBean>() {
                    @Override
                    public void onResponse(Call<UserLoginBean> call, Response<UserLoginBean> response) {
                        dismissLoading();
                        if (response.body().getCode() == 1) {
                            ToastUtils.showShort("注册成功");
                            PrefUtils.putBoolean(MyApplication.mApplicationContext, "isLogin", true);
                            PrefUtils.putString(MyApplication.mApplicationContext, "token", response.body().getData().getToken());
                            ActivityUtils.startActivity(MainActivity.class, RegisterActivity.this);
                            finish();
                        } else {
                            ToastUtils.showShort("注册失败");
//                            ToastUtils.showShort(response.body().getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<UserLoginBean> call, Throwable t) {
                        dismissLoading();
                        ToastUtils.showShort("注册失败");
                    }
                });
    }
}