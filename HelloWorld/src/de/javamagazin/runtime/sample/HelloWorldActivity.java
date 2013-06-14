package de.javamagazin.runtime.sample;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.esri.android.map.LocationService;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISTiledMapServiceLayer;
import com.esri.core.io.EsriSecurityException;
import com.esri.core.io.ProxySetup;
import com.esri.core.io.UserCredentials;
import com.esri.core.symbol.SimpleMarkerSymbol;

public class HelloWorldActivity extends Activity {

	MapView mMapView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		setupProxy();
		mMapView = (MapView) findViewById(R.id.map);
		// Add dynamic layer to MapView
		mMapView.addLayer(new ArcGISTiledMapServiceLayer(
				""
						+ "http://services.arcgisonline.com/ArcGIS/rest/services/World_Street_Map/MapServer"));

		LocationService locationService = mMapView.getLocationService();

		SimpleMarkerSymbol s = new SimpleMarkerSymbol(Color.RED, 15,
				com.esri.core.symbol.SimpleMarkerSymbol.STYLE.CIRCLE);
		locationService.setSymbol(s);
		locationService.start();

		// OpenStreetMapLayer openStreetMapLayer = new OpenStreetMapLayer(true);
		// mMapView.addLayer(openStreetMapLayer);
	}

	private void setupProxy() {
		String proxyHost = "158.32.144.2";
		int proxyPort = 80;
		UserCredentials userCredentials = new UserCredentials();
		userCredentials.setUserAccount("MarcoHuether", "jeejse567");

		try {
			ProxySetup.setupProxy(proxyHost, proxyPort, userCredentials);
		} catch (EsriSecurityException e) {
			Log.e(STORAGE_SERVICE, "test", e);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mMapView.pause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mMapView.unpause();
	}
}