package com.github.hexocraftapi.nms;

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

import com.github.hexocraftapi.nms.craft.Nms;
import com.github.hexocraftapi.reflection.resolver.ConstructorResolver;
import com.github.hexocraftapi.reflection.resolver.minecraft.NMSClassResolver;
import org.bukkit.Location;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class NmsBlockPosition extends Nms
{
	static class Reflection {
		private static final Class<?>            nmsBlockPosition       = new NMSClassResolver().resolveSilent("BlockPosition");
		private static final ConstructorResolver nmsConstructorResolver = new ConstructorResolver(nmsBlockPosition);
	}

	private double x;
	private double y;
	private double z;


	public NmsBlockPosition(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;

		try
		{
			nms = Reflection.nmsConstructorResolver
					.resolve(new Class[] {Double.TYPE, Double.TYPE, Double.TYPE})
					.newInstance(new Object[] { x, y, z });

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public NmsBlockPosition(Location location)
	{
		this(location.getX(), location.getY(), location.getZ());
	}
}
