package com.util;

import java.security.Principal;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public final class JSFUtil {
	/**
	 */
	public static final Object getObjectFromContext(final String attribute) {
		final ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		Object value = ec.getRequestParameterMap().get(attribute);

		if (value == null) {
			value = ec.getSessionMap().get(attribute);
		}
		return value;
	}

	/**
	 */
	public static final void saveMessage(final String message,
			final Severity severity) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(severity, message, ""));
	}

	/**
	 */
	public static final void saveMessage(final String message) {
		saveMessage(message, FacesMessage.SEVERITY_ERROR);
	}

	/**
	 */
	public static final void saveMessages(final List<String> messages) {
		if (messages != null) {
			for (final String m : messages) {
				saveMessage(m);
			}
		}
	}

	/**
	 */
	public static final String getPrincipalName() {
		final Principal p = FacesContext.getCurrentInstance()
				.getExternalContext().getUserPrincipal();
		return p != null ? p.getName() : "";
	}
}