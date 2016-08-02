package com.elf.Fragment.TesViewholder;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.elf.model.Testdetail;

import java.util.List;
import java.util.Objects;

/**
 * Created by nandhu on 3/8/16.
 */
public class TestParentObject implements ParentListItem {

    public List<Testdetail> mTestdetail;


    public TestParentObject(List<Testdetail> mTestdetail) {
        this.mTestdetail = mTestdetail;
    }

    @Override
    public List<Testdetail> getChildItemList() {
        return mTestdetail;
    }

    public void setmTestdetail(List<Testdetail> mTestdetail) {
        this.mTestdetail = mTestdetail;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
