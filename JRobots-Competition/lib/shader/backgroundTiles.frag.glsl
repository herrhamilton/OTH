#version 120

uniform sampler2D tex_Color;
uniform sampler2D tex_Noise;

vec4 getHeights(vec2 coords)
{
	// read noise texture
	vec4 texel = vec4(0);
	texel.x = texture2D(tex_Noise, vec2(coords.x,   coords.y  ) / 512.0).x;
	texel.y = texture2D(tex_Noise, vec2(coords.x+1, coords.y  ) / 512.0).x;
	texel.z = texture2D(tex_Noise, vec2(coords.x,   coords.y+1) / 512.0).x;
	texel.w = texture2D(tex_Noise, vec2(coords.x+1, coords.y+1) / 512.0).x;
	
	// map this noise texture's values to [0..1]
	texel = (texel - 0.4) * 5;
	texel = clamp(texel, 0, 1);
	
	// discretize to {0..2}
	return floor(texel*2.9999);
}

vec4 getMinHeight(vec4 heights)
{
	return min(min(heights.xyzw, heights.yzwx), min(heights.zwxy, heights.wxyz));
}

float getTileTypeFromHeights(vec4 heights)
{
	return dot(heights, vec4(8, 4, 2, 1));
}

void main(void)
{
	// split tile coordinates into tileIndex (determines heightfield/noisefield
	// lookup and thus tile type) and tileLocal (which is the position within
	// the current tile)
	vec2 tileNorm = gl_TexCoord[0].st; // a tile has a bordersize of 1.0
	tileNorm  += vec2(0.0001); // rounding is a bitch...
	vec2 tileIndex = floor(tileNorm);
	vec2 tileLocal = fract(tileNorm);

	// get the 4 height values from the corners of this tile
	vec4 heights = getHeights(tileIndex);

	// minimum of the 4 height values
	// (this determines the lower of the two parts the tile consists of)
	vec4 minHeight = getMinHeight(heights);

	// use the differences between the height values to determine the
	// exact shape of the upper part of the tile 
	float tileShape = getTileTypeFromHeights(heights - minHeight);

	//tileLocal = clamp(tileLocal, 0.5 / 32.0, 31.5 / 32.0);

	// combine the position within the tile, the height of the lower tile and
	// the shape of the upper tile into texture coordinates
	vec2 texCoordUpper = tileLocal + vec2(tileShape, minHeight.x + 1);
	texCoordUpper *= vec2(1.0 / 32.0, 1.0 / 3.0);

	// bring variance into the full tiles
	float fullTileOffset = fract((tileIndex.x * 13 + tileIndex.y * 97) / 16.0) * 16.0;
	vec2 texCoordLower = tileLocal + vec2(15 + fullTileOffset, minHeight.x);
	texCoordLower *= vec2(1.0 / 32.0, 1.0 / 3.0);
	
	// finally lookup the lower and upper part
	vec4 colorUpper = texture2D(tex_Color, texCoordUpper);
	vec4 colorLower = texture2D(tex_Color, texCoordLower);

	if (texCoordLower.y < 0) {
		colorLower = vec4(0);
	}

	// blend the upper and lower part of the tile (regular alpha blending)
	gl_FragColor = vec4(mix(colorLower.xyz, colorUpper.xyz, colorUpper.w), 1.0f);
}
