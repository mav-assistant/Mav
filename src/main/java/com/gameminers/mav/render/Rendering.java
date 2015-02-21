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
package com.gameminers.mav.render;

import java.awt.Color;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

import com.gameminers.mav.Dialogs;

public class Rendering {
	public static void drawPolygon(float x, float y, float radius, float r, float g, float b, float a, int count, float z) {
		GL11.glPushMatrix();
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glTranslatef(x, y, z);
			GL11.glColor4f(r, g, b, a);
			GL11.glBegin(GL11.GL_POLYGON);
			for (int i = 0; i < count; ++i) {
				GL11.glVertex2d(Math.sin(i / ((double) count) * 2 * Math.PI) * (radius), Math.cos(i / ((double) count) * 2 * Math.PI) * (radius));
			}
			GL11.glEnd();
			GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
	}
	public static void drawTriangle(float x, float y, float radius, float r, float g, float b, float a, float z) {
		GL11.glPushMatrix();
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glTranslatef(x, y, z);
			GL11.glColor4f(r, g, b, a);
			GL11.glBegin(GL11.GL_TRIANGLES);
			for (int i = 0; i < 3; ++i) {
				GL11.glVertex2d(Math.sin(i / ((double) 3) * 2 * Math.PI) * (radius), Math.cos(i / 3D * 2 * Math.PI) * (radius));
			}
			GL11.glEnd();
			GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
	}
	
	public static void setUpDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(320, 480));
			Display.setTitle("Mav");
			Display.setResizable(true);
			Display.create(new PixelFormat(24, 8, 8, 8, 8));
		} catch (Throwable t) {
			Dialogs.showErrorDialog(null, "An error occurred while initializing LWJGL. Mav will now exit.", t);
		}
	}
	
	public static void setUpGL() {
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_LIGHTING);

		GL11.glClearDepth(1);
		
		GL11.glShadeModel(GL11.GL_FLAT);
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}
	
	public static void beforeFrame() {
		GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
	public static float[] getColor(float lum) {
		return new java.awt.Color(java.awt.Color.HSBtoRGB(RenderState.lagHue/360f, RenderState.lagSat, lum)).getComponents(null);
	}
	
	public static float tend(float a, float b, float c) {
		if (a > b) {
			float diff = (a - b);
			if (diff < 0.1f) {
				return b;
			}
			return a - (diff/c);
		} else {
			float diff = (b - a);
			if (diff < 0.1f) {
				return b;
			}
			return a + (diff/c);
		}
	}
}