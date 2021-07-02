// ----------------------------------------------------------------------------
// Copyright (c) Microsoft Corporation.
// Licensed under the MIT license.
// ----------------------------------------------------------------------------

package com.fss.destinyanalytics.models;

import java.util.List;

/**
 * Properties for embedding the report 
 */
public class EmbedConfig {
	public List<ReportConfig> embedReports;

	public EmbedToken embedToken;

	public String errorMessage;
}
