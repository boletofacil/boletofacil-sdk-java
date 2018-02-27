package com.boletobancario.boletofacilsdk;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.StringUtils;

import com.boletobancario.boletofacilsdk.enums.BoletoFacilEnvironment;
import com.boletobancario.boletofacilsdk.enums.ResponseType;
import com.boletobancario.boletofacilsdk.exceptions.BoletoFacilException;
import com.boletobancario.boletofacilsdk.exceptions.BoletoFacilMethodNotAllowedException;
import com.boletobancario.boletofacilsdk.exceptions.BoletoFacilRequestException;
import com.boletobancario.boletofacilsdk.model.ModelBase;
import com.boletobancario.boletofacilsdk.model.response.BaseResponse;
import com.boletobancario.boletofacilsdk.model.response.ErrorResponse;

public abstract class BoletoFacilBase {

	protected BoletoFacilBase() {
		this.version = PROPERTIES.getProperty("version");
	}

	public void setProxy(String proxyHost) {
		setProxy(proxyHost, 80, null);
	}

	public void setProxy(String proxyHost, int proxyPort) {
		setProxy(proxyHost, proxyPort, null);
	}

	public void setProxy(String proxyHost, int proxyPort, Credentials credentials) {
		HTTP_CLIENT.getHostConfiguration().setProxy(proxyHost, proxyPort);
		if (credentials != null) {
			HTTP_CLIENT.getState().setProxyCredentials(AuthScope.ANY, credentials);
		}
	}

	protected <T extends BaseResponse> T request(StringBuilder requestUri, ResponseType responseType, Class<T> clazz) {
		return request(new GetMethod(), requestUri, responseType, clazz);
	}

	protected <T extends BaseResponse> T postRequest(StringBuilder requestUri, ResponseType responseType,
	        Class<T> clazz) {
		return request(new PostMethod(), requestUri, responseType, clazz);
	}

	private <T extends BaseResponse> T request(HttpMethod method, StringBuilder requestUri, ResponseType responseType,
	        Class<T> clazz) {
		addUriParameter(requestUri, "responseType", responseType.name());

		try {
			int statusCode = HTTP_CLIENT.executeMethod(createRequest(method, requestUri));
			String responseBody = extractResponse(method);

			if (statusCode == HttpStatus.SC_OK) {
				return getResponse(responseType, responseBody, clazz);
			}
			if (statusCode == HttpStatus.SC_METHOD_NOT_ALLOWED) {
				throw new BoletoFacilMethodNotAllowedException("A chamada " + getAPIActionName(requestUri)
				        + " não suporta o método " + method + ". Verifique se existe alguma atualização do"
				        + " SDK ou entre em contato com a equipe do Boleto Fácil.");
			}
			ErrorResponse error = getResponse(responseType, responseBody, ErrorResponse.class);
			throw new BoletoFacilRequestException(statusCode, error);
		} catch (IOException e) {
			throw new BoletoFacilException("Erro na requisição", e);
		}
	}

	private HttpMethod createRequest(HttpMethod method, StringBuilder requestUri) throws URIException {
		method.addRequestHeader("X-Requested-With", "Boleto Facil SDK Java " + version);
		method.setURI(new URI(requestUri.toString(), false));
		return method;
	}

	private static String extractResponse(HttpMethod method) throws IOException {
		StringBuilder textBuilder = new StringBuilder();
		try (InputStream responseBodyAsStream = method.getResponseBodyAsStream()) {
			try (Reader reader = new BufferedReader(
			        new InputStreamReader(responseBodyAsStream, Charset.forName(PROPERTIES.getProperty("encoding"))))) {
				int c = 0;
				while ((c = reader.read()) != -1) {
					textBuilder.append((char) c);
				}
			}
		}
		return textBuilder.toString();
	}

	private <T extends BaseResponse> T getResponse(ResponseType responseType, String responseBody, Class<T> clazz) {
		return responseType == ResponseType.JSON ? (T) ModelBase.fromJson(responseBody, clazz)
		        : (T) ModelBase.fromXml(responseBody, clazz);
	}

	protected void addTokenUriParameter(StringBuilder requestUri) {
		addUriParameter(requestUri, "token", token);
	}

	protected void addUriParameter(StringBuilder requestUri, String parameter, Boolean value) {
		addUriParameter(requestUri, parameter, value == null ? StringUtils.EMPTY : value.toString());
	}

	protected void addUriParameter(StringBuilder requestUri, String parameter, Long value) {
		addUriParameter(requestUri, parameter, value == null ? StringUtils.EMPTY : value.toString());
	}

	protected void addUriParameter(StringBuilder requestUri, String parameter, Integer value) {
		addUriParameter(requestUri, parameter, value == null ? StringUtils.EMPTY : value.toString());
	}

	protected void addUriParameter(StringBuilder requestUri, String parameter, BigDecimal value) {
		if (value != null) {
			value = value.setScale(2, BigDecimal.ROUND_DOWN);
			NumberFormat nf = NumberFormat.getNumberInstance(java.util.Locale.US);
			nf.setGroupingUsed(false);
			addUriParameter(requestUri, parameter, nf.format(value));
		} else {
			addUriParameter(requestUri, parameter, StringUtils.EMPTY);
		}
	}

	protected void addUriParameter(StringBuilder requestUri, String parameter, Calendar value) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		addUriParameter(requestUri, parameter, value == null ? StringUtils.EMPTY : df.format(value.getTime()));
	}

	protected void addUriParameter(StringBuilder requestUri, String parameter, String value) {
		if (StringUtils.isNotBlank(value)) {
			String separator = requestUri.toString().endsWith("?") ? "" : "&";
			requestUri.append(separator + parameter + "=" + value);
		}
	}

	private String getAPIActionName(StringBuilder requestUri) {
		String request = requestUri.toString();
		int questionMark = request.indexOf('?');
		String requestEndPoint = request.substring(0, questionMark);

		int lastSlash = requestEndPoint.lastIndexOf('/');
		return requestEndPoint.substring(lastSlash + 1);
	}

	protected URI getEndPoint() {
		try {
			switch (boletoFacilEnvironment) {
			case PRODUCTION:
				return new URI(PROPERTIES.getProperty("uri.production"), false);
			case SANDBOX:
				return new URI(PROPERTIES.getProperty("uri.sandbox"), false);
			default:
				return new URI(PROPERTIES.getProperty("uri.unittests"), false);
			}
		} catch (URIException | NullPointerException e) {
			throw new BoletoFacilException("Erro ao definir o EndPoint", e);
		}
	}

	private static final HttpClient HTTP_CLIENT = new HttpClient(new MultiThreadedHttpConnectionManager());

	private static final Properties PROPERTIES = new Properties();
	static {
		try {
			InputStream propertiesFile = BoletoFacil.class.getResourceAsStream("/boletofacilsdk.properties");
			PROPERTIES.load(propertiesFile);
		} catch (FileNotFoundException e) {
			throw new BoletoFacilException("Arquivo boletofacilsdk.properties não encontrado.");
		} catch (IOException e) {
			throw new BoletoFacilException("Erro ao ler arquivo boletofacilsdk.properties.");
		}
	}

	private String version;

	protected BoletoFacilEnvironment boletoFacilEnvironment;

	public BoletoFacilEnvironment getBoletoFacilEnvironment() {
		return boletoFacilEnvironment;
	}

	protected String token;

	public String getToken() {
		return token;
	}
}
