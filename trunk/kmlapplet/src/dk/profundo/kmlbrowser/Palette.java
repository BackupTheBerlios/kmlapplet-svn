package dk.profundo.kmlbrowser;

import java.awt.Color;
import java.awt.image.IndexColorModel;

/*
 * KML Applet
 * 
 * Copyright (C) 2006 Erik Martino Hansen
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 * 
 */

/**
 * The color palette of kiss players
 * 
 * @author ermh
 * 
 */
public class Palette extends IndexColorModel {
	public Palette() {
		super(8, 256, Palette.getR(), Palette.getG(), Palette.getB(), Palette
				.getA());
	}

	public static Color getColor(int index) {
		try {
			return palette[index];
		} catch (Exception e) {
			return getColor(255);
		}
	}

	public final static byte[] getR() {
		byte[] b = new byte[palette.length];
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) palette[i].getRed();
		}
		return b;
	}

	public final static byte[] getG() {
		byte[] b = new byte[palette.length];
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) palette[i].getGreen();
		}
		return b;
	}

	public final static byte[] getB() {
		byte[] b = new byte[palette.length];
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) palette[i].getBlue();
		}
		return b;
	}

	public final static byte[] getA() {
		byte[] b = new byte[palette.length];
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) palette[i].getAlpha();
		}
		return b;
	}

	final private static Color[] palette = { new Color(51, 51, 51, 0), // 0
			new Color(22, 27, 88, 255), // 1
			new Color(128, 128, 128, 64), // 2
			new Color(128, 128, 128, 128), // 3
			new Color(128, 128, 128, 191), // 4
			new Color(22, 27, 88, 64), // 5
			new Color(22, 27, 88, 102), // 6
			new Color(22, 27, 88, 191), // 7
			new Color(249, 162, 57, 64), // 8
			new Color(249, 162, 57, 128), // 9
			new Color(249, 162, 57, 191), // 10
			new Color(253, 31, 31, 255), // 11
			new Color(253, 64, 31, 255), // 12
			new Color(253, 109, 31, 255), // 13
			new Color(253, 160, 31, 255), // 14
			new Color(253, 203, 31, 255), // 15
			new Color(0, 0, 0, 255), // 16
			new Color(17, 17, 17, 255), // 17
			new Color(34, 34, 34, 255), // 18
			new Color(51, 51, 51, 255), // 19
			new Color(68, 68, 68, 255), // 20
			new Color(85, 85, 85, 255), // 21
			new Color(102, 102, 102, 255), // 22
			new Color(119, 119, 119, 255), // 23
			new Color(136, 136, 136, 255), // 24
			new Color(153, 153, 153, 255), // 25
			new Color(170, 170, 170, 255), // 26
			new Color(187, 187, 187, 255), // 27
			new Color(204, 204, 204, 255), // 28
			new Color(221, 221, 221, 255), // 29
			new Color(238, 238, 238, 255), // 30
			new Color(255, 255, 255, 255), // 31
			new Color(22, 27, 88, 102), // 32
			new Color(34, 39, 97, 116), // 33
			new Color(63, 67, 118, 136), // 34
			new Color(97, 101, 143, 160), // 35
			new Color(129, 133, 166, 184), // 36
			new Color(166, 168, 191, 211), // 37
			new Color(198, 200, 215, 234), // 38
			new Color(235, 236, 241, 254), // 39
			new Color(29, 29, 249, 255), // 40
			new Color(29, 39, 249, 255), // 41
			new Color(29, 57, 249, 255), // 42
			new Color(29, 80, 249, 255), // 43
			new Color(29, 110, 249, 255), // 44
			new Color(29, 141, 249, 255), // 45
			new Color(29, 167, 249, 255), // 46
			new Color(29, 187, 249, 255), // 47
			new Color(22, 27, 88, 102), // 48
			new Color(35, 37, 84, 116), // 49
			new Color(64, 60, 72, 136), // 50
			new Color(99, 88, 59, 160), // 51
			new Color(134, 116, 46, 184), // 52
			new Color(167, 141, 33, 211), // 53
			new Color(202, 169, 20, 234), // 54
			new Color(233, 194, 9, 254), // 55
			new Color(6, 1, 0, 255), // 56
			new Color(41, 8, 0, 255), // 57
			new Color(75, 15, 0, 255), // 58
			new Color(109, 22, 0, 255), // 59
			new Color(143, 29, 0, 255), // 60
			new Color(178, 36, 0, 255), // 61
			new Color(212, 42, 0, 255), // 62
			new Color(247, 49, 0, 255), // 63
			new Color(0, 0, 255, 255), // 64
			new Color(255, 0, 0, 255), // 65
			new Color(255, 0, 255, 255), // 66
			new Color(0, 255, 0, 255), // 67
			new Color(0, 255, 255, 255), // 68
			new Color(255, 255, 0, 255), // 69
			new Color(0, 153, 255, 255), // 70
			new Color(255, 153, 0, 255), // 71
			new Color(255, 0, 153, 255), // 72
			new Color(236, 190, 155, 255), // 73
			new Color(195, 206, 220, 255), // 74
			new Color(165, 228, 128, 255), // 75
			new Color(149, 176, 206, 255), // 76
			new Color(236, 152, 111, 255), // 77
			new Color(236, 206, 106, 255), // 78
			new Color(179, 133, 174, 255), // 79
			new Color(65, 65, 221, 255), // 80
			new Color(221, 65, 65, 255), // 81
			new Color(221, 65, 220, 255), // 82
			new Color(65, 221, 65, 255), // 83
			new Color(65, 220, 221, 255), // 84
			new Color(220, 221, 65, 255), // 85
			new Color(65, 158, 221, 255), // 86
			new Color(221, 158, 65, 255), // 87
			new Color(221, 65, 158, 255), // 88
			new Color(214, 185, 164, 255), // 89
			new Color(190, 196, 205, 255), // 90
			new Color(169, 208, 146, 255), // 91
			new Color(159, 176, 194, 255), // 92
			new Color(212, 160, 136, 255), // 93
			new Color(212, 193, 133, 255), // 94
			new Color(176, 149, 173, 255), // 95
			new Color(146, 146, 250, 255), // 96
			new Color(250, 146, 146, 255), // 97
			new Color(250, 146, 249, 255), // 98
			new Color(146, 250, 146, 255), // 99
			new Color(146, 249, 250, 255), // 100
			new Color(249, 250, 146, 255), // 101
			new Color(146, 208, 250, 255), // 102
			new Color(250, 208, 146, 255), // 103
			new Color(250, 146, 208, 255), // 104
			new Color(245, 226, 212, 255), // 105
			new Color(229, 233, 239, 255), // 106
			new Color(215, 241, 200, 255), // 107
			new Color(208, 220, 232, 255), // 108
			new Color(244, 209, 193, 255), // 109
			new Color(244, 231, 191, 255), // 110
			new Color(220, 201, 218, 255), // 111
			new Color(77, 77, 246, 255), // 112
			new Color(246, 77, 77, 255), // 113
			new Color(246, 77, 245, 255), // 114
			new Color(77, 246, 77, 255), // 115
			new Color(77, 245, 246, 255), // 116
			new Color(245, 246, 77, 255), // 117
			new Color(77, 178, 246, 255), // 118
			new Color(246, 178, 77, 255), // 119
			new Color(246, 77, 178, 255), // 120
			new Color(238, 207, 184, 255), // 121
			new Color(212, 219, 228, 255), // 122
			new Color(189, 231, 165, 255), // 123
			new Color(179, 197, 217, 255), // 124
			new Color(236, 180, 153, 255), // 125
			new Color(236, 216, 150, 255), // 126
			new Color(197, 167, 194, 255), // 127
			new Color(34, 34, 229, 255), // 128
			new Color(229, 34, 34, 255), // 129
			new Color(229, 34, 228, 255), // 130
			new Color(34, 229, 34, 255), // 131
			new Color(34, 228, 229, 255), // 132
			new Color(228, 229, 34, 255), // 133
			new Color(34, 150, 229, 255), // 134
			new Color(229, 150, 34, 255), // 135
			new Color(229, 34, 150, 255), // 136
			new Color(219, 184, 157, 255), // 137
			new Color(189, 198, 208, 255), // 138
			new Color(163, 212, 135, 255), // 139
			new Color(151, 172, 195, 255), // 140
			new Color(217, 153, 122, 255), // 141
			new Color(217, 194, 118, 255), // 142
			new Color(172, 138, 169, 255), // 143
			new Color(25, 25, 168, 255), // 144
			new Color(168, 25, 25, 255), // 145
			new Color(168, 25, 167, 255), // 146
			new Color(25, 168, 25, 255), // 147
			new Color(25, 167, 168, 255), // 148
			new Color(167, 168, 25, 255), // 149
			new Color(25, 110, 168, 255), // 150
			new Color(168, 110, 25, 255), // 151
			new Color(168, 25, 110, 255), // 152
			new Color(160, 135, 115, 255), // 153
			new Color(138, 145, 152, 255), // 154
			new Color(119, 155, 99, 255), // 155
			new Color(111, 126, 143, 255), // 156
			new Color(159, 112, 89, 255), // 157
			new Color(159, 142, 86, 255), // 158
			new Color(126, 101, 124, 255), // 159
			new Color(16, 16, 111, 255), // 160
			new Color(111, 16, 16, 255), // 161
			new Color(111, 16, 110, 255), // 162
			new Color(16, 111, 16, 255), // 163
			new Color(16, 110, 111, 255), // 164
			new Color(110, 111, 16, 255), // 165
			new Color(16, 73, 111, 255), // 166
			new Color(111, 73, 16, 255), // 167
			new Color(111, 16, 73, 255), // 168
			new Color(107, 89, 77, 255), // 169
			new Color(92, 96, 101, 255), // 170
			new Color(79, 103, 66, 255), // 171
			new Color(73, 84, 95, 255), // 172
			new Color(106, 74, 59, 255), // 173
			new Color(106, 94, 57, 255), // 174
			new Color(84, 67, 82, 255), // 175
			new Color(11, 11, 72, 255), // 176
			new Color(72, 11, 11, 255), // 177
			new Color(72, 11, 72, 255), // 178
			new Color(11, 72, 11, 255), // 179
			new Color(11, 72, 72, 255), // 180
			new Color(72, 72, 11, 255), // 181
			new Color(11, 47, 72, 255), // 182
			new Color(72, 47, 11, 255), // 183
			new Color(72, 11, 47, 255), // 184
			new Color(69, 58, 50, 255), // 185
			new Color(60, 63, 66, 255), // 186
			new Color(52, 67, 43, 255), // 187
			new Color(47, 55, 62, 255), // 188
			new Color(69, 48, 38, 255), // 189
			new Color(69, 61, 37, 255), // 190
			new Color(55, 44, 54, 255), // 191
			new Color(8, 8, 52, 255), // 192
			new Color(52, 8, 8, 255), // 193
			new Color(52, 8, 51, 255), // 194
			new Color(8, 52, 8, 255), // 195
			new Color(8, 51, 52, 255), // 196
			new Color(51, 52, 8, 255), // 197
			new Color(8, 34, 52, 255), // 198
			new Color(52, 34, 8, 255), // 199
			new Color(52, 8, 34, 255), // 200
			new Color(50, 42, 36, 255), // 201
			new Color(43, 45, 47, 255), // 202
			new Color(37, 48, 30, 255), // 203
			new Color(34, 39, 44, 255), // 204
			new Color(49, 35, 28, 255), // 205
			new Color(49, 44, 27, 255), // 206
			new Color(39, 31, 38, 255), // 207
			new Color(27, 27, 183, 128), // 208
			new Color(183, 27, 27, 128), // 209
			new Color(183, 27, 182, 128), // 210
			new Color(27, 183, 27, 128), // 211
			new Color(27, 182, 183, 128), // 212
			new Color(182, 183, 27, 128), // 213
			new Color(27, 120, 183, 128), // 214
			new Color(183, 120, 27, 128), // 215
			new Color(183, 27, 120, 128), // 216
			new Color(175, 147, 126, 128), // 217
			new Color(151, 158, 166, 128), // 218
			new Color(130, 169, 108, 128), // 219
			new Color(121, 138, 156, 128), // 220
			new Color(174, 122, 97, 128), // 221
			new Color(174, 155, 94, 128), // 222
			new Color(138, 110, 135, 128), // 223
			new Color(41, 98, 65, 255), // 224
			new Color(90, 27, 125, 255), // 225
			new Color(233, 133, 9, 255), // 226
			new Color(46, 184, 111, 255), // 227
			new Color(27, 65, 152, 255), // 228
			new Color(220, 71, 30, 255), // 229
			new Color(198, 71, 171, 255), // 230
			new Color(106, 71, 171, 255), // 231
			new Color(79, 122, 214, 255), // 232
			new Color(214, 149, 95, 255), // 233
			new Color(128, 149, 174, 255), // 234
			new Color(128, 195, 174, 255), // 235
			new Color(76, 114, 146, 255), // 236
			new Color(179, 68, 65, 255), // 237
			new Color(179, 255, 0, 255), // 238
			new Color(247, 130, 100, 255), // 239
			new Color(100, 57, 30, 255), // 240
			new Color(237, 196, 5, 64), // 241
			new Color(237, 196, 5, 128), // 242
			new Color(237, 196, 5, 191), // 243
			new Color(237, 196, 5, 255), // 244
			new Color(255, 255, 255, 64), // 245
			new Color(255, 255, 255, 128), // 246
			new Color(255, 255, 255, 191), // 247
			new Color(0, 0, 0, 191), // 248
			new Color(0, 0, 0, 128), // 249
			new Color(0, 0, 0, 64), // 250
			new Color(0, 0, 0, 102), // 251
			new Color(0, 0, 0, 255), // 252
			new Color(249, 162, 57, 255), // 253
			new Color(0, 0, 0, 143), // 254
			new Color(255, 255, 255, 255), // 255
	};
}
