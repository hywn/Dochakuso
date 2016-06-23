package net.hilaryoi.website.dochakuso;

public class About extends Page {

	public About(String template) {
		super(template);

	}

	@Override
	public String generatePage(String header, String footerTemplate, String... html) {

		// only html should be about contents
		return String.format(template, "./", "- ABOUT -", header, html[0],
				String.format(footerTemplate, "足の親指の爪の間に挟まったホコリのようなもの"));

	}

}
