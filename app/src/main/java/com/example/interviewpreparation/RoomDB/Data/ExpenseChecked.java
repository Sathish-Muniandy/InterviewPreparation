package com.example.interviewpreparation.RoomDB.Data;

public class ExpenseChecked {
    private String name;
    private boolean isChecked;

    public String getName() {
        return name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public ExpenseChecked(String name, boolean isChecked) {
        this.name = name;
        this.isChecked = isChecked;
    }
}
