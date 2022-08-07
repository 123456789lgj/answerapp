package com.lgj.answersystem.answer;

import java.util.List;

public interface IQuestionBankCompleteListener<T> {
    void onComplete(List<T> data);

    void showLoadingDialog();

    void dismissLoadingDialog();
}
