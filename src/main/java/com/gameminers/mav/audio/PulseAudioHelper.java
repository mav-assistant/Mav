/*
 * This file is part of Mav.
 *
 * Mav is free software: you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * Mav is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along
 * with Mav. If not, see <http://www.gnu.org/licenses/>.
 */
package com.gameminers.mav.audio;

import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

import org.classpath.icedtea.pulseaudio.PulseAudioSourceDataLine;
import org.classpath.icedtea.pulseaudio.PulseAudioTargetDataLine;

public class PulseAudioHelper {

	public static void setLineName(SourceDataLine line, String name) {
		((PulseAudioSourceDataLine)line).setName(name);
	}
	
	public static void setLineName(TargetDataLine line, String name) {
		((PulseAudioTargetDataLine)line).setName(name);
	}

}