package de.tudarmstadt.informatik.fop.breakout.constants;

public interface ItemParameters {

	// Items
	public static final String ITEM_SPEEDUP_ID = "item_speedup";
	public static final String ITEM_ONEUP_ID = "item_oneup";
	public static final String ITEM_MIRRORSTICK_ID = "item_mirror";
	public static final String ITEM_RANDOMREBOUND_ID = "item_randomrebound";
	public static final String ITEM_EXPANDSTICK_ID = "item_expand";
	public static final String ITEM_COMPRESSSTICK_ID = "item_compress";
	public static final String ITEM_SHOOTPLAYER_ID = "item_shootplayer";
	public static final String ITEM_SLOWDOWN_ID = "item_slowdown";

	public static final String SPEEDUP_LOGO_PATH = "images/items/item_speedup.png";
	public static final String ONEUP_LOGO_PATH = "images/items/item_oneup.png";
	public static final String MIRRORSTICK_LOGO_PATH = "images/items/item_mirror.png";
	public static final String RANDOMREBOUND_LOGO_PATH = "images/items/item_randomrebound.png";
	public static final String EXPANDSTICK_LOGO_PATH = "images/items/item_expand.png";
	public static final String COMPRESSSTICK_LOGO_PATH = "images/items/item_compress.png";
	public static final String SHOOTPLAYER_LOGO_PATH = "images/items/item_shootplayer.png";
	public static final String SLOWDOWN_LOGO_PATH = "images/items/item_slowdown.png";
	public static final String MAGMADROP_LOGO_PATH = "images/items/item_magmadrop.png";
	
	public static final String SPEEDUP_SOUND = "";
	public static final String ONEUP_SOUND = "";
	public static final String MIRROR_SOUND = "";
	public static final String RANDOMREBOUND_SOUND = "";
	public static final String EXPANDSTICK_SOUND = "";
	public static final String COMPRESSSTICK_SOUND = "";
	public static final String SHOOTPLAYER_SOUND = "";
	public static final String SLOWDOWN_SOUND = "";
	public static final String MAGMADROP_SOUND = "";

	public static enum ItemType {
		SPEEDUP, ONEUP, MIRRORSTICK, RANDOMREBOUND, EXPANDSTICK, COMPRESSSTICK, SLOWDOWN, SHOOTPLAYER, MAGMADROP
	};

}
