package com.github.hexocraftapi.nms.craft;

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

import com.github.hexocraftapi.reflection.resolver.minecraft.NMSClassResolver;
import com.github.hexocraftapi.reflection.resolver.minecraft.OBCClassResolver;
import com.github.hexocraftapi.reflection.util.AccessUtil;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class CraftResolver
{
	private static OBCClassResolver obcClassResolver = new OBCClassResolver();
	private static NMSClassResolver nmsClassResolver = new NMSClassResolver();

	private static Class<?> CraftPlayer = obcClassResolver.resolveSilent("entity.CraftPlayer");
	private static Class<?> CraftWorld = obcClassResolver.resolveSilent("CraftWorld");
	private static Class<?> CraftChunk = obcClassResolver.resolveSilent("CraftChunk");


	// EntityPlayer NmsPlayer = ((CraftPlayer) player).getHandle()
	public static Object getHandle(Player player)
	{
		try {
			Method method = AccessUtil.setAccessible(CraftPlayer.getDeclaredMethod("getHandle"));
			return method.invoke(player);
		}
		catch(Exception ignored) {}
		return null;
	}

	// WorldServer nmsWorld = ((CraftWorld) world).getHandle()
	public static Object getHandle(World world)
	{
		try {
			Method method = AccessUtil.setAccessible(CraftWorld.getDeclaredMethod("getHandle"));
			return method.invoke(world);
		}
		catch(Exception ignored) {}
		return null;
	}

	// Chunk nmsChunk = ((CraftChunk) chunk).getHandle()
	public static Object getHandle(Chunk chunk)
	{
		try {
			Method method = AccessUtil.setAccessible(CraftChunk.getDeclaredMethod("getHandle"));
			return method.invoke(chunk);
		}
		catch(Exception ignored) {}
		return null;
	}
}
