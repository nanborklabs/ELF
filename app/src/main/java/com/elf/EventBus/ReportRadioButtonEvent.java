package com.elf.EventBus;

/**
 * Created by user on 10-08-2016.
 */
public class ReportRadioButtonEvent {
    public String mSubjectClicked;

    public ReportRadioButtonEvent(String mSubjectClicked) {
        this.mSubjectClicked = mSubjectClicked;
    }

    public String getmSubjectClicked() {
        return mSubjectClicked;
    }

    public void setmSubjectClicked(String mSubjectClicked) {
        this.mSubjectClicked = mSubjectClicked;
    }
}
