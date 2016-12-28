/*
 * Copyright 2016 hexosse
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.github.hexocraftapi.nms;

import com.github.hexocraftapi.nms.craft.CraftResolver;
import com.github.hexocraftapi.nms.craft.Nms;
import com.github.hexocraftapi.reflection.resolver.MethodResolver;
import com.github.hexocraftapi.reflection.resolver.ResolverQuery;
import com.github.hexocraftapi.reflection.resolver.minecraft.NMSClassResolver;
import com.github.hexocraftapi.reflection.util.AccessUtil;
import org.bukkit.block.Chest;

import java.lang.reflect.Field;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class nmsChest extends Nms
{
	static class Reflection {
		private static final Class<?>       nmsTileEntityChest               = new NMSClassResolver().resolveSilent("TileEntityChest");
		private static final MethodResolver nmsTileEntityChestMethodResolver = new MethodResolver(nmsTileEntityChest);
	}

	private Chest chest;


	public nmsChest(Chest chest)
	{
		this.chest = chest;
		this.nms = CraftResolver.getTileEntity(chest);
	}

	// Get craftChest name
	public String getName()
	{
		return this.chest.getInventory().getTitle();
	}

	// Set craftChest name
	public void setName(String name)
	{
		try
		{
			Reflection.nmsTileEntityChestMethodResolver
				.resolve(new ResolverQuery("a", String.class))
				.invoke(nms, name);

			update();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void update()
	{
		try
		{
			Reflection.nmsTileEntityChestMethodResolver
				.resolve(new ResolverQuery("update"))
				.invoke(nms);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
