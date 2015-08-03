/**
 * Copyright 2015 IBM Corp. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ibm.watson.developer_cloud.language_translation.v2;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;
import com.ibm.watson.developer_cloud.language_translation.v2.model.IdentifiableLanguage;
import com.ibm.watson.developer_cloud.language_translation.v2.model.IdentifiedLanguage;
import com.ibm.watson.developer_cloud.language_translation.v2.model.TranslationModel;
import com.ibm.watson.developer_cloud.language_translation.v2.model.TranslationResult;
import com.ibm.watson.developer_cloud.service.Request;
import com.ibm.watson.developer_cloud.service.WatsonService;
import com.ibm.watson.developer_cloud.util.HttpHeaders;
import com.ibm.watson.developer_cloud.util.MediaType;
import com.ibm.watson.developer_cloud.util.ResponseUtil;

/**
 * The IBM Watson Language Translation service translate text from one language
 * to another and identifies the language in which text is written.
 *
 * @author German Attanasio Ruiz (germanatt@us.ibm.com)
 * @version v2
 * @see <a
 *      href="http://www.ibm.com/smarterplanet/us/en/ibmwatson/developercloud/language-translation.html">
 *      Language Translation</a>
 */
public class LanguageTranslation extends WatsonService {

	public static final String DEFAULT = "default";
	private static final String LANGUAGES = "languages";
	public static final String MODEL_ID = "model_id";
	public static final String SOURCE = "source";
	public static final String TARGET = "target";
	public static final String TEXT = "text";

	/** The url. */
	private static final String URL = "https://gateway.watsonplatform.net/language-translation/api";

	/** The identifiable languages list type. */
	private final Type identifiableLanguagesListType = new TypeToken<List<IdentifiableLanguage>>() {
	}.getType();

	/** The language model list type. */
	private final Type translationModelListType = new TypeToken<List<IdentifiedLanguage>>() {
	}.getType();

	/** The model list type. */
	private final Type modelListType = new TypeToken<List<TranslationModel>>() {
	}.getType();

	/**
	 * Instantiates a new Language Translation service.
	 */
	public LanguageTranslation() {
		setEndPoint(URL);
	}

	

	/**
	 * Retrieves the list of identifiable languages.
	 * 
	 * @return the identifiable languages
	 * @see TranslationModel
	 */
	public List<IdentifiableLanguage> getIdentifiableLanguages() {
		Request request = Request.Get("/v2/identifiable_languages");

		HttpRequestBase requestBase = request.build();
		try {
			HttpResponse response = execute(requestBase);
			JsonObject jsonObject = ResponseUtil.getJsonObject(response);
			List<IdentifiableLanguage> langs = getGson().fromJson(
					jsonObject.get(LANGUAGES), identifiableLanguagesListType);
			return langs;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Retrieves the list of models.
	 * 
	 * @return the translation models
	 * @see TranslationModel
	 */
	public List<TranslationModel> getModels() {
		return getModels(null, null, null);
	}

	/**
	 * Retrieves the list of models.
	 * 
	 * @param showDefault
	 *            show default models
	 * @param source
	 *            the source
	 * @param target
	 *            the target
	 * @return the translation models
	 * @see TranslationModel
	 */
	public List<TranslationModel> getModels(final Boolean showDefault,
			final String source, final String target) {
		Request request = Request.Get("/v2/models");

		if (source != null && !source.isEmpty())
			request.withQuery(SOURCE, source);

		if (target != null && !target.isEmpty())
			request.withQuery(TARGET, source);

		if (showDefault != null)
			request.withQuery(DEFAULT, showDefault.booleanValue());

		HttpRequestBase requestBase = request.build();
		try {
			HttpResponse response = execute(requestBase);
			JsonObject jsonObject = ResponseUtil.getJsonObject(response);
			List<TranslationModel> models = getGson().fromJson(
					jsonObject.get("models"), modelListType);
			return models;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Identify language in which text is written.
	 * 
	 * @param text
	 *            the text to identify
	 * @return the identified language
	 */
	public List<IdentifiedLanguage> identify(final String text) {
		HttpRequestBase request = Request.Post("/v2/identify")
				.withContent(text, MediaType.TEXT_PLAIN)
				.withHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).build();

		try {
			HttpResponse response = execute(request);
			JsonObject jsonObject = ResponseUtil.getJsonObject(response);
			List<IdentifiedLanguage> identifiedLanguages = getGson().fromJson(
					jsonObject.get(LANGUAGES), translationModelListType);
			return identifiedLanguages;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LanguageTranslation [getEndPoint()=");
		builder.append(getEndPoint());
		builder.append("]");
		return builder.toString();
	}

	/**
	 * Translate paragraphs of text using a model and or source and target.
	 * model_id or source and target needs to be specified.
	 * If both are specified, then only model_id will be used
	 *
	 * @param text            The submitted paragraphs to translate
	 * @param source            the source language
	 * @param target            the target language
	 * @param model_id            the model id
	 * @return The {@link TranslationResult} 
	 */
	public TranslationResult translate(final Map<String, Object> params) {
		
		final String source = (String) params.get(SOURCE);
		final String target = (String) params.get(TARGET);
		final String modelId = (String) params.get(MODEL_ID);
		final String [] text;
		if (params.get(TEXT) != null) {
			if (params.get(TEXT) instanceof String)
				text = new String[]{(String) params.get(TEXT)};
			else
				text = (String[]) params.get(TEXT);
		} else {
			text = null;
		}
		
		if ((modelId == null || modelId.isEmpty())
				&& (source == null || source.isEmpty() || target == null || target
						.isEmpty()))
			throw new IllegalArgumentException(
					"model_id or source and target should be specified");

		if (text == null)
			throw new IllegalArgumentException("text can not be null");

		JsonObject contentJson = new JsonObject();

		// convert the text into a json array
		JsonArray paragraphs = new JsonArray();
		for (String paragraph : text) {
			paragraphs.add(new JsonPrimitive(paragraph));
		}
		contentJson.add(TEXT, paragraphs);
		
		Request requestBuilder = Request.Post("/v2/translate")
				.withContent(contentJson);
		
		if (source != null && !source.isEmpty())
			requestBuilder.withQuery(SOURCE, source);

		if (target != null && !target.isEmpty())
			requestBuilder.withQuery(TARGET, target);

		if (modelId != null && !modelId.isEmpty())
			requestBuilder.withQuery(MODEL_ID, modelId);

		HttpRequestBase request = requestBuilder.build();

		try {
			HttpResponse response = execute(request);
			String translationResult = ResponseUtil.getString(response);
			TranslationResult translation = getGson().fromJson(
					translationResult, TranslationResult.class);
			return translation;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}



	/**
	 * Translate text using a model
	 *
	 * @param text            The submitted paragraphs to translate
	 * @param model_id            the model id
	 * @return The {@link TranslationResult} 
	 */
	public TranslationResult translate(final String text,final  String modelId) {
		Map<String, Object> params = new HashMap<String,Object>();
		params.put(TEXT, text);
		params.put(MODEL_ID, modelId);
		return translate(params);
	}
	
	/**
	 * Translate text using source and target languages
	 *
	 * @param text            The submitted paragraphs to translate
	 * @param source          The source language
	 * @param target          The target language
	 * @return The {@link TranslationResult} 
	 */
	public TranslationResult translate(final String text,final String source,final String target) {
		Map<String, Object> params = new HashMap<String,Object>();
		params.put(TEXT, text);
		params.put(SOURCE, source);
		params.put(TARGET, target);
		return translate(params);
	}
	
}