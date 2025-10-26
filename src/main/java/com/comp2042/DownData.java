package com.comp2042;

public final class DownData {
    private final ClearRow clearRow;
    private final ViewData viewData;

    public DownData(ClearRow clearRow, ViewData viewData) {
        if (clearRow == null) {
            throw new NullPointerException("clearRow cannot be null");
        }
        this.clearRow = clearRow;
        this.viewData = viewData;
    }

    public ClearRow getClearRow() {
        return clearRow;
    }

    public ViewData getViewData() {
        return viewData;
    }
}
