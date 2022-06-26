package com.clowns.foodapp.model.view;

public class ChoiceItem {
    private String choiceId;
    private String choiceName;
    private double choicePrice;
    private String choiceImg;
    private boolean isSelected;

    public ChoiceItem(String choiceName, double choicePrice, String choiceImg) {
        this.choiceName = choiceName;
        this.choicePrice = choicePrice;
        this.choiceImg = choiceImg;
    }

    public String getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(String choiceId) {
        this.choiceId = choiceId;
    }

    public String getChoiceName() {
        return choiceName;
    }

    public void setChoiceName(String choiceName) {
        this.choiceName = choiceName;
    }

    public double getChoicePrice() {
        return choicePrice;
    }

    public void setChoicePrice(double choicePrice) {
        this.choicePrice = choicePrice;
    }

    public String getChoiceImg() {
        return choiceImg;
    }

    public void setChoiceImg(String choiceImg) {
        this.choiceImg = choiceImg;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
