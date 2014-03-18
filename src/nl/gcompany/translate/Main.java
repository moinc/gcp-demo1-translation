package nl.gcompany.translate;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.translate.Translate;
import com.google.api.services.translate.model.DetectionsListResponse;
import com.google.api.services.translate.model.LanguagesListResponse;
import com.google.api.services.translate.model.TranslationsListResponse;

public class Main {

	// Some generic objects needed to connect to the Translate API:
	// Note that we use NetHttpTransport in this standalone app. On App Engine
	// we would use UrlFetchTransport instead.
	private static final HttpTransport TRANSPORT = new NetHttpTransport();
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();

	// Copied from our project in the Developer Console:
	private static final String API_KEY = "AIzaSyBIztLW_3Zp4Gxx0Lju1uQwjGCDCsuUT8E";
	private static final String PROJECT_ID = "proven-mystery-518";

	public static void main(String[] args) throws IOException,
			GeneralSecurityException, URISyntaxException {

		// Create an instance of the Translate API Client.
		// The client requires:
		// - A transport mechanism, in this case HTTP.
		// - A JsonFactory implementation which takes care of serialization and
		// deserialization.
		// - We do not use user-authentication for this API, so we will set this
		// to null.
		// - The project id (which is not required but good practice)
		Translate translate = new Translate.Builder(TRANSPORT, JSON_FACTORY,
				null).setApplicationName(PROJECT_ID).build();

		/*
		 * Available languages
		 */

		// Request the "List" interface, notice that we set the API KEY.
		LanguagesListResponse languagesListResponse = translate.languages()
				.list().setKey(API_KEY).execute();

		// Display the results
		Utilities.displayTheResults(languagesListResponse);

		/*
		 * Language detection
		 */

		List<String> queries = new ArrayList<String>();
		// Add some Russian
		queries.add("Нибх квуёдам долорэм прё эю");
		// Add some English
		queries.add("How are you today?");
		// Add some Dutch
		queries.add("Wat een feestje");

		// Request the "Detections" interface, notice that we set the API KEY.
		DetectionsListResponse detectionsListResponse = translate.detections()
				.list(queries).setKey(API_KEY).execute();

		// Display the results
		Utilities.displayTheResults(queries, detectionsListResponse);

		/*
		 * Translation
		 */

		List<String> texts = new ArrayList<String>();
		// Add some Swahili
		texts.add("Hebu kwenda kula sandwich");
		// Add some English
		texts.add("How are you today?");
		// Add some Dutch
		texts.add("Wat een feestje");

		TranslationsListResponse translationsListResponse = translate
				.translations().list(texts, "nl").setKey(API_KEY).execute();

		// Display the results
		Utilities.displayTheResults(texts, translationsListResponse);
	}

}
