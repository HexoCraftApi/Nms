package com.github.hexocraftapi.nms.utils;

/*
 * Copyright 2016 hexosse
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.github.hexocraftapi.nms.NmsChunk;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Collection;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class NmsChunkUtil
{
	public static int getBrightness(int enumSkyBlockValue, Location location)
	{
		return new NmsChunk(location.getChunk()).getBrightness(enumSkyBlockValue, location.getX(), location.getY(), location.getZ());
	}

	public static boolean isModified(Chunk chunk)
	{
		return new NmsChunk(chunk).isModified();
	}

	public static boolean isModified(Location location)
	{
		return new NmsChunk(location.getChunk()).isModified();
	}

	public static void initLighting(Chunk chunk)
	{
		new NmsChunk(chunk).initLighting();
	}

	public static void sendUpdate(Chunk chunk, Player player)
	{
		new NmsChunk(chunk).sendUpdate(player);
	}

	public static void sendUpdate(Chunk chunk, Collection<? extends Player> players)
	{
		for(Player player : players)
			new NmsChunk(chunk).sendUpdate(player);
	}

	public static void save(Chunk chunk)
	{
		new NmsChunk(chunk).save();
	}
}
