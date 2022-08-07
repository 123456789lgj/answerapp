package com.lgj.answersystem.answer;

// 用于更新错题和正确题的个数
public interface IUpdateRightAndErrorCount {
    void updateRightCount();

    void updateErrorCount();

    /*
    * isShow 是否展示解析
    * chooseOption 自己的答题选项
    * */
    void showAnalysis(boolean isShow, String chooseOption);
}
