package com.lgj.answersystem.answer;

import com.lgj.answersystem.bean.RecordDoneQuestionBean;

import java.util.HashMap;

public class RecordUserDoneQuestion {
    public int ERROR_COUNT = 0;
    public int RIGHT_COUNT = 0;

    private RecordUserDoneQuestion() {
    }

    // 这个集合用于，ViewPager内存回收之后，记录用户之前答过的题
    private HashMap<Integer, RecordDoneQuestionBean> hashMap = new HashMap<>();
    // 记录该题的状态（正确、错误、未答）
    private HashMap<Integer, OptionStatus> mCardOptionStatus = new HashMap<>();
    private static volatile RecordUserDoneQuestion sInstance;

    public static RecordUserDoneQuestion getInstance() {
        if (sInstance == null) {
            synchronized (RecordUserDoneQuestion.class) {
                if (sInstance == null) {
                    sInstance = new RecordUserDoneQuestion();
                }
            }
        }
        return sInstance;
    }

    public void record(int currentPosition, RecordDoneQuestionBean recordDoneQuestion) {
        hashMap.put(currentPosition, recordDoneQuestion);
    }

    public void clearRecord() {
        hashMap.clear();
        mCardOptionStatus.clear();
        ERROR_COUNT = 0;
        RIGHT_COUNT = 0;
    }


    public RecordDoneQuestionBean getCurrentRecord(int mCurrentPosition) {
        return hashMap.get(mCurrentPosition);
    }

    public void recordOptionStatus(int key, OptionStatus optionStatus) {
        mCardOptionStatus.put(key, optionStatus);
    }

    public OptionStatus getCardStatus(int mCurrentPosition) {
        return mCardOptionStatus.get(mCurrentPosition);
    }
}
