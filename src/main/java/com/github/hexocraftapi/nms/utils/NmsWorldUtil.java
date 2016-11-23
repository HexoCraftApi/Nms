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

import com.github.hexocraftapi.nms.NmsWorld;
import org.bukkit.Location;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class NmsWorldUtil
{

	public static void setBlockLight(Location location, int light)
	{
		new NmsWorld(location.getWorld()).setBlockLight(location.getX(), location.getY(), location.getZ(), light);
	}

	public static void setSkyLight(Location location, int light)
	{
		new NmsWorld(location.getWorld()).setSkyLight(location.getX(), location.getY(), location.getZ(), light);
	}

	public static void relightBlock(Location location)
	{
		new NmsWorld(location.getWorld()).relightBlock(location.getX(), location.getY(), location.getZ());
	}

	public static void relightSky(Location location)
	{
		new NmsWorld(location.getWorld()).relightSky(location.getX(), location.getY(), location.getZ());
	}

	public static void relight(Location location)
	{
		new NmsWorld(location.getWorld()).relight(location.getX(), location.getY(), location.getZ());
	}
}
