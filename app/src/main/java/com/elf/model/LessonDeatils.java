package com.elf.model;

/**
 * Created by nandhu on 3/8/16.
 */
public class LessonDeatils {
    public int one_mark;
    public int two_mark;
    public int six_mark;
    public int ten_mark;

    public LessonDeatils(int one_mark, int two_mark, int six_mark, int ten_mark) {
        this.one_mark = one_mark;
        this.two_mark = two_mark;
        this.six_mark = six_mark;
        this.ten_mark = ten_mark;
    }

    public LessonDeatils(int ten_mark, int two_mark, int one_mark) {
        this.ten_mark = ten_mark;
        this.two_mark = two_mark;
        this.one_mark = one_mark;
    }

    public int getOne_mark() {
        return one_mark;
    }

    public void setOne_mark(int one_mark) {
        this.one_mark = one_mark;
    }

    public int getTwo_mark() {
        return two_mark;
    }

    public void setTwo_mark(int two_mark) {
        this.two_mark = two_mark;
    }

    public int getSix_mark() {
        return six_mark;
    }

    public void setSix_mark(int six_mark) {
        this.six_mark = six_mark;
    }

    public int getTen_mark() {
        return ten_mark;
    }

    public void setTen_mark(int ten_mark) {
        this.ten_mark = ten_mark;
    }
}
