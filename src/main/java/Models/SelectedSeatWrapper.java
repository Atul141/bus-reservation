package Models;


import Services.SeatSelectionService;

import java.util.ArrayList;
import java.util.List;

public class SelectedSeatWrapper {

    private List<String> selectedSeatWomen;
    private List<String> selectedSeatSeniorCitizen;
    private List<String> selectedSeatDisabled;
    private List<String> selectedSeatGeneral;

    public SelectedSeatWrapper() {
        selectedSeatDisabled = new ArrayList<String>();
        selectedSeatGeneral = new ArrayList<String>();
        selectedSeatSeniorCitizen = new ArrayList<String>();
        selectedSeatWomen = new ArrayList<String>();
    }


    public List<String> getSelectedSeatWomen() {
        return selectedSeatWomen;
    }

    public void setSelectedSeatWomen(List<String> selectedSeatWomen) {
        this.selectedSeatWomen = selectedSeatWomen;
    }

    public List<String> getSelectedSeatSeniorCitizen() {
        return selectedSeatSeniorCitizen;
    }

    public void setSelectedSeatSeniorCitizen(List<String> selectedSeatSeniorCitizen) {
        this.selectedSeatSeniorCitizen = selectedSeatSeniorCitizen;
    }

    public List<String> getSelectedSeatDisabled() {
        return selectedSeatDisabled;
    }

    public void setSelectedSeatDisabled(List<String> selectedSeatDisabled) {
        this.selectedSeatDisabled = selectedSeatDisabled;
    }

    public List<String> getSelectedSeatGeneral() {
        return selectedSeatGeneral;
    }

    public void setSelectedSeatGeneral(List<String> selectedSeatGeneral) {
        this.selectedSeatGeneral = selectedSeatGeneral;
    }
}
