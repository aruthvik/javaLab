package com.aravind.ruthvik.analytics.configuration;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Holds the GA Credential which will be used to run the GA Query.
 * 
 * @author aravind.ruthvik
 * @since Aug 19, 2013 6:16:37 PM $Id:$
 */
@XStreamAlias("gaCredential")
public class GACredential implements Serializable {

	private static final long	serialVersionUID	= -149507084870486070L;

	/**
	 * Client Id
	 */
	private String				clientId;

	/**
	 * Client API Secret Key
	 */
	private String				clientSecretKey;

	/**
	 * Application Name against which data needs to be collected
	 */
	private String				gaApplicationName;

	/**
	 * Private Key Absolute path
	 */
	private String				privateKeyFileAbsoultePath;

	/**
	 * Service account ID (typically an e-mail address) or {@code null} for
	 * none.
	 */
	private String				serviceAccountId;

	/**
	 * Collection of OAuth scopes to use with the the service account flow or
	 * {@code null} for none.
	 */
	private String				serviceAccountScopes;

	/**
	 * Service Account User (user@domain.com)
	 */
	private String				serviceAccountUser;

	/**
	 * Default Constructor
	 */
	public GACredential() {
	}

	/**
	 * 
	 * @param serviceAccountId
	 * @param serviceAccountScopes
	 * @param serviceAccountUser
	 * @param clientId
	 * @param clientSecretKey
	 * @param privateKeyFileAbsoultePath
	 */
	public GACredential(String serviceAccountId, String serviceAccountScopes,
			String serviceAccountUser, String clientId, String clientSecretKey,
			String privateKeyFileAbsoultePath) {
		this.serviceAccountId = serviceAccountId;
		this.serviceAccountScopes = serviceAccountScopes;
		this.serviceAccountUser = serviceAccountUser;
		this.clientId = clientId;
		this.clientSecretKey = clientSecretKey;
		this.privateKeyFileAbsoultePath = privateKeyFileAbsoultePath;
	}

	public String getClientId() {
		return clientId;
	}

	public String getClientSecretKey() {
		return clientSecretKey;
	}

	public String getGaApplicationName() {
		return gaApplicationName;
	}

	public String getPrivateKeyFileAbsoultePath() {
		return privateKeyFileAbsoultePath;
	}

	public String getServiceAccountId() {
		return serviceAccountId;
	}

	public String getServiceAccountScopes() {
		return serviceAccountScopes;
	}

	public String getServiceAccountUser() {
		return serviceAccountUser;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void setClientSecretKey(String clientSecretKey) {
		this.clientSecretKey = clientSecretKey;
	}

	public void setGaApplicationName(String gaApplicationName) {
		this.gaApplicationName = gaApplicationName;
	}

	public void setPrivateKeyFileAbsoultePath(String privateKeyFileAbsoultePath) {
		this.privateKeyFileAbsoultePath = privateKeyFileAbsoultePath;
	}

	public void setServiceAccountId(String serviceAccountId) {
		this.serviceAccountId = serviceAccountId;
	}

	public void setServiceAccountScopes(String serviceAccountScopes) {
		this.serviceAccountScopes = serviceAccountScopes;
	}

	public void setServiceAccountUser(String serviceAccountUser) {
		this.serviceAccountUser = serviceAccountUser;
	}
}
