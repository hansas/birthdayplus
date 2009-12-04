/**
 * 
 */
package com.tau.birthdayplus.client;

import com.google.gwt.http.client.Header;
import com.google.gwt.http.client.Response;

final class FakeResponse extends Response {
	private final GadgetResponse response;

	FakeResponse(GadgetResponse response) {
		this.response = response;
	}

	@Override
	public String getText() {
		return response.getText();
	}

	@Override
	public String getStatusText() {
		return "OK";
	}

	@Override
	public int getStatusCode() {
		return 200;
	}

	@Override
	public String getHeadersAsString() {
		return null;
	}

	@Override
	public Header[] getHeaders() {
		return new Header[0];
	}

	@Override
	public String getHeader(String header) {
		return null;
	}
}