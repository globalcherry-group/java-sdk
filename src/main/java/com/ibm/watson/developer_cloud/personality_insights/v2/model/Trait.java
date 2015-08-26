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
package com.ibm.watson.developer_cloud.personality_insights.v2.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.ibm.watson.developer_cloud.util.GsonSingleton;

/**
 * The personality trait/model POJO class.
 */
public class Trait {

	/** The category. */
	private String category;
	
	/** The children. */
	private List<Trait> children;
	
	/** The id. */
	private String id;
	
	/** The name. */
	private String name;
	
	/** The percentage. */
	private double percentage;

	/** The raw sampling error. */
	@SerializedName("raw_sampling_error")
	private Double rawSamplingError;
	
	/** The raw score. */
	@SerializedName("raw_score")
	private Double rawScore;
	
	/** The sampling error. */
	@SerializedName("sampling_error")
	private double samplingError;

	/**
	 * Gets the personality model category.
	 * e.g: "values", "needs" or "personality"
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Gets the characteristic children.
	 * 
	 * @return the children
	 */
	public List<Trait> getChildren() {
		return children;
	}

	/**
	 * Gets id of the characteristic, globally unique.,
	 * 
	 * @return the characteristic identifier
	 */
	public String getId() {
		return id;
	}

	/**
	 * Gets the user-displayable name of the characteristic.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the normalized value of the characteristic, from 0-1.
	 * For example, if the percentage for Openness is 0.25, you scored
	 * in the 25th percentile. You are more open than 24% of the
	 * population and less open than 74% of the population.,
	 * 
	 * @return the percentage
	 */
	public double getPercentage() {
		return percentage;
	}

	/**
	 * Gets the raw sampling error.
	 *
	 * @return the raw sampling error
	 */
	public double getRawSamplingError() {
		return rawSamplingError;
	}

	/**
	 * Gets the raw score.
	 *
	 * @return the raw score
	 */
	public double getRawScore() {
		return rawScore;
	}

	/**
	 * Indicates the sampling error of the percentage, based on the
	 * number of words in the input. The number defines a 95% confidence
	 * interval around the percentage. For example, the sampling error is
	 * 4% and percentage is 61%. It is 95% likely that the actual percentage
	 * value is between 57% and 65% if more words are given.
	 * 
	 * @return the sampling error
	 */
	public double getSamplingError() {
		return samplingError;
	}

	/**
	 * Sets personality model category.
	 * e.g: "values", "needs" or "personality"
	 * 
	 * @param category
	 *            the new category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Sets the characteristic children.
	 * 
	 * @param children
	 *            the new children
	 */
	public void setChildren(List<Trait> children) {
		this.children = children;
	}

	/**
	 * Sets the id of the characteristic, globally unique.,
	 * 
	 * @param id
	 *            the characteristic identifier
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Sets the user-displayable name of the characteristic.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the normalized value of the characteristic, from 0-1.
	 * 
	 * @param percentage
	 *            the new percentage value from 0-1
	 */
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	/**
	 * Sets the raw sampling error.
	 *
	 * @param rawSamplingError the new raw sampling error
	 */
	public void setRawSamplingError(double rawSamplingError) {
		this.rawSamplingError = rawSamplingError;
	}

	/**
	 * Sets the raw score.
	 *
	 * @param rawScore the new raw score
	 */
	public void setRawScore(double rawScore) {
		this.rawScore = rawScore;
	}

	/**
	 * Sets the sampling error of the percentage based on the
	 * number of words in the input.
	 * 
	 * @param samplingError error
	 *            the new sampling error
	 */
	public void setSamplingError(double samplingError) {
		this.samplingError = samplingError;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Trait trait = (Trait) o;

		if (Double.compare(trait.percentage, percentage) != 0) return false;
		if (Double.compare(trait.samplingError, samplingError) != 0) return false;
		if (category != null ? !category.equals(trait.category) : trait.category != null) return false;
		if (children != null ? !children.equals(trait.children) : trait.children != null) return false;
		if (!id.equals(trait.id)) return false;
		if (name != null ? !name.equals(trait.name) : trait.name != null) return false;
		if (rawSamplingError != null ? !rawSamplingError.equals(trait.rawSamplingError) : trait.rawSamplingError != null)
			return false;
		return !(rawScore != null ? !rawScore.equals(trait.rawScore) : trait.rawScore != null);

	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = category != null ? category.hashCode() : 0;
		result = 31 * result + (children != null ? children.hashCode() : 0);
		result = 31 * result + id.hashCode();
		result = 31 * result + (name != null ? name.hashCode() : 0);
		temp = Double.doubleToLongBits(percentage);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + (rawSamplingError != null ? rawSamplingError.hashCode() : 0);
		result = 31 * result + (rawScore != null ? rawScore.hashCode() : 0);
		temp = Double.doubleToLongBits(samplingError);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/* (non-Javadoc)
         * @see java.lang.Object#toString()
         */
	@Override
	public String toString() {
		return getClass().getName() + " "
				+ GsonSingleton.getGson().toJson(this);
	}


}
