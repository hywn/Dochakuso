package net.hilaryoi.website.dochakuso;

import java.io.IOException;

public class Top extends Page {

	String topTemplate;

	Releases releases;

	public Top(String pageTemplate, String topTemplate, Releases releases) {

		super(pageTemplate);

		this.topTemplate = topTemplate;

		this.releases = releases;

	}

	@Override
	public String generatePage(String header, String footerTemplate, String... html) throws IOException {
		return String.format(template, "./", "Dochakuso Records", header,
				String.format(topTemplate, releases.getNewestRelease().generateContent()),
				String.format(footerTemplate, "イクゼ俺達ドチャドチャクソクソ丸"));
	}

}
