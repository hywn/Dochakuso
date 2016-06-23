package net.hilaryoi.website.dochakuso;

import java.io.IOException;

abstract class Page {

	String template;

	public Page(String template) {
		this.template = template;

	}

	// generates it according to template
	public abstract String generatePage(String header, String footerTemplate, String... html) throws IOException;

}