package com.uottawa.cookr;

/**
 * Created by filipslatinac on 2016-11-23.
 */

public class selection_items {

    private String[] elements;
    private boolean[] selected;
    private String name;

    public selection_items(String[] items,String title) {
        elements = items;
        selected = new boolean[elements.length];
        name = title;

        for (int i = 0; i < elements.length; i++) {
            selected[i] = false;
        }
    }


    public void select(int id) {
        if (id - 1 > selected.length) {
        } else {
            selected[id] = true;
        }

    }


    public void unselect(int id) {
        if (id - 1 > selected.length) {
        } else {
            selected[id] = false;
        }
    }

    public String[] getArray() {
        return elements;
    }

    public boolean isSelected(int pos) {
        if (pos - 1 > selected.length) {
            return false;
        } else {
            return selected[pos];
        }

    }

    private boolean moreThanOneSelect (){
        int repetitions = 0;

        for(int i = 0; i < elements.length;i++){
            if (repetitions > 1) return true;
            if (isSelected(i)){
                repetitions++;
            }
        }

        return false;
    }

    private String getOnlyNotSelected() {
        for (int i = 0; i < elements.length; i++) {
            if (isSelected(i)) return elements[i];
        }

        return name;
    }

    public String getSelectionText(){
        if (moreThanOneSelect()) return "Multiple selections";
        else return getOnlyNotSelected();
    }

}


