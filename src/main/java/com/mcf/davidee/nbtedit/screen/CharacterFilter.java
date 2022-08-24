package com.mcf.davidee.nbtedit.screen;

import com.mcf.davidee.nbtedit.NBTStringHelper;

public class CharacterFilter {
	public static String filerAllowedCharacters(String str, boolean section) {
		StringBuilder sb = new StringBuilder();
		for (char c : str.toCharArray()) {
			if ((c != 167 && c >= ' ' && c != 127) || (section && (c == NBTStringHelper.SECTION_SIGN || c == '\n'))) {
				sb.append(c);
			}
		}

		return sb.toString();
	}
}
