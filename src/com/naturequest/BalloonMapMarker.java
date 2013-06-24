package com.naturequest;

import java.util.ArrayList;

import android.graphics.drawable.Drawable;
import android.location.Location;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.naturequest.question.Question;

public class BalloonMapMarker extends BalloonItemizedOverlay<OverlayItem> 
{
	private ArrayList<OverlayItem> _overlays = new ArrayList<OverlayItem>();
	
	private MapView _mapView;
	private OverlayItem _overlayItem;
	
	private BalloonMapMarkerListener _listener;
	
	private Question question;
	
	public BalloonMapMarker(Drawable defaultMarker, MapView mapView, OverlayItem initialOverlayItem, Question question)
	{
		super(boundCenter(defaultMarker), mapView);
		
		// offsets the balloon callout
		int markerHeight = defaultMarker.getIntrinsicHeight();
		
		setBalloonBottomOffset(markerHeight / 2);
		
		this.question = question;
		addOverlay(initialOverlayItem);
		
		_mapView = mapView;
		_overlayItem = initialOverlayItem;
		
		_listener = (BalloonMapMarkerListener)mapView.getContext();
	}

	public void addOverlay(OverlayItem overlay)
	{
	    _overlays.add(overlay);
	    populate();
	}

	@Override
	protected OverlayItem createItem(int i)
	{
		return _overlays.get(i);
	}

	@Override
	public int size()
	{
		return _overlays.size();
	}
	
	@Override
	protected boolean onBalloonTap(int index, OverlayItem item)
	{
		hideBalloon();
		
		if (_listener != null)
		{
			_listener.onBalloonTap(this);
		}
		
		return true;
	}
	
	public boolean hitTest(int x, int y)
	{
		GeoPoint currentPoint = _mapView.getProjection().fromPixels(x, y);
		
		return isWithinDistance(currentPoint, _overlayItem.getPoint(), 10);
	}
	
	private boolean isWithinDistance(GeoPoint geoPoint1, GeoPoint geoPoint2, int distance)
	{
		Location startLocation = new Location("");
		Location endLocation = new Location("");

		startLocation.setLatitude(geoPoint1.getLatitudeE6() / 1E6);
		startLocation.setLongitude(geoPoint1.getLongitudeE6() / 1E6);

		endLocation.setLatitude(geoPoint2.getLatitudeE6() / 1E6);
		endLocation.setLongitude(geoPoint2.getLongitudeE6() / 1E6);
		
		if (startLocation.distanceTo(endLocation) < distance)
		{
			return true;
		}
		
		return false;
	}
	
	public Question getQuestion()
	{
		return this.question;
	}
}
