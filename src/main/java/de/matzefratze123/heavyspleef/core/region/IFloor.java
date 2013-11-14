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
package de.matzefratze123.heavyspleef.core.region;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import de.matzefratze123.heavyspleef.core.Game;
import de.matzefratze123.heavyspleef.database.DatabaseSerializeable;
import de.matzefratze123.heavyspleef.objects.SimpleBlockData;

public interface IFloor extends Comparable<IFloor>, DatabaseSerializeable {
	
	public String FILE_EXTENSION = "ssf";
	
	public int getId();
	
	public SimpleBlockData getBlockData();
	
	public void setBlockData(SimpleBlockData data);
	
	public FloorType getType();
	
	public boolean contains(Location location);
	
	public void generate();
	
	public void remove();
	
	public int getY();
	
	public ConfigurationSection serialize(Game game);

	public String asPlayerInfo();

}
