package com.naturequest;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class SimpleMapMarker extends ItemizedOverlay<OverlayItem>
{
	private OverlayItem _item;
	
	public SimpleMapMarker(Drawable defaultMarker, OverlayItem item) 
	{
		super(boundCenter(defaultMarker));
		_item = item;
		populate();
	}
	
	@Override
	protected OverlayItem createItem(int i) 
	{
		return _item;
	}
	
	@Override
	public int size() 
	{
		return 1;
	}

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow)
	{
		super.draw(canvas, mapView, false);
	}
}