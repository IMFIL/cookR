package com.uottawa.cookr;

/**
 * Created by filipslatinac on 2016-11-23.
 */

public class SelectionItems {

    private String[] elements;
    private boolean[] selected;
    private String name;

    public SelectionItems(String[] items, String title) {
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
            selected[id] = false;
    }

    public String getName(){
        return name;
    }

    public String[] getArray() {
        return elements;
    }

    public boolean isSelected(int pos) {
        return selected[pos];

    }

    private boolean moreThanOneSelect (){
        int repetitions = 0;

        for(int i = 0; i < elements.length;i++){
            if (isSelected(i)){
                repetitions++;
            }
        }
        if (repetitions > 1) return true;

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

    private int getTotalSelected(){
        int count = 0;

        for(int i=0; i<elements.length;i++){
            if(selected[i]){
                count++;
            }
        }
        return  count;
    }

    public String [] getSelected(){
        String [] tmp = new String [getTotalSelected()];
        int count = 0;

        for(int i=0; i < elements.length;i++){
            if(selected[i]){
                tmp[count++] = elements[i];
            }
        }
        return tmp;
    };

}


