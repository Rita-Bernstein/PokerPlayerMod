package ThePokerPlayer.powers;

import ThePokerPlayer.PokerPlayerMod;
import ThePokerPlayer.actions.PokerCardChangeAction;
import ThePokerPlayer.interfaces.IShowdownEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class GamblerFormPower extends AbstractPower implements IShowdownEffect {
	public AbstractCreature source;

	private static final String RAW_ID = "GamblerFormPower";
	public static final String POWER_ID = PokerPlayerMod.makeID(RAW_ID);
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	public static final TextureAtlas.AtlasRegion IMG128 = new TextureAtlas.AtlasRegion(
			ImageMaster.loadImage(PokerPlayerMod.GetPowerPath(RAW_ID, 128)), 0, 0, 84, 84);
	public static final TextureAtlas.AtlasRegion IMG48 = new TextureAtlas.AtlasRegion(
			ImageMaster.loadImage(PokerPlayerMod.GetPowerPath(RAW_ID, 48)), 0, 0, 32, 32);

	public GamblerFormPower(AbstractCreature owner, AbstractCreature source, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		this.updateDescription();
		this.type = PowerType.BUFF;
		this.isTurnBased = false;
		this.region128 = IMG128;
		this.region48 = IMG48;
		this.source = source;
	}

	@Override
	public void onShowdownStart() {
		AbstractDungeon.actionManager.addToBottom(new PokerCardChangeAction(this.owner, this.source, PokerCardChangeAction.Mode.RANK_CHANGE_ANY, this.amount, -1));
	}

	@Override
	public void updateDescription() {
		if (amount == 1) {
			this.description = DESCRIPTIONS[0];
		} else {
			this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
		}
	}
}
