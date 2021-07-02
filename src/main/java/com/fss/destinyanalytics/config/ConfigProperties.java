package com.fss.destinyanalytics.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
@PropertySource("classpath:application.properties")
public class ConfigProperties {
	// private final Logger logger =
	// LoggerFactory.getLogger(ConfigProperties.class);

	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String user;
	@Value("${spring.datasource.password}")
	private String password;
	@Value("${authenticationType}")
	private String authenticationType;
	@Value("${workspaceId}")
	private String workspaceId;
	@Value("${clientId}")
	private String clientId;
	@Value("${tenantId}")
	private String tenantId;
	@Value("${pbiUsername}")
	private String pbiUsername;
	@Value("${pbiPassword}")
	private String pbiPassword;
	@Value("${appSecret}")
	private String appSecret;
	@Value("${authorityUrl}")
	private String authorityUrl;
	@Value("${scopeUrl}")
	private String scopeUrl;
	
	
	
	public String getAuthorityUrl() {
		return authorityUrl;
	}
	public void setAuthorityUrl(String authorityUrl) {
		this.authorityUrl = authorityUrl;
	}
	public String getScopeUrl() {
		return scopeUrl;
	}
	public void setScopeUrl(String scopeUrl) {
		this.scopeUrl = scopeUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAuthenticationType() {
		return authenticationType;
	}
	public void setAuthenticationType(String authenticationType) {
		this.authenticationType = authenticationType;
	}
	public String getWorkspaceId() {
		return workspaceId;
	}
	public void setWorkspaceId(String workspaceId) {
		this.workspaceId = workspaceId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getPbiUsername() {
		return pbiUsername;
	}
	public void setPbiUsername(String pbiUsername) {
		this.pbiUsername = pbiUsername;
	}
	public String getPbiPassword() {
		return pbiPassword;
	}
	public void setPbiPassword(String pbiPassword) {
		this.pbiPassword = pbiPassword;
	}
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
}
