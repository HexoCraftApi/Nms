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
import com.github.hexocraftapi.reflection.resolver.FieldResolver;
import com.github.hexocraftapi.reflection.resolver.minecraft.NMSClassResolver;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class NmsEnumSkyBlock extends Nms
{
	static class Reflection
	{
		private static final Class<?>      nmsEnumSkyBlock              = new NMSClassResolver().resolveSilent("EnumSkyBlock");
		private static final FieldResolver nmsEnumSkyBlockFieldResolver = new FieldResolver(nmsEnumSkyBlock);
	}

	// BLOCK : 0
	// SKY : 15
	private int enumskyblock;
	private Object BLOCK;
	private Object SKY;


	public NmsEnumSkyBlock(int enumSkyBlockValue)
	{
		this.enumskyblock = enumSkyBlockValue;

		if(BLOCK == null)
		{
			try
			{
				if(Reflection.nmsEnumSkyBlock.isEnum())
				{
					for(Object objEnum: Reflection.nmsEnumSkyBlock.getEnumConstants())
					{
						Object objEnumValue = Reflection.nmsEnumSkyBlockFieldResolver.resolve("c").get(objEnum) ;

						if((int)objEnumValue == 0)
							BLOCK = objEnum;
						else if((int)objEnumValue == 15)
							SKY = objEnum;
					}
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}

		if(enumSkyBlockValue == 0)
			nms = BLOCK;
		else if(enumSkyBlockValue == 15)
			nms = SKY;
	}
}
