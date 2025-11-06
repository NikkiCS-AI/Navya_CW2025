package com.comp2042;

public final class DownData {
    private final ClearRow clearRow;
    private final ViewData viewData;

    // stores cleared row and updated view data for down action
    public DownData(ClearRow clearRow, ViewData viewData) {

        this.clearRow = clearRow;
        this.viewData = viewData;
    }

    //returns cleared row
    public ClearRow getClearRow() {
        return clearRow;
    }

    //returns updated view data
    public ViewData getViewData() {
        return viewData;
    }
}
