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
package de.matzefratze123.heavyspleef.util;

import javax.swing.JOptionPane;

/**
 * Ignore this class
 */
public class OptionPane {

	/**
	 * Just if the user opens the HeavySpleef.jar
	 */
	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, 
				"This application/plugin requires Bukkit!", "HeavySpleef", JOptionPane.OK_OPTION);
	}

}
