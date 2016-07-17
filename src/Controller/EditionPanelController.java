package Controller;
import Model.*;
import View.EditionPanel;


/**
 * Created by Gulgulgulgul on 27.05.2016.
 */
public class EditionPanelController implements EditionPanelListener {
    private final EditionPanel editionPanel;
    private final Model model;

    public EditionPanelController ( Model model,EditionPanel editionPanel){
        this.editionPanel = editionPanel;
        this.model = model;
    }
    @Override
    public void changeEditionMode()
    {
        model.setSettingEndPoint(false);
        model.setSettingStartPoint(false);
        if (model.getMode()) editionPanel.changeModeInfo("Off");
        else {
            editionPanel.changeModeInfo("On");
            editionPanel.changeEndPointInfo("Off");
            editionPanel.changeStartPointInfo("Off");
        }
        model.changeMode();
        editionPanel.refreshInformationPanel();
    }
    @Override
    public void setStartPoint()
    {
        if (model.getMode()) model.changeMode();
        model.setSettingEndPoint(false);
        if(!model.getSettingStartPoint())
        {
            editionPanel.changeModeInfo("Off");
            editionPanel.changeEndPointInfo("Off");
            editionPanel.changeStartPointInfo("On");
        }
        else editionPanel.changeStartPointInfo("Off");
        model.setSettingStartPoint(!model.getSettingStartPoint());
        editionPanel.refreshInformationPanel();
    }
    @Override
    public void setEndPoint()
    {
        if (model.getMode()) model.changeMode();
        model.setSettingStartPoint(false);
        if (!model.getSettingEndPoint())
        {
            editionPanel.changeModeInfo("Off");
            editionPanel.changeEndPointInfo("On");
            editionPanel.changeStartPointInfo("Off");
        }
        else editionPanel.changeEndPointInfo("Off");
        model.setSettingEndPoint(!model.getSettingEndPoint());
        editionPanel.refreshInformationPanel();
    }
}
