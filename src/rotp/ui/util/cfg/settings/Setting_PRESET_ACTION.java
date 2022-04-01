package rotp.ui.util.cfg.settings;

import java.util.List;

import br.config.AbstractSetting;
import br.config.comment.Comment;
import rotp.model.game.IGameOptions;

public class Setting_PRESET_ACTION extends AbstractSetting <IGameOptions> {

    Setting_PRESET_ACTION(IGameOptions gameOptions) { super(
            "PRESET ACTION",
            List.of("-", "LOAD", "SAVE", "UPDATE", "LOAD AND SAVE", "LOAD AND UPDATE", "SAVE DEFAULT", "UPDATE TO DEFAULT"),
            "SAVE"
            );
    }
    @Override
	public
    String getSelectedOption(IGameOptions gO) {
		return "-";
	}
	@Override
	public
	void setSelectedOption(IGameOptions gO, String userOption) {
	}
	@Override
	public
	void setSelectedOptionToInitial(IGameOptions gO) {
	}
    @Override
    public void initComments() {
		headComments(new Comment(List.of(
            "            EXTENDED PLAYER'S SETTINGS",
            "-------------------------------------------------- ",
            " ",
            "---- This is where you add your configuration list ",
            "---- Multiple LOAD will follow their sequence",
            " " )));
		bottomComments(new Comment(
            "(---- The last loaded Win)" ));
    }
}
