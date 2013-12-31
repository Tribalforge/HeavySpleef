/**
 *   HeavySpleef - Advanced spleef plugin for bukkit
 *   
 *   Copyright (C) 2013 matzefratze123
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package de.matzefratze123.heavyspleef.config.sections;

import org.bukkit.configuration.ConfigurationSection;

import de.matzefratze123.heavyspleef.config.SpleefConfig;

public class SettingsSectionSounds implements SettingsSection {

	private static final String SECTION_NAME = "sounds";
	
	private SpleefConfig configuration;
	private ConfigurationSection section;
	
	private boolean playPlingSound;
	private boolean playLevelUpSound;
	
	public SettingsSectionSounds(SpleefConfig config) {
		this.configuration = config;
		this.section = config.getFileConfiguration().getConfigurationSection(SECTION_NAME);
		
		reload();
	}
	
	@Override
	public SpleefConfig getConfig() {
		return configuration;
	}

	@Override
	public ConfigurationSection getSection() {
		return section;
	}

	@Override
	public Object getValue(String path) {
		return section.get(path);
	}

	@Override
	public void reload() {
		playPlingSound = section.getBoolean("plingSound", true);
		playLevelUpSound = section.getBoolean("levelUp", true);
	}

	public boolean isPlayPlingSound() {
		return playPlingSound;
	}

	public boolean isPlayLevelUpSound() {
		return playLevelUpSound;
	}
	
}
