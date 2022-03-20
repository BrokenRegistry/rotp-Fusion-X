package rotp.ui.util.cfg;

import java.util.List;

import rotp.model.game.IGameOptions;

public class Setting_PRESET_ACTION extends BaseSetting {

    Setting_PRESET_ACTION(IGameOptions gameOptions) {
        super(
            "PRESET ACTION",
            "SAVE",
            List.of("-", "LOAD", "SAVE", "UPDATE", "LOAD AND SAVE", "LOAD AND UPDATE", "SAVE DEFAULT", "UPDATE TO DEFAULT")
            );
    }
    @Override
    void selectedGameOptions(IGameOptions gameOptions, String userOption) {}
    @Override
    void setGameOptionsToDefault(IGameOptions gameOptions) {}
    @Override
    void initComments() {
		headComments(new Comments(List.of(
            "            EXTENDED PLAYER'S SETTINGS",
            "-------------------------------------------------- ",
            " ",
            "---- This is where you add your configuration list ",
            "---- Multiple LOAD will follow this sequence",
            " "
            )));
		bottomComments(new Comments(
            "(---- The last loaded Win)"));
    }
}
