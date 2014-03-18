package nl.gcompany.translate;

import java.util.List;

import com.google.api.services.translate.model.DetectionsListResponse;
import com.google.api.services.translate.model.DetectionsResourceItems;
import com.google.api.services.translate.model.LanguagesListResponse;
import com.google.api.services.translate.model.LanguagesResource;
import com.google.api.services.translate.model.TranslationsListResponse;
import com.google.api.services.translate.model.TranslationsResource;

;

public class Utilities {
	public static void displayTheResults(
			LanguagesListResponse languagesListResponse) {
		System.out.println("\nLanguages:");
		int j = 0;
		for (LanguagesResource languagesResource : languagesListResponse
				.getLanguages()) {
			if (j > 0) {
				System.out.print(", ");
			}
			System.out.print(languagesResource.getLanguage());
			j++;
		}
		System.out.print("\n");
	}

	public static void displayTheResults(List<String> queries,
			DetectionsListResponse detectionsListResponse) {
		System.out.println("\nDetections:");
		int i = 0;
		for (List<DetectionsResourceItems> d : detectionsListResponse
				.getDetections()) {
			for (DetectionsResourceItems detectionsResourceItems : d) {
				System.out.println(queries.get(i) + " is: "
						+ detectionsResourceItems.getLanguage());
			}
			i++;
		}
		System.out.print("\n");
	}

	public static void displayTheResults(List<String> texts,
			TranslationsListResponse translationsListResponse) {
		System.out.println("\nTranslations:");
		int i = 0;
		for (TranslationsResource d : translationsListResponse
				.getTranslations()) {
			System.out.println("Text: " + texts.get(i));
			System.out.println("Detected language: "
					+ d.getDetectedSourceLanguage());
			System.out.println("Translated text: " + d.getTranslatedText());
			System.out.print("\n");
			i++;
		}
	}
}
