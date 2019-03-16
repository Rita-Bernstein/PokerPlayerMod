package ThePokerPlayer.cards;

import ThePokerPlayer.PokerPlayerMod;
import ThePokerPlayer.actions.PokerCardDiscoveryAction;
import ThePokerPlayer.patches.CardColorEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class JackOfSpades extends CustomCard {
	private static final String RAW_ID = "JackOfSpades";
	public static final String ID = PokerPlayerMod.makeID(RAW_ID);
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String IMG = PokerPlayerMod.GetCardPath(RAW_ID);
	private static final int COST = 2;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final AbstractCard.CardType TYPE = CardType.SKILL;
	private static final AbstractCard.CardColor COLOR = CardColorEnum.POKER_PLAYER_GRAY;
	private static final AbstractCard.CardRarity RARITY = CardRarity.UNCOMMON;
	private static final AbstractCard.CardTarget TARGET = CardTarget.SELF;

	private static final int POWER = 11;
	private static final int MAGIC = 11;
	private static final int NEW_COST = 1;

	public JackOfSpades() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
		this.baseBlock = POWER;
		this.baseMagicNumber = MAGIC;
		this.magicNumber = this.baseMagicNumber;
		this.exhaust = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SLASH_DIAGONAL));

		AbstractDungeon.actionManager.addToBottom(new PokerCardDiscoveryAction(this, this.magicNumber, PokerCard.Suit.Club));
	}

	public AbstractCard makeCopy() {
		return new JackOfSpades();
	}

	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			this.upgradeBaseCost(NEW_COST);
		}
	}
}